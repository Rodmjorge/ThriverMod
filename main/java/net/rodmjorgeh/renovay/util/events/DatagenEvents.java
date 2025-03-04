package net.rodmjorgeh.renovay.util.events;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rodmjorgeh.renovay.client.data.BuilderRegistries;
import net.rodmjorgeh.renovay.client.data.models.ModelDataGenerator;

public class DatagenEvents {

    public static void onGatherDataClient(GatherDataEvent.Client event) {
        event.createProvider(BuilderRegistries::new);
        event.createProvider(ModelDataGenerator::new);
    }
}
