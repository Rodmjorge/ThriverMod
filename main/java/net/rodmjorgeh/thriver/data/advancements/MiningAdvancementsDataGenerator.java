package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.rodmjorgeh.thriver.advancements.criterion.CampfireCookedTrigger;
import net.rodmjorgeh.thriver.advancements.criterion.DestroyedBlockTrigger;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        HolderGetter<Block> blocks = provider.lookupOrThrow(Registries.BLOCK);

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

        AdvancementHolder getAmethystShard = Advancement.Builder.advancement()
                .parent(this.generator.createParent("story", "iron_tools"))
                .display(
                        Items.AMETHYST_SHARD,
                        this.createTitle("get_amethyst_shard"),
                        this.createDescription("get_amethyst_shard"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("get_amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(writer, this.getFolder("get_amethyst_shard"));

        Advancement.Builder.advancement()
                .parent(getAmethystShard)
                .display(
                        Blocks.AMETHYST_BLOCK,
                        this.createTitle("place_amethyst_blocks"),
                        this.createDescription("place_amethyst_blocks"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("place_amethyst", amethystBlockRowCheck(10, blocks))
                .save(writer, this.getFolder("place_amethyst_blocks"));

        AdvancementHolder mineDiamondsAtBedrock = Advancement.Builder.advancement()
                .parent(this.generator.createParent("story", "mine_diamond"))
                .display(
                        Blocks.BEDROCK,
                        this.createTitle("mine_diamonds_at_bedrock"),
                        this.createDescription("mine_diamonds_at_bedrock"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("broken_block", DestroyedBlockTrigger.TriggerInstance.destroyedBlock(
                        EntityPredicate.Builder.entity().steppingOn(
                                LocationPredicate.Builder.location().setBlock(
                                        BlockPredicate.Builder.block().of(blocks, Blocks.BEDROCK)
                                )
                        ),
                        true,
                        blocks,
                        Blocks.DIAMOND_ORE,
                        Blocks.DEEPSLATE_DIAMOND_ORE
                ))
                .save(writer, this.getFolder("mine_diamonds_at_bedrock"));

        Advancement.Builder.advancement()
                .parent(this.generator.createParent("story", "lava_bucket"))
                .display(
                        Blocks.CAMPFIRE,
                        this.createTitle("cook_at_deepslate_level"),
                        this.createDescription("cook_at_deepslate_level"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("use_campfire",
                        CampfireCookedTrigger.TriggerInstance.campfireCooked(
                                LocationPredicate.Builder
                                        .atYLocation(MinMaxBounds.Doubles.atMost(0.0D))
                                        .setBlock(
                                                BlockPredicate.Builder.block().setProperties(
                                                        StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(CampfireBlock.LIT, true)
                                                                .hasProperty(CampfireBlock.SIGNAL_FIRE, true)
                                                )
                                        )
                        )
                )
                .save(writer, this.getFolder("cook_at_deepslate_level"));


        // Organization
        this.generator.getAdvancement("story", "root", provider, false)
                .addCriterion("remove", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(writer, this.getFolderMinecraft("story", "root"));

        this.generator.getAdvancement("story", "mine_stone", provider)
                .parent(root)
                .save(writer, this.getFolderMinecraft("story", "mine_stone"));

        this.generator.getAdvancement("story", "lava_bucket", provider)
                .parent(this.generator.createParent("story", "mine_diamond"))
                .save(writer, this.getFolderMinecraft("story", "lava_bucket"));

        this.generator.getAdvancement("adventure", "spyglass_at_parrot", provider)
                .parent(getAmethystShard)
                .save(writer, this.getFolderMinecraft("adventure", "spyglass_at_parrot"));

        this.generator.getAdvancement("adventure", "fall_from_world_height", provider)
                .parent(mineDiamondsAtBedrock)
                .save(writer, this.getFolderMinecraft("adventure", "fall_from_world_height"));
    }

    private static Criterion<ItemUsedOnLocationTrigger.TriggerInstance> amethystBlockRowCheck(int count, HolderGetter<Block> blockRegistry) {
        LootItemCondition.Builder[] conditions = Arrays.stream(Direction.values())
                .filter(x -> x != Direction.DOWN && x != Direction.UP)
                .map(x -> {
                    List<LootItemCondition.Builder> list = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        Vec3i vec3i = x.getUnitVec3i();

                        LootItemCondition.Builder itemCondBuilder = LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setBlock(
                                        BlockPredicate.Builder.block().of(blockRegistry, Blocks.AMETHYST_BLOCK)),
                                new BlockPos(new Vec3i(vec3i.getX() * i, vec3i.getY() * i, vec3i.getZ() * i))
                        );
                        list.add(itemCondBuilder);
                    }

                    LootItemCondition.Builder[] arr = list.toArray(LootItemCondition.Builder[]::new);
                    return AllOfCondition.allOf(arr);
                })
                .toArray(LootItemCondition.Builder[]::new);

        return ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(AnyOfCondition.anyOf(conditions));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
