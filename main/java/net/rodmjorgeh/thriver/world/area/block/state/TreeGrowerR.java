package net.rodmjorgeh.thriver.world.area.block.state;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.worldgen.features.TreeFeaturesRegistry;

import java.util.Optional;

public class TreeGrowerR {

    public static final TreeGrower PALM = new TreeGrower(ThriverMod.name("palm"),
            Optional.empty(),
            Optional.of(TreeFeaturesRegistry.PALM_TREE),
            Optional.empty());
}
