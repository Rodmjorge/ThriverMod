package net.rodmjorgeh.renovay.world.area.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rodmjorgeh.renovay.util.tags.BlockTagRegistry;
import net.rodmjorgeh.renovay.world.area.block.state.properties.BlockStatePropertyRegistry;
import net.rodmjorgeh.renovay.world.area.block.state.properties.TripleBlockStep;
import org.jetbrains.annotations.Nullable;

public class TallReedBlock extends BushBlock {
    public static final MapCodec<TallReedBlock> CODEC = simpleCodec(TallReedBlock::new);

    public static final EnumProperty<TripleBlockStep> STEP = BlockStatePropertyRegistry.TRIPLE_BLOCK_STEP;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape[] SHAPE_BY_STEP = new VoxelShape[] {
            Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)
    };

    @Override
    public MapCodec<? extends TallReedBlock> codec() { return CODEC; }

    public TallReedBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(STEP, TripleBlockStep.BOTTOM)
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(STEP) == TripleBlockStep.TOP ? SHAPE_BY_STEP[1] : SHAPE_BY_STEP[0];
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTagRegistry.MUD);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTick, BlockPos pos, Direction neighborDir,
                                     BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        boolean check = neighborDir.getAxis() != Direction.Axis.Y;
        boolean flag = true;

        if (!check) {
            boolean check2 = neighborState.is(this);
            switch (state.getValue(STEP)) {
                default:
                case TripleBlockStep.BOTTOM:
                    flag = neighborDir != Direction.UP || check2 && neighborState.getValue(STEP) == TripleBlockStep.MIDDLE;
                    break;

                case TripleBlockStep.MIDDLE:
                    flag = check2 && neighborState.getValue(STEP) == ((neighborDir == Direction.UP) ? TripleBlockStep.TOP : TripleBlockStep.BOTTOM);
                    break;

                case TripleBlockStep.TOP:
                    flag = neighborDir != Direction.DOWN || check2 && neighborState.getValue(STEP) == TripleBlockStep.MIDDLE;
                    break;
            }
        }

        return flag ? super.updateShape(state, level, scheduledTick, pos, neighborDir, neighborPos, neighborState, random) : Blocks.AIR.defaultBlockState();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluidState = level.getFluidState(pos);

        boolean flag = true;
        for (int i = 1; i < 3; i++) {
            if (!(pos.getY() < level.getMaxY() - (i - 1) && level.getBlockState(pos.above(i)).canBeReplaced())) {
                flag = false;
            }
        }

        return flag ? super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER)) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        place(level, pos, this.defaultBlockState(), 3, false);
    }

    protected static void place(Level level, BlockPos pos, BlockState state, int flags, boolean placeOrigin) {
        TripleBlockStep[] arr = TripleBlockStep.values();
        for (int i = placeOrigin ? 0 : 1; i < 3; i++) {
            level.setBlock(pos.above(i), state.setValue(STEP, arr[i]), flags);
        }
    }

    /**
     * Only the bottom portion of the block can be inside water. If the block above is inside water as well, then that's
     * not allowed, so it cannot survive.
     */
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        TripleBlockStep stepProperty = state.getValue(STEP);
        if (stepProperty != TripleBlockStep.BOTTOM && state.getValue(WATERLOGGED)) {
            return false;
        }
        if (state.getBlock() != this) {
            return super.canSurvive(state, level, pos);
        }

        BlockState stateBelow = level.getBlockState(pos.below());
        boolean check = stateBelow.is(this);
        return switch (stepProperty) {
            case TripleBlockStep.BOTTOM -> super.canSurvive(state, level, pos);
            case TripleBlockStep.MIDDLE -> check && stateBelow.getValue(STEP) == TripleBlockStep.BOTTOM;
            default -> check && stateBelow.getValue(STEP) == TripleBlockStep.MIDDLE;
        };
    }

    @Override
    protected FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STEP, WATERLOGGED);
    }

    @Override
    protected long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(
                pos.getX(), pos.below(state.getValue(STEP).ordinal()).getY(), pos.getZ()
        );
    }
}
