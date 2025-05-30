package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class MiningAdvancementsDataGenerator implements AdvancementSubProvider, AdvancementDataGeneratorProvider {

    private final AdvancementDataGenerator generator;
    private final String type;

    public MiningAdvancementsDataGenerator(AdvancementDataGenerator generator, String type) {
        this.generator = generator;
        this.type = type;
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> writer) {
        HolderGetter<Item> items = provider.lookupOrThrow(Registries.ITEM);

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        Blocks.STONE,
                        this.createTitle("root"),
                        this.createDescription("root"),
                        this.createBackground(),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("get_wooden_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(items, ItemTags.PICKAXES)))
                .save(writer, this.getFolder("root"));

        // Organization
        this.generator.getAdvancement("story", "root", provider, false)
                .addCriterion("remove", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(writer, this.getFolderMinecraft("story", "root"));
        this.generator.getAdvancement("story", "mine_stone", provider)
                .parent(root)
                .save(writer, this.getFolderMinecraft("story", "mine_stone"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
