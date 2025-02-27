package net.rodmjorgeh.renovay.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.world.area.block.CoirMatBlock;
import net.rodmjorgeh.renovay.world.area.block.entity.CoirMatBlockEntity;

import javax.annotation.Nullable;

public class CoirMatItem extends BlockItem {

    public CoirMatItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState state) {
        boolean flag = super.updateCustomBlockEntityTag(pos, level, player, stack, state);

        if (!level.isClientSide && !flag && player != null
                && level.getBlockEntity(pos) instanceof CoirMatBlockEntity blockEntity
                && level.getBlockState(pos).getBlock() instanceof CoirMatBlock coirMatBlock) {
            coirMatBlock.openTextEdit(player, blockEntity, true);
        }

        return flag;
    }
}
