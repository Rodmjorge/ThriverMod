package net.rodmjorgeh.thriver.world.area.areagen.feature.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.CoconutBlock;

import java.util.List;

public class CoconutDecorator extends TreeDecorator {
    public static final MapCodec<CoconutDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(CoconutDecorator::new, instance -> instance.probability);
    private final float probability;

    public CoconutDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TreeDecoratorsReg.COCONUT.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();

        if (random.nextDouble() < this.probability) {
            List<BlockPos> leavesPos = context.leaves();

            if (!leavesPos.isEmpty()) {
                int minY = leavesPos.getFirst().getY();
                leavesPos.stream().filter(pos -> pos.getY() == minY)
                        .forEach(pos -> {
                            if (random.nextDouble() > 0.45F) {
                                BlockPos belowLeaves = pos.below();
                                if (context.isAir(belowLeaves)) {
                                    context.setBlock(belowLeaves, BlockReg.COCONUT.get()
                                            .defaultBlockState()
                                            .setValue(CoconutBlock.AGE, Integer.valueOf(random.nextInt(3)))
                                    );
                                }
                            }
                        });
            }
        }
    }
}
