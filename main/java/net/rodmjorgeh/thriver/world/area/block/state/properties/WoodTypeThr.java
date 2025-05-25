package net.rodmjorgeh.thriver.world.area.block.state.properties;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

public final class WoodTypeThr {

    public static final WoodType PALM = WoodType.register(new WoodType(ResourceMod.createStringLoc("palm"), BlockSetType.OAK));
}
