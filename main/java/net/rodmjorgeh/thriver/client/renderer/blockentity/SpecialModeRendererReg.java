package net.rodmjorgeh.thriver.client.renderer.blockentity;

import net.minecraft.client.renderer.special.*;
import net.neoforged.neoforge.client.event.RegisterSpecialBlockModelRendererEvent;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.state.properties.WoodTypeThr;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;

public class SpecialModeRendererReg {

    public static void register(final RegisterSpecialBlockModelRendererEvent event) {
        event.register(BlockReg.BEIGE_BANNER.get(), new BannerSpecialRenderer.Unbaked(DyeColorThr.BEIGE));
        event.register(BlockReg.BEIGE_WALL_BANNER.get(), new BannerSpecialRenderer.Unbaked(DyeColorThr.BEIGE));
        event.register(BlockReg.BEIGE_BED.get(), new BedSpecialRenderer.Unbaked(DyeColorThr.BEIGE));
        event.register(BlockReg.BEIGE_SHULKER_BOX.get(), new ShulkerBoxSpecialRenderer.Unbaked(DyeColorThr.BEIGE));
        event.register(BlockReg.PALM_SIGN.get(), new StandingSignSpecialRenderer.Unbaked(WoodTypeThr.PALM));
        event.register(BlockReg.PALM_WALL_SIGN.get(), new StandingSignSpecialRenderer.Unbaked(WoodTypeThr.PALM));
        event.register(BlockReg.PALM_HANGING_SIGN.get(), new HangingSignSpecialRenderer.Unbaked(WoodTypeThr.PALM));
        event.register(BlockReg.PALM_WALL_HANGING_SIGN.get(), new HangingSignSpecialRenderer.Unbaked(WoodTypeThr.PALM));
    }
}
