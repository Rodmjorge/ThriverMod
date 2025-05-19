package net.rodmjorgeh.renovay.plus;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(Shulker.class)
public class ShulkerP {

    @Shadow @Final
    protected static EntityDataAccessor<Byte> DATA_COLOR_ID;

    @Inject(method = "getColor", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void getColor(CallbackInfoReturnable<DyeColor> cir, byte b0) {
        cir.setReturnValue(b0 != 64 && b0 <= 63 ? DyeColor.byId(b0) : null);
    }

    @Inject(method = "defineSynchedData", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/network/syncher/SynchedEntityData$Builder;define(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)Lnet/minecraft/network/syncher/SynchedEntityData$Builder;",
            ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    public void defineNewSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_COLOR_ID, (byte)64);
        ci.cancel();
    }

    @Inject(method = "setVariant(Ljava/util/Optional;)V", at = @At("HEAD"), cancellable = true)
    public void setVariant(Optional<DyeColor> variant, CallbackInfo ci) {
        Shulker instance = (Shulker)(Object)this;

        instance.getEntityData().set(DATA_COLOR_ID, variant.map((x) -> (byte)x.getId()).orElse((byte)64));
        ci.cancel();
    }
}
