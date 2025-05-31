package net.rodmjorgeh.thriver.advancements.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;

import java.util.Optional;

public class PlayedReedFluteTrigger extends SimpleCriterionTrigger<PlayedReedFluteTrigger.TriggerInstance> {

    @Override
    public Codec<PlayedReedFluteTrigger.TriggerInstance> codec() {
        return PlayedReedFluteTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack item, int mobAmount) {
        this.trigger(player, x -> x.matches(item, mobAmount));
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player,
                                         Optional<ItemPredicate> item,
                                         MinMaxBounds.Ints mobAmount) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<PlayedReedFluteTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                x -> x.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(PlayedReedFluteTrigger.TriggerInstance::player),
                        ItemPredicate.CODEC.optionalFieldOf("item").forGetter(PlayedReedFluteTrigger.TriggerInstance::item),
                        MinMaxBounds.Ints.CODEC.optionalFieldOf("mob_amount", MinMaxBounds.Ints.ANY).forGetter(PlayedReedFluteTrigger.TriggerInstance::mobAmount)
                ).apply(x, PlayedReedFluteTrigger.TriggerInstance::new)
        );

        public static Criterion<PlayedReedFluteTrigger.TriggerInstance> playedReedFlute(HolderGetter<Item> registry, ItemLike item, MinMaxBounds.Ints mobAmount) {
            ItemPredicate itemPredicate = ItemPredicate.Builder.item().of(registry, item).build();
            return playedReedFlute(itemPredicate, mobAmount);
        }

        public static Criterion<PlayedReedFluteTrigger.TriggerInstance> playedReedFlute(ItemPredicate item, MinMaxBounds.Ints mobAmount) {
            return CriteriaTriggerReg.PLAYED_REED_FLUTE.get().createCriterion(
                    new PlayedReedFluteTrigger.TriggerInstance(Optional.empty(), Optional.of(item), mobAmount)
            );
        }

        public boolean matches(ItemStack item, int mobAmount) {
            return (this.item.isEmpty() || this.item.get().test(item)) && this.mobAmount.matches(mobAmount);
        }
    }
}
