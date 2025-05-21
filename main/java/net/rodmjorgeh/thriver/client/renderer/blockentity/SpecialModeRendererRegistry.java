package net.rodmjorgeh.thriver.client.renderer.blockentity;

import net.minecraft.client.renderer.special.*;
import net.neoforged.neoforge.client.event.RegisterSpecialBlockModelRendererEvent;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.area.block.state.properties.WoodTypeR;
import net.rodmjorgeh.thriver.world.item.DyeColorR;

public class SpecialModeRendererRegistry {

    public static void register(final RegisterSpecialBlockModelRendererEvent event) {
        event.register(BlockRegistry.BEIGE_BANNER.get(), new BannerSpecialRenderer.Unbaked(DyeColorR.BEIGE));
        event.register(BlockRegistry.BEIGE_WALL_BANNER.get(), new BannerSpecialRenderer.Unbaked(DyeColorR.BEIGE));
        event.register(BlockRegistry.BEIGE_BED.get(), new BedSpecialRenderer.Unbaked(DyeColorR.BEIGE));
        event.register(BlockRegistry.BEIGE_SHULKER_BOX.get(), new ShulkerBoxSpecialRenderer.Unbaked(DyeColorR.BEIGE));
        event.register(BlockRegistry.PALM_SIGN.get(), new StandingSignSpecialRenderer.Unbaked(WoodTypeR.PALM));
        event.register(BlockRegistry.PALM_WALL_SIGN.get(), new StandingSignSpecialRenderer.Unbaked(WoodTypeR.PALM));
        event.register(BlockRegistry.PALM_HANGING_SIGN.get(), new HangingSignSpecialRenderer.Unbaked(WoodTypeR.PALM));
        event.register(BlockRegistry.PALM_WALL_HANGING_SIGN.get(), new HangingSignSpecialRenderer.Unbaked(WoodTypeR.PALM));
    }
}
