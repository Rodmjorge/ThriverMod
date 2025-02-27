package net.rodmjorgeh.renovay.plus.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.block.PalmLeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.logging.Logger;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockP {

    /**
     * For the palm trees, many of the leaves blocks go way farther than the maximum distance for leaves to not decay,
     * thus, this method basically is a copy of the original but adds my decay distance of 12 instead of the usual 7.
     */
    @Inject(method = "updateDistance", at = @At("HEAD"), cancellable = true)
    private static void updateDistance(BlockState state, LevelAccessor level, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (state.getBlock() instanceof PalmLeavesBlock) {
            int decayDist = PalmLeavesBlock.DECAY_DISTANCE;
            int j = decayDist;

            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.relative(direction);

                j = Math.min(j, LeavesBlock.getOptionalDistanceAt(level.getBlockState(offsetPos)).orElse(decayDist) + 1);
                if (j == 1) {
                    break;
                }
            }

            cir.setReturnValue(state.setValue(PalmLeavesBlock.DISTANCE, Integer.valueOf(j)));
        }
    }

    @ModifyVariable(method = "updateShape", at = @At("STORE"))
    private int distanceAt(int i, BlockState state, LevelReader levelReader, ScheduledTickAccess scheduledTick, BlockPos pos, Direction neighborDir,
                           BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        LeavesBlock block = (LeavesBlock)(Object)this;

        return LeavesBlock.getOptionalDistanceAt(neighborState)
                .orElse((block instanceof PalmLeavesBlock) ? PalmLeavesBlock.DECAY_DISTANCE : LeavesBlock.DECAY_DISTANCE);
    }
}
