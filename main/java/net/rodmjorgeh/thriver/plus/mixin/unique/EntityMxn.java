package net.rodmjorgeh.thriver.plus.mixin.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public class EntityMxn implements EntityAdd {

    /**
     * A lot of this mess is just to make the Doll's Eyes blindness effect working. It takes inspiration from the Powder
     * Snow block, which also causes something to happen after a certain time has passed.
     */
    @Unique private boolean isInDollsEyes;
    @Unique private boolean wasInDollsEyes;
    @Unique private static final EntityDataAccessor<Integer> DATA_TICKS_TO_BLIND = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.INT);
    @Unique private static final String DATA_TICKS_TO_BLIND_TEXT = "TicksToBlind";

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/syncher/SynchedEntityData$Builder;define(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)Lnet/minecraft/network/syncher/SynchedEntityData$Builder;",
        ordinal = 7, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void EntityInit(EntityType<?> entityType, Level level, CallbackInfo ci, SynchedEntityData.Builder builder) {
        builder.define(DATA_TICKS_TO_BLIND, 5);
    }

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateInWaterStateAndDoFluidPushing()Z"))
    public void baseTick(CallbackInfo ci) {
        this.wasInDollsEyes = this.isInDollsEyes;
        this.isInDollsEyes = false;
    }


    @Inject(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getTicksFrozen()I", ordinal = 0))
    public void saveWithoutId(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        int i = this.getTicksToBlind();
        if (i > 0) {
            compound.putInt(DATA_TICKS_TO_BLIND_TEXT, i);
        }
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;setTicksFrozen(I)V", shift = At.Shift.AFTER))
    public void load(CompoundTag compound, CallbackInfo ci) {
        this.setTicksToBlind(compound.getInt(DATA_TICKS_TO_BLIND_TEXT));
    }

    @Override
    public int getTicksToBlind() {
        return ((Entity)(Object)this).getEntityData().get(DATA_TICKS_TO_BLIND);
    }

    @Override
    public void setTicksToBlind(int ticksToBlind) {
        ((Entity)(Object)this).getEntityData().set(DATA_TICKS_TO_BLIND, ticksToBlind);
    }


    @Override
    public void setIsInDollsEyes(boolean isInDollsEyes) {
        this.isInDollsEyes = isInDollsEyes;
    }

    @Override
    public boolean getIsInDollsEyes() {
        return this.isInDollsEyes || this.wasInDollsEyes;
    }
}
