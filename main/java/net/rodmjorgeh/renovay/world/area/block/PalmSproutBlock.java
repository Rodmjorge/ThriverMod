package net.rodmjorgeh.renovay.world.area.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import net.rodmjorgeh.renovay.world.area.block.state.TreeGrowerR;

public class PalmSproutBlock extends SaplingBlock {

    public PalmSproutBlock(BlockBehaviour.Properties pProperties) {
        super(TreeGrowerR.PALM, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.SAND) || super.mayPlaceOn(state, level, pos);
    }
}
