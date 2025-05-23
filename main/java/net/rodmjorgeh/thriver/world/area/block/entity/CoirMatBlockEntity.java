package net.rodmjorgeh.thriver.world.area.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;

public class CoirMatBlockEntity extends SignBlockEntity {

    public CoirMatBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityReg.COIR_MAT.get(), pos, state);
    }

    /**
     * You can only set text on the top of the coir mat, so the only text you'll get access to is the front. (or, in
     * this case, the text on top)
     */
    @Override
    public SignText getText(boolean isFrontText) { return super.getText(true); }
    @Override
    public boolean setText(SignText text, boolean isFrontText) { return super.setText(text, true); }

    @Override
    public int getMaxTextLineWidth() {
        return 65;
    }

    @Override
    protected SignText createDefaultSignText() {
        return new SignText(SignText.emptyMessages(), SignText.emptyMessages(), DyeColor.BROWN, false);
    }
}
