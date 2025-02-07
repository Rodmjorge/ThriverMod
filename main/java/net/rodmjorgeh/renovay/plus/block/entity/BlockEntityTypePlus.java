package net.rodmjorgeh.renovay.plus.block.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Mixin(BlockEntityType.class)
public class BlockEntityTypePlus {

    /**
     * Injects code into {@code BlockEntityType.isValid()}, which is a method that checks if a {@code Block} is registered
     * into a certain {@code BlockEntity}.
     * Problem is, we can't add it directly to the register, but since it only ever checks it via this method, we can just
     * inject code so it also includes our new blocks.
     *
     * <p>No need to register new renderers either, since in {@code BlockEntityRenderers} it's already registering the
     * {@code BlockEntityType} we're adding new blocks on.
     */
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void isValid(BlockState pState, CallbackInfoReturnable<Boolean> cir) {
        BlockEntityType instance = (BlockEntityType)(Object)this;
        Set<Block> validBlocks = instance.validBlocks;
        List<Block> newValidBlocks = new ArrayList<>();

        // check if we are in the BlockEntityType instance where its validBlocks are SignBlockEntity
        if (validBlocks.contains(Blocks.OAK_SIGN)) {
            newValidBlocks.addAll(Arrays.asList(
                    BlockRegistry.PALM_SIGN.get(),
                    BlockRegistry.PALM_WALL_SIGN.get()
            ));
        }
        // check if we are in the BlockEntityType instance where its validBlocks are HangingSignBlockEntity
        else if (validBlocks.contains(Blocks.OAK_HANGING_SIGN)) {
            newValidBlocks.addAll(Arrays.asList(
                    BlockRegistry.PALM_HANGING_SIGN.get(),
                    BlockRegistry.PALM_WALL_HANGING_SIGN.get()
            ));
        }

        cir.setReturnValue(validBlocks.contains(pState.getBlock()) || newValidBlocks.contains(pState.getBlock()));
    }
}
