package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(ShulkerBoxBlock.class)
public class IShulkerBoxBlock {

    @Inject(method = "getBlockByColor", at = @At("HEAD"), cancellable = true)
    private static void getBlockByColor(@Nullable DyeColor color, CallbackInfoReturnable<Block> cir) {
        if (color != null) {
            if (color.equals(DyeColorThr.BEIGE) && BlockReg.BEIGE_SHULKER_BOX.isBound()) { cir.setReturnValue(BlockReg.BEIGE_SHULKER_BOX.get()); }

            else { cir.setReturnValue(Blocks.SHULKER_BOX); }
        }
    }
}
