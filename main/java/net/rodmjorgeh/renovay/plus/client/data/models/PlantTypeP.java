package net.rodmjorgeh.renovay.plus.client.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BlockModelGenerators.PlantType.class)
public class PlantTypeP {

    @Shadow @Final @Mutable
    public static BlockModelGenerators.PlantType[] $VALUES;

    private static final BlockModelGenerators.PlantType NOT_TINTED_CUTOUT = addPlantType("NOT_TINTED_CUTOUT", 3,
            ModelTemplates.CROSS.extend().renderType("minecraft:cutout").build(),
            ModelTemplates.FLOWER_POT_CROSS.extend().renderType("minecraft:cutout").build(), false);

    @Invoker("<init>")
    public static BlockModelGenerators.PlantType plantType(String variableName, int id, ModelTemplate blockTemplate, ModelTemplate flowerPotTemplate, boolean isEmissive) {
        throw new AssertionError();
    }

    private static BlockModelGenerators.PlantType addPlantType(String variableName, int id, ModelTemplate blockTemplate, ModelTemplate flowerPotTemplate, boolean isEmissive) {
        ArrayList<BlockModelGenerators.PlantType> arr = new ArrayList<>(Arrays.asList($VALUES));

        BlockModelGenerators.PlantType plantType = plantType(variableName, id, blockTemplate, flowerPotTemplate, isEmissive);
        arr.add(plantType);
        $VALUES = arr.toArray(new BlockModelGenerators.PlantType[0]);

        return plantType;
    }
}
