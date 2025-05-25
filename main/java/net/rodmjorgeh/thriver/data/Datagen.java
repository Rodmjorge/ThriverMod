package net.rodmjorgeh.thriver.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.rodmjorgeh.thriver.ThriverMod;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Datagen<T, U> {

    String getCopyFolder();
    Codec<T> getCodec();

    PackOutput getOutput();
    void setOutput(PackOutput output);

    List<U> getProviderEntries();

    default ResourceLocation copyLocation(DataGeneratorProvider provider, String filename) {
        String locName = this.getCopyFolder() + "/" + provider.getType() + "/";
        return ResourceLocation.withDefaultNamespace(locName + filename + ".json");
    }

    default T getInfoFromFile(DataGeneratorProvider provider, String name, HolderLookup.Provider lookupProvider) {
        ResourceLocation location = this.copyLocation(provider, name);
        PackOutput output = this.getOutput();

        DynamicOps<JsonElement> ops = lookupProvider.createSerializationContext(JsonOps.INSTANCE);
        Codec<Optional<T>> conditionalCodec = ConditionalOps.createConditionalCodec(this.getCodec());
        String outputFolder = output.getOutputFolder().toString();
        Path filePath = Path.of(outputFolder.replace("generated", "main"), "data", location.getNamespace(), location.getPath());

        Optional<T> optional = Optional.empty();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            Optional<Optional<T>> result = conditionalCodec.parse(ops, JsonParser.parseReader(reader)).result();
            if (result.isEmpty()) {
                throw new JsonParseException("Couldn't parse the file from path " + filePath);
            }

            optional = result.get();
        } catch (IOException e) {
            ThriverMod.LOGGER.error("Couldn't find file '{}'.", filePath);
        }

        if (optional.isEmpty()) {
            throw new NoSuchElementException("Couldn't find type from ID " + location);
        }

        return optional.get();
    }

    static <T extends DataProvider, U> DataProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries,
                                                         DataProviderUpdated<T, U> builder) {
        List<U> list = new ArrayList<>();
        DataProvider instance = builder.create(output, registries, list);

        if (instance instanceof Datagen<?, ?> datagen) {
            datagen.setOutput(output);
            list.addAll((List<U>)datagen.getProviderEntries());
        }

        return instance;
    }

    @FunctionalInterface
    interface DataProviderUpdated<T extends DataProvider, U> {
        T create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, List<U> entryList);
    }
}
