package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;

import java.util.function.Consumer;

public class NetherAdvancementsDataGenerator implements AdvancementSubProvider, AdvancementDataGeneratorProvider {

    private final AdvancementDataGenerator generator;
    private final String type;

    public NetherAdvancementsDataGenerator(AdvancementDataGenerator generator, String type) {
        this.generator = generator;
        this.type = type;
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> writer) {
        // Organization
        this.generator.getAdvancement("story", "follow_ender_eye", provider)
                .parent(this.generator.createParent(this, "obtain_blaze_rod"))
                .save(writer, this.getFolderMinecraft("story", "follow_ender_eye"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
