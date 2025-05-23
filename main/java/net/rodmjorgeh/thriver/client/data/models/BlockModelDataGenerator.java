package net.rodmjorgeh.thriver.client.data.models;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.rodmjorgeh.thriver.data.BlockFamilyReg;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.CoconutBlock;
import net.rodmjorgeh.thriver.world.area.block.TallReedBlock;
import net.rodmjorgeh.thriver.world.area.block.state.properties.BlockStatePropertyReg;
import net.rodmjorgeh.thriver.world.area.block.state.properties.TripleBlockStep;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import net.rodmjorgeh.thriver.world.item.ItemReg;

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
        BlockFamilyReg.getCustomFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(x -> this.generator.family(x.getBaseBlock()).generateFor(x));

        this.generator.createBanner(BlockReg.BEIGE_BANNER.get(), BlockReg.BEIGE_WALL_BANNER.get(), DyeColorThr.BEIGE);
        this.generator.createBed(BlockReg.BEIGE_BED.get(), BlockReg.BEIGE_WOOL.get(), DyeColorThr.BEIGE);
        this.generator.createCandleAndCandleCake(BlockReg.BEIGE_CANDLE.get(), BlockReg.BEIGE_CANDLE_CAKE.get());
        this.generator.createTrivialCube(BlockReg.BEIGE_CONCRETE.get());
        this.generator.createColoredBlockWithRandomRotations(TexturedModel.CUBE, BlockReg.BEIGE_CONCRETE_POWDER.get());
        this.generator.createColoredBlockWithStateRotations(TexturedModel.GLAZED_TERRACOTTA, BlockReg.BEIGE_GLAZED_TERRACOTTA.get());
        this.generator.createGlassBlocks(BlockReg.BEIGE_STAINED_GLASS.get(), BlockReg.BEIGE_STAINED_GLASS_PANE.get());
        this.generator.createTrivialCube(BlockReg.BEIGE_TERRACOTTA.get());
        this.generator.createFullAndCarpetBlocks(BlockReg.BEIGE_WOOL.get(), BlockReg.BEIGE_CARPET.get());
        this.generator.createShulkerBox(BlockReg.BEIGE_SHULKER_BOX.get(), DyeColorThr.BEIGE);
        this.createCoconut();
        this.createHorizontalFacingWithoutDataModel(BlockReg.COIR_MAT.get());
        this.generator.createTrivialCube(BlockReg.CRACKED_MUD_BRICKS.get());
        this.generator.createCrossBlock(BlockReg.DOLLS_EYES_CROP.get(), BlockModelGenerators.PlantType.NOT_TINTED, BlockStateProperties.AGE_2, 0, 1, 2);
        this.generator.createPlantWithDefaultItem(BlockReg.DOLLS_EYES.get(), BlockReg.POTTED_DOLLS_EYES.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.generator.woodProvider(BlockReg.PALM_LOG.get())
                .logWithHorizontal(BlockReg.PALM_LOG.get())
                .wood(BlockReg.PALM_WOOD.get());
        this.generator.woodProvider(BlockReg.STRIPPED_PALM_LOG.get())
                .logWithHorizontal(BlockReg.STRIPPED_PALM_LOG.get())
                .wood(BlockReg.STRIPPED_PALM_WOOD.get());
        this.generator.createHangingSign(BlockReg.STRIPPED_PALM_LOG.get(),
                BlockReg.PALM_HANGING_SIGN.get(), BlockReg.PALM_WALL_HANGING_SIGN.get());
        this.generator.createTrivialCube(BlockReg.PALM_LEAVES.get());
        this.generator.createPlantWithDefaultItem(BlockReg.PALM_SPROUT.get(), BlockReg.POTTED_PALM_SPROUT.get(),
                BlockModelGenerators.PlantType.NOT_TINTED);
        this.generator.createDoublePlantWithDefaultItem(BlockReg.REEDS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.generator.createTrivialCube(BlockReg.SILT_MUD.get());
        this.createTallReeds();

        this.updateFarmland();
    }

    private void createNewTexturedModels() {
        this.generator.texturedModels = new ImmutableMap.Builder<Block, TexturedModel>()
                .putAll(this.generator.texturedModels)
                .put(
                        BlockReg.SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN_WITH_WALL.get(Blocks.SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockReg.SANDSTONE_BRICKS.get())))
                                .updateTextures(m ->
                                        m.put(TextureSlot.WALL, TextureMapping.getBlockTexture(BlockReg.SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockReg.CRACKED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN.get(Blocks.SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockReg.CRACKED_SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockReg.RED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN_WITH_WALL.get(Blocks.RED_SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockReg.RED_SANDSTONE_BRICKS.get())))
                                .updateTextures(m ->
                                        m.put(TextureSlot.WALL, TextureMapping.getBlockTexture(BlockReg.RED_SANDSTONE_BRICKS.get())))
                )
                .put(
                        BlockReg.CRACKED_RED_SANDSTONE_BRICKS.get(),
                        TexturedModel.COLUMN.get(Blocks.RED_SANDSTONE)
                                .updateTextures(m ->
                                        m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockReg.CRACKED_RED_SANDSTONE_BRICKS.get())))
                )
                .build();
    }

    public void register() {
        createNewTexturedModels();
        registerModels();
    }

    public static void setRenderTypes() {
        RenderType cutout = RenderType.CUTOUT;
        RenderType translucent = RenderType.TRANSLUCENT;

        ItemBlockRenderTypes.setRenderLayer(BlockReg.REEDS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.TALL_REEDS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.PALM_SPROUT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.POTTED_PALM_SPROUT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.DOLLS_EYES.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.DOLLS_EYES_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.POTTED_DOLLS_EYES.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.BEIGE_STAINED_GLASS.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(BlockReg.BEIGE_STAINED_GLASS_PANE.get(), translucent);
    }

    private void createHorizontalFacingWithoutDataModel(Block block) {
        this.stateProvider.accept(
                MultiVariantGenerator.multiVariant(block,
                                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block)))
                        .with(BlockModelGenerators.createHorizontalFacingDispatchAlt())
        );
    }

    private void createCoconut() {
        Block block = BlockReg.COCONUT.get();

        this.generator.registerSimpleFlatItemModel(ItemReg.COCONUT.get());
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
        Block block = BlockReg.TALL_REEDS.get();

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
                                PropertyDispatch.properties(FarmBlock.MOISTURE, BlockStatePropertyReg.SILTY)
                                        .generate(
                                                (moisture, isSilty) -> (moisture == 7)
                                                        ? Variant.variant().with(VariantProperties.MODEL, isSilty ? silty : moistured)
                                                        : Variant.variant().with(VariantProperties.MODEL, notMoistured)
                                        )
                        )

        );
    }
}
