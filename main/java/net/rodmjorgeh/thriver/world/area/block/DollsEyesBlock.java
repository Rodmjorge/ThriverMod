package net.rodmjorgeh.thriver.world.area.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.plus.EntityAdder;
import net.rodmjorgeh.thriver.world.area.GameRuleRegistry;

public class DollsEyesBlock extends FlowerBlock {

    public static final int TICKS_TO_BLIND = 100;

    public DollsEyesBlock(BlockBehaviour.Properties properties) {
        super(MobEffects.NIGHT_VISION, 20.0F, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level instanceof ServerLevel serverLevel
                && entity instanceof EntityAdder aEntity
                && entity instanceof LivingEntity livingEntity) {

            if ((serverLevel.getGameRules().getBoolean(GameRuleRegistry.RULE_DO_DOLLS_EYES_BLIND) || !(entity instanceof Player))
                && !livingEntity.hasEffect(MobEffects.BLINDNESS)) {
                aEntity.setIsInDollsEyes(true);
            }
        }
    }
}
