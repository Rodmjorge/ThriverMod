package net.rodmjorgeh.renovay.world.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

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

        player.awardStat(Stats.ITEM_USED.get(this));
        player.startUsingItem(hand);
        player.getCooldowns().addCooldown(item, this.getCooldown()); //10s * 20 ticks

        return InteractionResult.CONSUME;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    private int getCooldown() {
        return COOLDOWN * 20;
    }
}
