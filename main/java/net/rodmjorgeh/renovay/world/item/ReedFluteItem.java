package net.rodmjorgeh.renovay.world.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ContainerScreenEvent;
import net.rodmjorgeh.renovay.RenovayMod;

import java.util.List;

public class ReedFluteItem extends Item {
    private static final int COOLDOWN = 10;

    public ReedFluteItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return Mth.floor(this.getCooldown() / 3F);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        player.startUsingItem(hand);
        player.getCooldowns().addCooldown(item, this.getCooldown()); //10s * 20 ticks

        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        this.finishUsingItem(stack, level, entity);
        return true;
    }

    /**
     * Small rant, but when you make a mob stop being angry via the {@code stopBeingAngry()} function, you also need to
     * set targets inside the available goals of the entity, for some reason. Kinda annoying.
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
            player.awardStat(Stats.ITEM_USED.get(this));

            if (level instanceof ServerLevel) {
                Vec3 playerPos = player.position();
                level.getEntitiesOfClass(
                                Mob.class,
                                new AABB(playerPos.x - 5.0F, playerPos.y - 5.0F, playerPos.z - 5.0F, playerPos.x + 5.0F, playerPos.y + 5.0F, playerPos.z + 5.0F),
                                EntitySelector.ENTITY_STILL_ALIVE
                        ).stream()
                        .filter(x -> x instanceof NeutralMob)
                        .forEach(x -> {
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
            }
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

    private int getCooldown() {
        return COOLDOWN * 20;
    }
}
