package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;

import java.util.function.Consumer;

public class HusbandryAdvancementsDataGenerator implements AdvancementSubProvider, AdvancementDataGeneratorProvider {

    private final AdvancementDataGenerator generator;
    private final String type;

    public HusbandryAdvancementsDataGenerator(AdvancementDataGenerator generator, String type) {
        this.generator = generator;
        this.type = type;
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> writer) {
        this.generator.getAdvancement(this, "plant_any_sniffer_seed", provider)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("dolls_eyes", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(BlockReg.DOLLS_EYES_CROP.get()))
                .save(writer, this.getFolderMinecraft("plant_any_sniffer_seed"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
