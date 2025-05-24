package net.rodmjorgeh.thriver.data.advancements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.ibm.icu.impl.number.range.PrefixInfixSuffixLengthHelper;
import com.mojang.serialization.*;
import net.minecraft.FileUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.rodmjorgeh.thriver.ThriverMod;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AdvancementDataGenerator extends AdvancementProvider {

    private static final String ADVANCEMENT_COPY_FOLDER = "advancement_copy";

    public AdvancementDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, getProviderEntries(output));
    }

    private static List<AdvancementSubProvider> getProviderEntries(PackOutput output) {
        return List.of(
                new AdventureAdvancementsDataGenerator(output, "adventure"),
                new HusbandryAdvancementsDataGenerator(output, "husbandry")
        );
    }

    public static Advancement.Builder getAdvancement(AdvancementDataGeneratorProvider provider, String name,
                                                     HolderLookup.Provider lookupProvider) {
        return getAdvancement(provider, name, lookupProvider, true);
    }
    /**
     * Since this is in runClientData, the file for the advancement has to be inside the resources folder.
     */
    public static Advancement.Builder getAdvancement(AdvancementDataGeneratorProvider provider, String name,
                                                     HolderLookup.Provider lookupProvider, boolean addCriteria) {
        ResourceLocation location = copiedAdvancement(provider.getType(), name);
        PackOutput output = provider.getOutput();

        DynamicOps<JsonElement> ops = lookupProvider.createSerializationContext(JsonOps.INSTANCE);
        Codec<Optional<Advancement>> codec = ConditionalOps.createConditionalCodec(Advancement.CODEC);
        String outputFolder = output.getOutputFolder().toString();
        Path filePath = Path.of(outputFolder.replace("generated", "main"), "data", location.getNamespace(), location.getPath());

        Optional<Advancement> optional = Optional.empty();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            Optional<Optional<Advancement>> result = codec.parse(ops, JsonParser.parseReader(reader)).result();
            if (result.isEmpty()) {
                throw new JsonParseException("Couldn't parse the file from path " + filePath);
            }

            optional = result.get();
        } catch (IOException e) {
            ThriverMod.LOGGER.error("Couldn't find file '{}'.", filePath);
        }

        if (optional.isEmpty()) {
            throw new NoSuchElementException("Couldn't find advancement from ID " + location);
        }

        Advancement advancement = optional.get();
        Advancement.Builder builder = Advancement.Builder.advancement()
                                        .parent(advancement.parent().get())
                                        .display(advancement.display().get())
                                        .rewards(advancement.rewards());

        if (addCriteria) {
            Map<String, Criterion<?>> criteria = advancement.criteria();

            for (String key : criteria.keySet()) {
                Criterion<?> criterion = criteria.get(key);
                builder.addCriterion(key, criterion);
            }
        }
        if (advancement.sendsTelemetryEvent()) {
            builder.sendsTelemetryEvent();
        }

        return builder;
    }

    private static ResourceLocation copiedAdvancement(String type, String filename) {
        String locName = ADVANCEMENT_COPY_FOLDER + "/" + type + "/";
        return ResourceLocation.withDefaultNamespace(locName + filename + ".json");
    }

    public static AdvancementHolder createParent(AdvancementDataGeneratorProvider provider, String advancementLocation) {
        return createParent(provider, advancementLocation, "minecraft");
    }
    public static AdvancementHolder createParent(AdvancementDataGeneratorProvider provider, String advancementLocation, String namespace) {
        String type = provider.getType();
        return AdvancementSubProvider.createPlaceholder(namespace + ":" + type + "/" + advancementLocation);
    }
}
