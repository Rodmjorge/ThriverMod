package net.rodmjorgeh.renovay.data;

import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rodmjorgeh.renovay.client.data.BuilderRegistries;
import net.rodmjorgeh.renovay.data.loot.GlobalLootDataGenerator;
import net.rodmjorgeh.renovay.data.loot.LootDataGenerator;
import net.rodmjorgeh.renovay.client.data.models.ModelDataGenerator;
import net.rodmjorgeh.renovay.data.recipe.RecipeDataGenerator;

import java.util.Set;

public class DatagenEvents {

    public static void onGatherDataClient(GatherDataEvent.Client event) {
        event.createProvider(BuilderRegistries::new);
        event.createProvider(ModelDataGenerator::new);
        event.createProvider(LootDataGenerator::new);
        event.createProvider(GlobalLootDataGenerator::new);
        event.createProvider(RecipeDataGenerator.Runner::new);
    }
}
