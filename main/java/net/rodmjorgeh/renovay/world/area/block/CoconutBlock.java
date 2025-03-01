package net.rodmjorgeh.renovay.world.area.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.rodmjorgeh.renovay.world.area.entity.damage.DamageSourcesR;

public class CoconutBlock extends FallingBlock implements BonemealableBlock {
    public static final MapCodec<CoconutBlock> CODEC = simpleCodec(CoconutBlock::new);

    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(6.0, 10.0, 6.0, 10.0, 14.0, 10.0),
            Block.box(5.5, 9.0, 5.5, 10.5, 14.0, 10.5),
            Block.box(4.5, 6.0, 4.5, 11.5, 14.0, 11.5),
            Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0)
    };

    public CoconutBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    protected MapCodec<? extends CoconutBlock> codec() { return CODEC; }

    /**
     * This overrides {@code tick()} from {@link FallingBlock}. Checks when the coconut has reached the maximum age,
     * meaning it stops being attached to the tree and starts falling down.
     */
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) >= MAX_AGE) {
            super.tick(state, level, pos, random);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);

        if (age < MAX_AGE && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, Integer.valueOf(age + 1)), 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) != MAX_AGE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateAbove = level.getBlockState(pos.above());
        if (isValidBonemealTarget(level, pos, state)) {
            return stateAbove.is(BlockRegistry.PALM_LEAVES.get());
        }

        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = this.defaultBlockState();
        Direction direction = context.getNearestLookingDirections()[0];

        if (direction.equals(Direction.UP)) {
            if (state.canSurvive(level, pos)) {
                return state;
            }
        }

        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTick, BlockPos pos, Direction neighborDir,
                                     BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return !state.canSurvive(level, pos) ?
                Blocks.AIR.defaultBlockState() :
                super.updateShape(state, level, scheduledTick, pos, neighborDir, neighborPos, neighborState, random);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected void falling(FallingBlockEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(1.5F, 20);
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return DamageSourcesR.coconut(entity);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) != MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) { return true; }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, Integer.valueOf(state.getValue(AGE) + 1)), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    /**
     * In {@link FallingBlock}, this method is used for sand and gravel, where it spawns particles whenever
     * there's no blocks below. Not needed for the coconut since it wouldn't make sense now, would it.
     */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) { }
}
