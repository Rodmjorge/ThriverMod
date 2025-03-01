package net.rodmjorgeh.renovay.world.area.block.state;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.worldgen.features.TreeFeaturesRegistry;

import java.util.Optional;

public class TreeGrowerR {

    public static final TreeGrower PALM = new TreeGrower(RenovayMod.name("palm"),
            Optional.empty(),
            Optional.of(TreeFeaturesRegistry.PALM_TREE),
            Optional.empty());
}
