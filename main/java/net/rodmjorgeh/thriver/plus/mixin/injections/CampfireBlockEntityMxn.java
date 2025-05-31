package net.rodmjorgeh.thriver.plus.mixin.injections;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMxn {

    @Unique private static final int ADVANCEMENT_GIVE_MAX_DISTANCE = 40;

    @Inject(method = "cookTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;gameEvent(Lnet/minecraft/core/Holder;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V",
            shift = At.Shift.AFTER))
    private static void cookTick(ServerLevel level,
                                 BlockPos pos,
                                 BlockState state,
                                 CampfireBlockEntity campfire,
                                 RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> check,
                                 CallbackInfo ci,
                                 @Local(ordinal = 1) ItemStack itemCooked) {

        Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), ADVANCEMENT_GIVE_MAX_DISTANCE, false);
        ThriverMod.LOGGER.debug("Player: " + player);
        if (player != null) {
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggerReg.CAMPFIRE_COOKED.get().trigger(serverPlayer, pos, itemCooked);
            }
        }
    }
}
