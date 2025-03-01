package net.rodmjorgeh.renovay.world.area.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.world.area.block.state.TreeGrowerR;

public class PalmSproutBlock extends SaplingBlock {
    public static final MapCodec<PalmSproutBlock> CODEC = simpleCodec(PalmSproutBlock::new);

    public PalmSproutBlock(BlockBehaviour.Properties pProperties) {
        super(TreeGrowerR.PALM, pProperties);
    }

    @Override
    public MapCodec<? extends PalmSproutBlock> codec() { return CODEC; }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.SAND) || super.mayPlaceOn(state, level, pos);
    }
}
