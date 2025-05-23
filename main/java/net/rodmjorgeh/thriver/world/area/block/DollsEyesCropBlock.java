package net.rodmjorgeh.thriver.world.area.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rodmjorgeh.thriver.world.item.ItemReg;

public class DollsEyesCropBlock extends CropBlock {
    public static final MapCodec<DollsEyesCropBlock> CODEC = simpleCodec(DollsEyesCropBlock::new);
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(4.0, 0.0, 4.0, 11.0, 4.0, 11.0),
            Block.box(3.0, 0.0, 3.0, 12.0, 6.0, 12.0),
            Block.box(2.0, 0.0, 2.0, 13.0, 10.0, 13.0),
    };

    public DollsEyesCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) == 0) {
            super.randomTick(state, level, pos, random);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public BlockState getStateForAge(int age) {
        return (age == MAX_AGE) ? BlockReg.DOLLS_EYES.get().defaultBlockState() : super.getStateForAge(age);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemReg.DOLLS_EYE_SEEDS.get();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return 1;
    }
}
