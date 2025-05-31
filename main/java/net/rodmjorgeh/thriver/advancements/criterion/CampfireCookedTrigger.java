package net.rodmjorgeh.thriver.advancements.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;

import java.util.Optional;

public class CampfireCookedTrigger extends SimpleCriterionTrigger<CampfireCookedTrigger.TriggerInstance> {

    @Override
    public Codec<CampfireCookedTrigger.TriggerInstance> codec() {
        return CampfireCookedTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockPos pos, ItemStack itemCooked) {
        ServerLevel level = player.serverLevel();
        BlockState state = level.getBlockState(pos);
        LootParams params = new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .create(LootContextParamSets.ADVANCEMENT_LOCATION);

        ThriverMod.LOGGER.debug("Triggered! You dumb bitch!");
        this.trigger(player, x -> x.matches(new LootContext.Builder(params).create(Optional.empty()), itemCooked));
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player,
                                         Optional<ContextAwarePredicate> location,
                                         Optional<Holder<Item>> itemCooked) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<CampfireCookedTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                x -> x.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(CampfireCookedTrigger.TriggerInstance::player),
                        ContextAwarePredicate.CODEC.optionalFieldOf("location").forGetter(CampfireCookedTrigger.TriggerInstance::location),
                        BuiltInRegistries.ITEM.holderByNameCodec().optionalFieldOf("item_cooked").forGetter(CampfireCookedTrigger.TriggerInstance::itemCooked)
                ).apply(x, CampfireCookedTrigger.TriggerInstance::new)
        );

        public static Criterion<CampfireCookedTrigger.TriggerInstance> campfireCooked(LocationPredicate.Builder location) {
            return campfireCooked(ContextAwarePredicate.create(
                    LocationCheck.checkLocation(location).build()
            ));
        }

        public static Criterion<CampfireCookedTrigger.TriggerInstance> campfireCooked(ContextAwarePredicate predicate) {
            return CriteriaTriggerReg.CAMPFIRE_COOKED.get().createCriterion(
                    new CampfireCookedTrigger.TriggerInstance(
                            Optional.empty(),
                            Optional.of(predicate),
                            Optional.empty()
                    )
            );
        }

        public boolean matches(LootContext context, ItemStack itemCooked) {
            if (this.location.isPresent() && !this.location.get().matches(context)) {
                return false;
            }

            return this.itemCooked.isEmpty() || itemCooked.is(this.itemCooked.get());
        }
    }
}
