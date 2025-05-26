package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Sheep.class)
public class SheepMxn {

    @Shadow @Final private static EntityDataAccessor<Byte> DATA_WOOL_ID;

    /**
     * Sets the new maximum of colors that the game can handle. A lot of it is hardcoded to have 16 instead of having
     * a simple constant variable with a value like I do, for... some reason.
     */
    private static byte NEW_AMOUNT = 32;

    @ModifyConstant(method = "getColor()Lnet/minecraft/world/item/DyeColor;", constant = @Constant(intValue = 15))
    public int getColorConstantValue(int i) {
        return NEW_AMOUNT - 1;
    }

    @Inject(method = "setColor", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/network/syncher/SynchedEntityData;set(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void setColor(DyeColor color, CallbackInfo ci, byte b0) {
        Sheep sheep = (Sheep)(Object)this;
        sheep.getEntityData().set(DATA_WOOL_ID, (byte)(b0 & 255 - (NEW_AMOUNT - 1) | color.getId() & (NEW_AMOUNT - 1)));
        ci.cancel();
    }

    @ModifyConstant(method = "isSheared", constant = @Constant(intValue = 16))
    public int isShearedConstantValue(int i) {
        return NEW_AMOUNT;
    }

    @Inject(method = "setSheared", at = @At(value = "HEAD"), cancellable = true)
    public void setSheared(boolean sheared, CallbackInfo ci) {
        SynchedEntityData entityData = ((Sheep)(Object)this).getEntityData();
        byte b0 = entityData.get(DATA_WOOL_ID);

        //sets the first byte next to the 5 bytes used (32) for if it's sheared or not
        if (sheared) {
            entityData.set(DATA_WOOL_ID, (byte)(b0 | NEW_AMOUNT));
        } else {
            entityData.set(DATA_WOOL_ID, (byte)(b0 & ~NEW_AMOUNT));
        }

        ci.cancel();
    }
}
