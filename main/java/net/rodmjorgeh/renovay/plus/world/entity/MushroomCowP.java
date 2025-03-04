package net.rodmjorgeh.renovay.plus.world.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(MushroomCow.class)
public class MushroomCowP {

    @Shadow @Nullable private SuspiciousStewEffects stewEffects;

    /**
     * This is something I actually didn't know: you can "milk" Brown Mooshrooms with a Bowl, and they'll give you
     * Mushroom Stew, and if you interact with them with a small flower it will give you a Suspicious Stew based on that
     * flower. Since I added my own Mushroom / Suspicious Stew items with the Coconut Bowl, I have to inject new
     * information into {@code mobInteract()} so it works with my Coconut Bowl.
     */
    @Inject(method = "mobInteract", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", shift = At.Shift.BEFORE, ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir, ItemStack stack) {
        MushroomCow cow = (MushroomCow)(Object)this;
        boolean isCoconut = stack.is(ItemRegistry.COCONUT_BOWL.get());

        if ((stack.is(Items.BOWL) || isCoconut) && !cow.isBaby()) {
            boolean flag = (stewEffects != null);
            ItemStack stack2;

            if (flag) {
                SuspiciousStewEffects.Entry effect = stewEffects.effects().getFirst();

                stack2 = new ItemStack(isCoconut ? ItemRegistry.COCONUT_SUSPICIOUS_STEW.get() : Items.SUSPICIOUS_STEW);
                stack2.set(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                        new SuspiciousStewEffects(List.of(
                                new SuspiciousStewEffects.Entry(effect.effect(), effect.duration() * (isCoconut ? 5 : 1)))));

                stewEffects = null;
            } else {
                stack2 = new ItemStack(isCoconut ? ItemRegistry.COCONUT_MUSHROOM_STEW.get() : Items.MUSHROOM_STEW);
            }

            ItemStack stack1 = ItemUtils.createFilledResult(stack, player, stack2, false);
            player.setItemInHand(hand, stack1);

            SoundEvent soundevent = flag ? SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY : SoundEvents.MOOSHROOM_MILK;
            cow.playSound(soundevent, 1.0F, 1.0F);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
