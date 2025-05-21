package net.rodmjorgeh.thriver.data;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rodmjorgeh.thriver.client.data.BuilderRegistries;
import net.rodmjorgeh.thriver.client.data.models.EquipmentAssetDataGenerator;
import net.rodmjorgeh.thriver.data.advancements.AdvancementDataGenerator;
import net.rodmjorgeh.thriver.data.loot.GlobalLootDataGenerator;
import net.rodmjorgeh.thriver.data.loot.LootDataGenerator;
import net.rodmjorgeh.thriver.client.data.models.ModelDataGenerator;
import net.rodmjorgeh.thriver.data.recipe.RecipeDataGenerator;
import net.rodmjorgeh.thriver.data.tags.BlockTagDataGenerator;
import net.rodmjorgeh.thriver.data.tags.EntityTypeTagDataGenerator;
import net.rodmjorgeh.thriver.data.tags.ItemTagDataGenerator;

public class DatagenEvents {

    public static void onGatherDataClient(GatherDataEvent.Client event) {
        event.createProvider(BuilderRegistries::new);
        event.createProvider(ModelDataGenerator::new);
        event.createProvider(EquipmentAssetDataGenerator::new);
        event.createProvider(LootDataGenerator::new);
        event.createProvider(GlobalLootDataGenerator::new);
        event.createProvider(RecipeDataGenerator.Runner::new);
        event.createProvider(AdvancementDataGenerator::new);

        TagsProvider<Block> blockTags = event.createProvider(BlockTagDataGenerator::new);
        event.createProvider(EntityTypeTagDataGenerator::new);
        event.createProvider((output, future) -> new ItemTagDataGenerator(output, future, blockTags.contentsGetter()));
    }
}
