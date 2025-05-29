package net.rodmjorgeh.thriver.world.area.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import net.rodmjorgeh.thriver.world.area.GameRuleReg;
import net.rodmjorgeh.thriver.world.area.entity.InsideBlockEffectTypeThr;

public class DollsEyesBlock extends FlowerBlock {
    public static final MapCodec<DollsEyesBlock> CODEC = RecordCodecBuilder.mapCodec(
            x -> x.group(
                    EFFECTS_FIELD.forGetter(FlowerBlock::getSuspiciousEffects),
                    propertiesCodec()
            ).apply(x, DollsEyesBlock::new)
    );

    public static final int TICKS_TO_BLIND = 100;

    public DollsEyesBlock(Holder<MobEffect> effect, float seconds, BlockBehaviour.Properties properties) {
        this(makeEffectList(effect, seconds), properties);
    }
    private DollsEyesBlock(SuspiciousStewEffects effects, BlockBehaviour.Properties properties) {
        super(effects, properties);
    }

    @Override
    public MapCodec<DollsEyesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier) {
        if (entity instanceof LivingEntity livingEntity && level instanceof ServerLevel) {
            if (this.checkLevel(level) && !livingEntity.hasEffect(MobEffects.BLINDNESS)) {
                applier.apply(InsideBlockEffectTypeThr.CLOSE_EYES);
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (this.checkLevel(level) && random.nextDouble() > 0.6F) {
            double x = pos.getX() + 0.35F + (random.nextDouble() - 0.8F) * 0.5F;
            double y = pos.getY() + 0.4F + (random.nextDouble() - 0.75F) * 0.4F;
            double z = pos.getZ() + 0.35F + (random.nextDouble() - 0.8F) * 0.5F;

            level.addParticle(
                    ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, ARGB.color(255, MobEffects.BLINDNESS.value().getColor())),
                    x, y, z,
                    0.0F, 0.0F, 0.0F
            );
        }
    }


    private boolean checkLevel(Level level) {
        boolean b0 = true;
        if (level instanceof ServerLevel serverLevel) {
            b0 = serverLevel.getGameRules().getBoolean(GameRuleReg.RULE_DO_DOLLS_EYES_BLIND);
        }

        return level.getDifficulty() != Difficulty.PEACEFUL && b0;
    }
}
