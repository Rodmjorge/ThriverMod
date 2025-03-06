package net.rodmjorgeh.renovay.data;

import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rodmjorgeh.renovay.client.data.BuilderRegistries;
import net.rodmjorgeh.renovay.data.loot.GlobalLootDataGenerator;
import net.rodmjorgeh.renovay.data.loot.LootDataGenerator;
import net.rodmjorgeh.renovay.client.data.models.ModelDataGenerator;
import net.rodmjorgeh.renovay.data.recipe.RecipeDataGenerator;
import net.rodmjorgeh.renovay.data.tags.BlockTagDataGenerator;
import net.rodmjorgeh.renovay.data.tags.EntityTypeTagDataGenerator;
import net.rodmjorgeh.renovay.data.tags.ItemTagDataGenerator;

import java.util.Set;

public class DatagenEvents {

    public static void onGatherDataClient(GatherDataEvent.Client event) {
        event.createProvider(BuilderRegistries::new);
        event.createProvider(ModelDataGenerator::new);
        event.createProvider(LootDataGenerator::new);
        event.createProvider(GlobalLootDataGenerator::new);
        event.createProvider(RecipeDataGenerator.Runner::new);

        TagsProvider<Block> blockTags = event.createProvider(BlockTagDataGenerator::new);
        event.createProvider(EntityTypeTagDataGenerator::new);
        event.createProvider((output, future) -> new ItemTagDataGenerator(output, future, blockTags.contentsGetter()));
    }
}
