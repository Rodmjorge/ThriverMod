package net.rodmjorgeh.renovay.client.gui.screens.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.RenovayMod;
import org.joml.Vector3f;

public class CoirMatEditScreen extends AbstractSignEditScreen {

    private static final Vector3f TEXT_SCALE = new Vector3f(1.1F, 1.1F, 1.1F);
    private final ResourceLocation COIR_MAT_TEXTURE = RenovayMod.createLoc("textures/block/coir_mat.png");

    public CoirMatEditScreen(SignBlockEntity blockEntity, boolean isFrontText, boolean isFiltered) {
        super(blockEntity, isFrontText, isFiltered, Component.translatable("coir_mat.edit"));
    }

    @Override
    protected void offsetSign(GuiGraphics guiGraphics, BlockState state) {
        PoseStack pose = guiGraphics.pose();

        pose.translate((float)this.width / 2.0F, 105.0F, 50.0F);
        pose.scale(1.2F, 1.2F, 1.0F);
    }

    @Override
    protected void renderSignBackground(GuiGraphics guiGraphics) {
        PoseStack pose = guiGraphics.pose();

        pose.translate(-42.0F, -44.0F, 0.0F);
        pose.scale(5.2F, 5.2F, 1.0F);
        guiGraphics.blit(RenderType::guiTextured, this.COIR_MAT_TEXTURE, 0, 0, 0.0F, 0.0F, 16, 16, 16, 16);
    }

    @Override
    protected Vector3f getSignTextScale() { return TEXT_SCALE; }
}
