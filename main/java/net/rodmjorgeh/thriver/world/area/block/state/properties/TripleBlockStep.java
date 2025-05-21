package net.rodmjorgeh.thriver.world.area.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum TripleBlockStep implements StringRepresentable {
    BOTTOM("bottom"),
    MIDDLE("middle"),
    TOP("top");

    private final String name;

    private TripleBlockStep(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
