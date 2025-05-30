package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
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

        // Organization
        this.generator.getAdvancement("adventure", "honey_block_slide", provider)
                .parent(this.generator.createParent(this, "safely_harvest_honey"))
                .save(writer, this.getFolderMinecraft("adventure", "honey_block_slide"));

        this.generator.getAdvancement(this, "silk_touch_nest", provider)
                .parent(this.generator.createParent(this, "safely_harvest_honey"))
                .save(writer, this.getFolderMinecraft("silk_touch_nest"));

        this.generator.getAdvancement(this, "tadpole_in_a_bucket", provider)
                .parent(this.generator.createParent(this, "tactical_fishing"))
                .save(writer, this.getFolderMinecraft("tadpole_in_a_bucket"));

        this.generator.getAdvancement(this, "tame_an_animal", provider)
                .parent(this.generator.createParent(this, "breed_an_animal"))
                .save(writer, this.getFolderMinecraft("tame_an_animal"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
