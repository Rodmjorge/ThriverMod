package net.rodmjorgeh.thriver.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.data.loot.LootDataGenerator;

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

    default ResourceLocation copyLocation(String type, String filename) {
        String locName = this.getCopyFolder() + "/" + type + "/";
        return ResourceLocation.withDefaultNamespace(locName + filename + ".json");
    }

    /**
     * Since this is in runClientData, the file has to be inside the resources folder inside a special copy data type
     * folder.
     */
    default T getInfoFromFile(String type, String name, HolderLookup.Provider lookupProvider) {
        ResourceLocation location = this.copyLocation(type, name);
        PackOutput output = this.getOutput();

        RegistryOps<JsonElement> ops = lookupProvider.createSerializationContext(JsonOps.INSTANCE);
        Codec<Optional<T>> conditionalCodec = ConditionalOps.createConditionalCodec(this.getCodec());
        String outputFolder = output.getOutputFolder().toString();
        Path filePath = Path.of(ResourceMod.mainFromGenerated(outputFolder), "data", location.getNamespace(), location.getPath());

        Optional<T> optional = Optional.empty();
        try (JsonReader reader = new JsonReader(Files.newBufferedReader(filePath, StandardCharsets.UTF_8))) {
            reader.setLenient(false);
            JsonElement parsed = Streams.parse(reader);
            DataResult<Optional<T>> result = conditionalCodec.parse(ops, parsed);

            if (result.isError()) {
                ThriverMod.LOGGER.error("Couldn't parse file from ID " + filePath + ", giving error: " + result.error().get());
            }

            optional = result.getOrThrow();
        } catch (IOException e) {
            ThriverMod.LOGGER.error("Couldn't find file '{}'.", filePath);
        }

        if (optional.isEmpty()) {
            throw new NoSuchElementException("Couldn't find type from ID " + location);
        }

        return optional.get();
    }

    /**
     * Creates a new DataGenerator, like {@link LootDataGenerator}. Since every DataGenerator that implements this
     * interface has a new parameter in its constructor, it needs its own create method.
     */
    static <T extends DataProvider, U> DataProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries,
                                                         DataProviderUpdated<T, U> builder) {
        List<U> list = new ArrayList<>();
        DataProvider instance = builder.create(output, registries, list);

        if (instance instanceof Datagen<?, ?> datagen) {
            datagen.setOutput(output);
            list.addAll((List<U>)datagen.getProviderEntries()); // this might be dangerous
        }

        return instance;
    }

    @FunctionalInterface
    interface DataProviderUpdated<T extends DataProvider, U> {
        T create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, List<U> entryList);
    }
}
