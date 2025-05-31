package net.rodmjorgeh.thriver.advancements.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;

import java.util.Optional;

public class DestroyedBlockTrigger extends SimpleCriterionTrigger<DestroyedBlockTrigger.TriggerInstance> {

    @Override
    public Codec<DestroyedBlockTrigger.TriggerInstance> codec() {
        return DestroyedBlockTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockState block, ItemStack item, boolean usedRightTool) {
        this.trigger(player, x -> x.matches(block, item, usedRightTool));
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player,
                                         Optional<BlockPredicate> block,
                                         Optional<Holder<Item>> item,
                                         boolean needsRightTool) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<DestroyedBlockTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                x -> x.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(DestroyedBlockTrigger.TriggerInstance::player),
                        BlockPredicate.CODEC.optionalFieldOf("block").forGetter(DestroyedBlockTrigger.TriggerInstance::block),
                        BuiltInRegistries.ITEM.holderByNameCodec().optionalFieldOf("item").forGetter(DestroyedBlockTrigger.TriggerInstance::item),
                        Codec.BOOL.optionalFieldOf("needs_right_tool", true).forGetter(DestroyedBlockTrigger.TriggerInstance::needsRightTool)
                ).apply(x, DestroyedBlockTrigger.TriggerInstance::new)
        );

        public static Criterion<DestroyedBlockTrigger.TriggerInstance> destroyedBlock(EntityPredicate.Builder player,
                                                                                      boolean needsRightTool,
                                                                                      HolderGetter<Block> registry,
                                                                                      Block... blocks) {
            BlockPredicate predicate = BlockPredicate.Builder.block().of(registry, blocks).build();
            return destroyedBlock(player, predicate, needsRightTool);
        }

        public static Criterion<DestroyedBlockTrigger.TriggerInstance> destroyedBlock(EntityPredicate.Builder player,
                                                                                      BlockPredicate block,
                                                                                      boolean needsRightTool) {
            return CriteriaTriggerReg.DESTROYED_BLOCK.get().createCriterion(
                    new DestroyedBlockTrigger.TriggerInstance(
                            Optional.of(EntityPredicate.wrap(player)),
                            Optional.of(block),
                            Optional.empty(),
                            needsRightTool
                    )
            );
        }

        public boolean matches(BlockState block, ItemStack item, boolean usedRightTool) {
            if (this.block.isPresent()) {
                Optional<HolderSet<Block>> blockSet = this.block.get().blocks();
                if (blockSet.isEmpty() || !blockSet.get().contains(block.getBlockHolder())) {
                    return false;
                }
            }
            if (this.item.isPresent() && !item.is(this.item.get())) {
                return false;
            }

            return !this.needsRightTool || usedRightTool;
        }
    }
}
