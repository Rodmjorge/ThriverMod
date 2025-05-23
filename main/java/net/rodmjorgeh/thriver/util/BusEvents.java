package net.rodmjorgeh.thriver.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rodmjorgeh.thriver.client.data.models.BlockModelDataGenerator;
import net.rodmjorgeh.thriver.client.renderer.EntityRendererReg;
import net.rodmjorgeh.thriver.client.renderer.blockentity.BlockEntityRendererReg;
import net.rodmjorgeh.thriver.world.area.GameRuleReg;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.entity.BlockEntityAdderReg;
import net.rodmjorgeh.thriver.world.area.block.state.properties.WoodTypeThr;

public class BusEvents {

    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityAdderReg.bootstrap();
            GameRuleReg.registerAll();

            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(BlockReg.PALM_LOG.get(), BlockReg.STRIPPED_PALM_LOG.get())
                    .put(BlockReg.PALM_WOOD.get(), BlockReg.STRIPPED_PALM_WOOD.get())
                    .build();

            FireBlock fb = (FireBlock)Blocks.FIRE;
            fb.setFlammable(BlockReg.PALM_LOG.get(), 5, 5);
            fb.setFlammable(BlockReg.PALM_WOOD.get(), 5, 5);
            fb.setFlammable(BlockReg.STRIPPED_PALM_LOG.get(), 5, 5);
            fb.setFlammable(BlockReg.STRIPPED_PALM_WOOD.get(), 5, 5);
            fb.setFlammable(BlockReg.PALM_PLANKS.get(), 5, 20);
            fb.setFlammable(BlockReg.PALM_SLAB.get(), 5, 20);
            fb.setFlammable(BlockReg.PALM_STAIRS.get(), 5, 20);
            fb.setFlammable(BlockReg.PALM_FENCE.get(), 5, 20);
            fb.setFlammable(BlockReg.PALM_FENCE_GATE.get(), 5, 20);
            fb.setFlammable(BlockReg.PALM_LEAVES.get(), 30, 60);
        });
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRendererReg.registerAll();
            BlockEntityRendererReg.registerAll();
            BlockModelDataGenerator.setRenderTypes();

            Sheets.addWoodType(WoodTypeThr.PALM);
        });
    }
}
