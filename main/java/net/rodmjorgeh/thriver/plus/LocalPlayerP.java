package net.rodmjorgeh.thriver.plus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.rodmjorgeh.thriver.client.gui.CoirMatEditScreen;
import net.rodmjorgeh.thriver.world.area.block.entity.CoirMatBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerP {

    @Inject(method = "openTextEdit", at = @At("HEAD"), cancellable = true)
    public void openTextEdit(SignBlockEntity blockEntity, boolean isFrontText, CallbackInfo ci) {
        Minecraft instance = Minecraft.getInstance();

        if (blockEntity instanceof CoirMatBlockEntity) {
            instance.setScreen(new CoirMatEditScreen(blockEntity, true, instance.isTextFilteringEnabled()));
            ci.cancel();
        }
    }
}
