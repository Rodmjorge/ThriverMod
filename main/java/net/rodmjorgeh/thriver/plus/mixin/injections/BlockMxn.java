package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMxn {

    @Inject(method = "playerDestroy", at = @At("TAIL"))
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggerReg.DESTROYED_BLOCK.get().trigger(serverPlayer, state, tool, tool.isCorrectToolForDrops(state));
        }
    }
}
