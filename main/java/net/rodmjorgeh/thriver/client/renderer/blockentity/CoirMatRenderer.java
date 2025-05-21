package net.rodmjorgeh.thriver.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.rodmjorgeh.thriver.world.area.block.CoirMatBlock;

public class CoirMatRenderer extends AbstractSignRenderer {

    private Direction blockFacingDirection;

    public CoirMatRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SignBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int lightColor, int overlayTexture) {
        this.blockFacingDirection = blockEntity.getBlockState().getValue(CoirMatBlock.FACING);
        this.renderSignText(blockEntity.getBlockPos(), blockEntity.getText(true), poseStack, bufferSource, lightColor,
                blockEntity.getTextLineHeight(), blockEntity.getMaxTextLineWidth(), true);
    }

    @Override
    public void translateSignText(PoseStack poseStack, boolean isFront, Vec3 offset) {
        poseStack.translate(new Vec3(0.5F, 0.065F, 0.52083333F));
        poseStack.mulPose(Axis.YP.rotationDegrees(this.blockFacingDirection.get2DDataValue() * -90F));
        poseStack.mulPose(Axis.XP.rotationDegrees(-90F));
        super.translateSignText(poseStack, isFront, offset);
    }


    @Override
    protected float getSignTextRenderScale() { return 0.833333333F; }

    @Override
    protected Vec3 getTextOffset() {
        return new Vec3(0.0F, 0.0F, 0.0F);
    }

    /**
     * Here's a bunch of shit we don't need. The model is given via the model file (in .json) so it renders that way,
     * as opposed to how signs are rendered, which is inside the code, for... some reason.
     * <p>We also don't need the material of the coir mat because it's not made from wood. Basic stuff.
     */
    @Override
    protected Model getSignModel(BlockState state, WoodType woodType) { return null; }

    @Override
    protected Material getSignMaterial(WoodType woodType) { return null; }

    @Override
    protected float getSignModelRenderScale() { return 0; }

    @Override
    protected void translateSign(PoseStack poseStack, float yRot, BlockState state) { }

}
