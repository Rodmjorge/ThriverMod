package net.rodmjorgeh.renovay.client.data.models;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.rodmjorgeh.renovay.data.BlockFamilyRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.block.CoconutBlock;
import net.rodmjorgeh.renovay.world.area.block.TallReedBlock;
import net.rodmjorgeh.renovay.world.area.block.state.properties.BlockStatePropertyRegistry;
import net.rodmjorgeh.renovay.world.area.block.state.properties.TripleBlockStep;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BlockModelDataGenerator {

    private final BlockModelGenerators generator;
    private final Consumer<BlockStateGenerator> stateProvider;
    private final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public BlockModelDataGenerator(BlockModelGenerators generator) {
        this.generator = generator;
        this.stateProvider = generator.blockStateOutput;
        this.modelOutput = generator.modelOutput;
    }

    private void registerModels() {
        BlockFamilyRegistry.getCustomFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(x -> this.generator.family(x.getBaseBlock()).generateFor(x));

        this.createCoconut();
        this.createHorizontalFacingWithoutDataModel(BlockRegistry.COIR_MAT.get());
        this.generator.createTrivialCube(BlockRegistry.CRACKED_MUD_BRICKS.get());
        this.generator.woodProvider(BlockRegistry.PALM_LOG.get())
                .logWithHorizontal(BlockRegistry.PALM_LOG.get())
                .wood(BlockRegistry.PALM_WOOD.get());
        this.generator.woodProvider(BlockRegistry.STRIPPED_PALM_LOG.get())
                .logWithHorizontal(BlockRegistry.STRIPPED_PALM_LOG.get())
                .wood(BlockRegistry.STRIPPED_PALM_WOOD.get());
        this.generator.createHangingSign(BlockRegistry.STRIPPED_PALM_LOG.get(),
                BlockRegistry.PALM_HANGING_SIGN.get(), BlockRegistry.PALM_WALL_HANGING_SIGN.get());
        this.generator.createTrivialCube(BlockRegistry.PALM_LEAVES.get());
        this.generator.createPlantWithDefaultItem(BlockRegistry.PALM_SPROUT.get(), BlockRegistry.POTTED_PALM_SPROUT.get(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        this.generator.createDoublePlantWithDefaultItem(BlockRegistry.REEDS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.generator.createTrivialCube(BlockRegistry.SILT_MUD.get());
        this.createTallReeds();

        this.updateFarmland();
    }

    private void createNewTexturedModels() {
        this.generator.texturedModels = new ImmutableMap.Builder<Block, TexturedModel>()
                .putAll(this.generator.texturedModels)
                .put(
                        BlockRegistry.SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN_WITH_WALL.get(Blocks.SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockRegistry.CRACKED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN.get(Blocks.SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.CRACKED_SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockRegistry.RED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN_WITH_WALL.get(Blocks.RED_SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.RED_SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockRegistry.CRACKED_RED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN.get(Blocks.RED_SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.CRACKED_RED_SANDSTONE_BRICKS.get())))
                )
                .put(BlockRegistry.WEATHERED_SANDSTONE.get(), TexturedModel.TOP_BOTTOM_WITH_WALL.get(BlockRegistry.WEATHERED_SANDSTONE.get()))
                .put(
                        BlockRegistry.CUT_WEATHERED_SANDSTONE.get(),
                        TexturedModel.COLUMN.get(BlockRegistry.WEATHERED_SANDSTONE.get())
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.CUT_WEATHERED_SANDSTONE.get())))
                )
                .put(
                        BlockRegistry.CHISELED_WEATHERED_SANDSTONE.get(),
                        TexturedModel.COLUMN.get(BlockRegistry.WEATHERED_SANDSTONE.get())
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.CHISELED_WEATHERED_SANDSTONE.get())))
                )
                .put(BlockRegistry.SMOOTH_WEATHERED_SANDSTONE.get(),
                        TexturedModel.createAllSame(TextureMapping.getBlockTexture(BlockRegistry.WEATHERED_SANDSTONE.get(), "_top")))
                .put(
                        BlockRegistry.WEATHERED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN_WITH_WALL.get(BlockRegistry.WEATHERED_SANDSTONE.get())
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.WEATHERED_SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockRegistry.CRACKED_WEATHERED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN.get(BlockRegistry.WEATHERED_SANDSTONE.get())
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.CRACKED_WEATHERED_SANDSTONE_BRICKS.get())))
                )
                .build();
    }

    public void register() {
        createNewTexturedModels();
        registerModels();
    }

    private void createHorizontalFacingWithoutDataModel(Block block) {
        this.stateProvider.accept(
                MultiVariantGenerator.multiVariant(block,
                                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block)))
                        .with(BlockModelGenerators.createHorizontalFacingDispatchAlt())
        );
    }

    private void createCoconut() {
        Block block = BlockRegistry.COCONUT.get();

        this.generator.registerSimpleFlatItemModel(ItemRegistry.COCONUT.get());
        this.stateProvider.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(
                                PropertyDispatch.property(CoconutBlock.AGE)
                                        .generate(
                                                i -> Variant.variant()
                                                        .with(
                                                                VariantProperties.MODEL,
                                                                ModelLocationUtils.getModelLocation(block, "_stage" + i)
                                                        )
                                        )
                        )
        );
    }

    private void createTallReeds() {
        Block block = BlockRegistry.TALL_REEDS.get();

        ResourceLocation top = this.generator.createSuffixedVariant(block, "_top", ModelTemplates.CROSS, TextureMapping::cross);
        ResourceLocation middle = this.generator.createSuffixedVariant(block, "_middle", ModelTemplates.CROSS, TextureMapping::cross);
        ResourceLocation bottom = this.generator.createSuffixedVariant(block, "_bottom", ModelTemplates.CROSS, TextureMapping::cross);

        this.stateProvider.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(
                                PropertyDispatch.property(TallReedBlock.STEP)
                                        .select(TripleBlockStep.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bottom))
                                        .select(TripleBlockStep.MIDDLE, Variant.variant().with(VariantProperties.MODEL, middle))
                                        .select(TripleBlockStep.TOP, Variant.variant().with(VariantProperties.MODEL, top))
                        )
        );
    }


    private void updateFarmland() {
        Block block = Blocks.FARMLAND;
        ResourceLocation notMoistured = ModelLocationUtils.getModelLocation(block);
        ResourceLocation moistured = ModelLocationUtils.getModelLocation(block, "_moist");

        ResourceLocation siltyTextureName = ModelDataGenerator.useVanillaTextureWithNamespace(block, "_moist_silt");
        TextureMapping siltyTextureMap = new TextureMapping()
                .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(Blocks.DIRT))
                .put(TextureSlot.TOP, siltyTextureName);
        ResourceLocation silty = ModelTemplates.FARMLAND.create(siltyTextureName, siltyTextureMap, this.modelOutput);

        this.stateProvider.accept(
                MultiVariantGenerator.multiVariant(Blocks.FARMLAND)
                        .with(
                                PropertyDispatch.properties(FarmBlock.MOISTURE, BlockStatePropertyRegistry.SILTY)
                                        .generate(
                                                (moisture, isSilty) -> (moisture == 7)
                                                        ? Variant.variant().with(VariantProperties.MODEL, isSilty ? silty : moistured)
                                                        : Variant.variant().with(VariantProperties.MODEL, notMoistured)
                                        )
                        )

        );
    }
}
