package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.state.properties.BlockStatePropertyReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmBlock.class)
public class FarmBlockMxn {

    /**
     * Adds a new property for the {@link FarmBlock} block state, to know if there's a Silt Mud block below it. This is
     * used in {@link CropBlockMxn}, because if the Farmland is silty, then the crops grow faster by an insane 2.5x.
     */
    @Unique private static final BooleanProperty SILTY = BlockStatePropertyReg.SILTY;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/FarmBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"))
    public BlockState modifyStateDefinition(BlockState state) {
        FarmBlock block = (FarmBlock)(Object)this;
        return block.getStateDefinition().any()
                .setValue(FarmBlock.MOISTURE, Integer.valueOf(0))
                .setValue(SILTY, Boolean.valueOf(false));
    }

    @Inject(method = "randomTick", at = @At("TAIL"))
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        FarmBlock instance = (FarmBlock)(Object)this;
        BlockState stateBelow = level.getBlockState(pos.below());
        boolean isSilty = stateBelow.is(BlockReg.SILT_MUD.get());

        if (state.getValue(FarmBlock.MOISTURE) == 7 &&
                (instance.isNearWater(level, pos) || level.isRainingAt(pos.above()))) {
            level.setBlock(pos, state.setValue(SILTY, Boolean.valueOf(isSilty)), 2);
        }
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SILTY);
    }
}
