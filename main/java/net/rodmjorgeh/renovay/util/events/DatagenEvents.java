package net.rodmjorgeh.renovay.util.events;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rodmjorgeh.renovay.data.BuilderRegistries;

public class DatagenEvents {

    public static void onGatherDataClient(GatherDataEvent.Client event) {
        event.createProvider(BuilderRegistries::new);
    }
}
