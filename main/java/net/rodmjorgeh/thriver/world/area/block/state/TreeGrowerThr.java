package net.rodmjorgeh.thriver.world.area.block.state;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.worldgen.features.TreeFeaturesReg;

import java.util.Optional;

public class TreeGrowerThr {

    public static final TreeGrower PALM = new TreeGrower(ThriverMod.createStringLoc("palm"),
            Optional.empty(),
            Optional.of(TreeFeaturesReg.PALM_TREE),
            Optional.empty());
}
