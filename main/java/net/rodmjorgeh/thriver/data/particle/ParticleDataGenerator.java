package net.rodmjorgeh.thriver.data.particle;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.area.particles.ParticleReg;

public class ParticleDataGenerator extends ParticleDescriptionProvider {

    public ParticleDataGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        this.spriteSet(ParticleReg.PALM_LEAVES.get(), ResourceMod.createLoc("palm"), 12, false);
    }
}
