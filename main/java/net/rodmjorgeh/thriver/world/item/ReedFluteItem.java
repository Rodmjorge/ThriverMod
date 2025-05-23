package net.rodmjorgeh.thriver.world.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;
import net.rodmjorgeh.thriver.sound.SoundEventReg;

import java.util.List;
import java.util.stream.Stream;

public class ReedFluteItem extends Item {
    private static final int COOLDOWN = 10;

    public ReedFluteItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return Mth.floor(this.getCooldown() / 3F);
    }

    /**
     * Small rant, but when you make a mob stop being angry via the {@code stopBeingAngry()} function, you also need to
     * set targets inside the available goals of the entity, for some reason. Kinda annoying.
     */
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        player.startUsingItem(hand);
        player.getCooldowns().addCooldown(item, this.getCooldown());
        level.playSound(player, player.getOnPos(), SoundEventReg.REED_FLUTE_PLAY.get(), SoundSource.RECORDS);

        if (level instanceof ServerLevel) {
            Vec3 playerPos = player.position();

            List<Mob> mobsNear = level.getEntitiesOfClass(
                                    Mob.class,
                                    new AABB(playerPos.x - 8.0F, playerPos.y - 8.0F, playerPos.z - 8.0F, playerPos.x + 8.0F, playerPos.y + 8.0F, playerPos.z + 8.0F),
                                    EntitySelector.ENTITY_STILL_ALIVE
                                 );
            getNeutralMobs(mobsNear).forEach(x -> {
                NeutralMob mob = (NeutralMob)x;
                if (player.getUUID().equals(mob.getPersistentAngerTarget())) {
                    mob.stopBeingAngry();
                    x.targetSelector.getAvailableGoals()
                            .stream()
                            .filter(y -> y.getGoal() instanceof TargetGoal)
                            .map(y -> (TargetGoal)y.getGoal())
                            .forEach(y -> y.targetMob = null);

                    x.setLastHurtByMob(null);
                }
            });

            int i = (int)getNeutralMobs(mobsNear).count();
            if (i > 0 && player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggerReg.PLAYED_REED_FLUTE.get().trigger(serverPlayer, item, i);
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        this.finishUsingItem(stack, level, entity);
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return stack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    private static Stream<Mob> getNeutralMobs(List<Mob> mobList) {
        return mobList.stream()
                .filter(x -> x instanceof NeutralMob);
    }

    private int getCooldown() {
        return COOLDOWN * 20;
    }
}
