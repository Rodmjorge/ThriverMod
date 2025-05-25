package net.rodmjorgeh.thriver.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

import java.util.function.Supplier;

public class SoundEventReg {

    public static final DeferredRegister<SoundEvent> SOUND =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ThriverMod.MOD_ID);

    public static final Supplier<SoundEvent> REED_FLUTE_PLAY = register("item.reed_flute.play");


    private static Supplier<SoundEvent> register(String name) {
        ResourceLocation loc = ResourceMod.createLoc(name);
        return SOUND.register(name, () -> SoundEvent.createVariableRangeEvent(loc));
    }

    public static void register(IEventBus bus) { SOUND.register(bus); }
}
