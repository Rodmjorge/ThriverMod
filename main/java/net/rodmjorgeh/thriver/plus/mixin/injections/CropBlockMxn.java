package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.world.area.block.state.properties.BlockStatePropertyReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(CropBlock.class)
public class CropBlockMxn {

    /**
     * If the Farmland block is silty (that is, if there is a Silt Mud block below it), then make the crops grow faster
     * by 2.5x. Not a bad deal.
     */
    @ModifyConstant(method = "getGrowthSpeed", constant = @Constant(floatValue = 3.0F))
    private static float getGrowthSpeed(float f, BlockState state, BlockGetter getter, BlockPos pos) {
        BlockState stateBelow = getter.getBlockState(pos.below());

        /* This may look like a redundant if statement, but there's actually a possibility of a crop spawning on top
           of, say, a Dirt Path instead of Farmland. I would know since this happened to me, and it just crashed the
           game afterward.*/
        float f1 = 1.0F;
        if (stateBelow.is(Blocks.FARMLAND)) {
            f1 = stateBelow.getValue(BlockStatePropertyReg.SILTY) ? 2.5F : 1.0F;
        }

        return f * f1;
    }
}
