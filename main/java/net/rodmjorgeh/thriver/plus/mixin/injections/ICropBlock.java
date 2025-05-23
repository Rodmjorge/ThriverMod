package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.world.area.block.state.properties.BlockStatePropertyReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(CropBlock.class)
public class ICropBlock {

    /**
     * If the Farmland block is silty (that is, if there is a Silt Mud block below it), then make the crops grow faster
     * by 2.5x. Not a bad deal.
     */
    @ModifyConstant(method = "getGrowthSpeed", constant = @Constant(floatValue = 3.0F))
    private static float getGrowthSpeed(float f, BlockState state, BlockGetter getter, BlockPos pos) {
        BlockState stateBelow = getter.getBlockState(pos.below());
        float f1 = stateBelow.getValue(BlockStatePropertyReg.SILTY) ? 2.5F : 1.0F;

        return f * f1;
    }
}
