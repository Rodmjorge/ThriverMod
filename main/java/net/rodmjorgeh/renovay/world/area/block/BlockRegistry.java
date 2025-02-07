package net.rodmjorgeh.renovay.world.area.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.block.state.TreeGrowerR;
import net.rodmjorgeh.renovay.world.area.block.state.WoodTypeR;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;
import net.rodmjorgeh.renovay.world.material.MapColorR;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RenovayMod.MOD_ID);


    public static final RegistryObject<Block> PALM_LOG = register("palm_log",
            () -> new RotatedPillarBlock(Blocks.logProperties(MapColor.COLOR_BROWN, MapColorR.PALM_TREE, SoundType.WOOD).setId(createId("palm_log"))),
            CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistryObject<Block> PALM_WOOD = register("palm_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).setId(createId("palm_wood"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PALM_LOG = register("stripped_palm_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG).mapColor(MapColorR.PALM_TREE).setId(createId("stripped_palm_log"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PALM_WOOD = register("stripped_palm_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_WOOD).mapColor(MapColorR.PALM_TREE).setId(createId("stripped_palm_wood"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_PLANKS = register("palm_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).mapColor(MapColorR.PALM_TREE).setId(createId("palm_planks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_SLAB = register("palm_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).mapColor(MapColorR.PALM_TREE).setId(createId("palm_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_STAIRS = register("palm_stairs",
            () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS).mapColor(MapColorR.PALM_TREE).setId(createId("palm_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_FENCE = register("palm_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE).mapColor(MapColorR.PALM_TREE).setId(createId("palm_fence"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_FENCE_GATE = register("palm_fence_gate",
            () -> new FenceGateBlock(WoodTypeR.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE).mapColor(MapColorR.PALM_TREE).setId(createId("palm_fence_gate"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_DOOR = register("palm_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR).mapColor(MapColorR.PALM_TREE).setId(createId("palm_door"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_TRAPDOOR = register("palm_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR).mapColor(MapColorR.PALM_TREE).setId(createId("palm_trapdoor"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_PRESSURE_PLATE = register("palm_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE).mapColor(MapColorR.PALM_TREE).setId(createId("palm_pressure_plate"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_BUTTON = register("palm_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, Blocks.buttonProperties().mapColor(MapColorR.PALM_TREE).setId(createId("palm_button"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> PALM_LEAVES = register("palm_leaves",
            () -> new LeavesBlock(Blocks.leavesProperties(SoundType.GRASS).setId(createId("palm_leaves"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistryObject<Block> PALM_SPROUT = register("palm_sprout",
            () -> new SaplingBlock(TreeGrowerR.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SAPLING).setId(createId("palm_sprout"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistryObject<Block> PALM_SIGN = register("palm_sign",
            () -> new StandingSignBlock(WoodTypeR.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SIGN).mapColor(MapColorR.PALM_TREE).setId(createId("palm_sign"))),
            false);
    public static final RegistryObject<Block> PALM_WALL_SIGN = register("palm_wall_sign",
            () -> new WallSignBlock(WoodTypeR.PALM, Blocks.wallVariant(PALM_SIGN.get(), true).ofFullCopy(Blocks.JUNGLE_WALL_SIGN).mapColor(MapColorR.PALM_TREE).setId(createId("palm_sign"))),
            false);
    public static final RegistryObject<Block> PALM_HANGING_SIGN = register("palm_hanging_sign",
            () -> new CeilingHangingSignBlock(WoodTypeR.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_HANGING_SIGN).mapColor(MapColorR.PALM_TREE).setId(createId("palm_hanging_sign"))),
            false);
    public static final RegistryObject<Block> PALM_WALL_HANGING_SIGN = register("palm_wall_hanging_sign",
            () -> new WallHangingSignBlock(WoodTypeR.PALM, Blocks.wallVariant(PALM_HANGING_SIGN.get(), true).ofFullCopy(Blocks.JUNGLE_WALL_HANGING_SIGN).mapColor(MapColorR.PALM_TREE).setId(createId("palm_wall_hanging_sign"))),
            false);


    public static final RegistryObject<Block> SANDSTONE_BRICKS = register("sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(createId("sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SANDSTONE_BRICK_SLAB = register("sandstone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).setId(createId("sandstone_brick_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SANDSTONE_BRICK_STAIRS = register("sandstone_brick_stairs",
            () -> new StairBlock(SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS).setId(createId("sandstone_brick_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SANDSTONE_BRICK_WALL = register("sandstone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_WALL).forceSolidOn().setId(createId("sandstone_brick_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRACKED_SANDSTONE_BRICKS = register("cracked_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SANDSTONE_BRICKS.get()).setId(createId("cracked_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);

    public static final RegistryObject<Block> RED_SANDSTONE_BRICKS = register("red_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE).setId(createId("red_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> RED_SANDSTONE_BRICK_SLAB = register("red_sandstone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB).setId(createId("red_sandstone_brick_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> RED_SANDSTONE_BRICK_STAIRS = register("red_sandstone_brick_stairs",
            () -> new StairBlock(RED_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_STAIRS).setId(createId("red_sandstone_brick_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> RED_SANDSTONE_BRICK_WALL = register("red_sandstone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_WALL).forceSolidOn().setId(createId("red_sandstone_brick_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRACKED_RED_SANDSTONE_BRICKS = register("cracked_red_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(RED_SANDSTONE_BRICKS.get()).setId(createId("cracked_red_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);

    public static final RegistryObject<Block> WEATHERED_SANDSTONE = register("weathered_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(createId("weathered_sandstone"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_WEATHERED_SANDSTONE = register("chiseled_weathered_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE).setId(createId("chiseled_weathered_sandstone"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_WEATHERED_SANDSTONE = register("cut_weathered_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE).setId(createId("cut_weathered_sandstone"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_WEATHERED_SANDSTONE = register("smooth_weathered_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE).setId(createId("smooth_weathered_sandstone"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_SLAB = register("weathered_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).setId(createId("weathered_sandstone_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_STAIRS = register("weathered_sandstone_stairs",
            () -> new StairBlock(WEATHERED_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS).setId(createId("weathered_sandstone_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_WALL = register("weathered_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_WALL).setId(createId("weathered_sandstone_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_WEATHERED_SANDSTONE_SLAB = register("cut_weathered_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB).setId(createId("cut_weathered_sandstone_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_WEATHERED_SANDSTONE_SLAB = register("smooth_weathered_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB).setId(createId("smooth_weathered_sandstone_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_WEATHERED_SANDSTONE_STAIRS = register("smooth_weathered_sandstone_stairs",
            () -> new StairBlock(SMOOTH_WEATHERED_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_STAIRS).setId(createId("smooth_weathered_sandstone_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_BRICKS = register("weathered_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEATHERED_SANDSTONE.get()).setId(createId("weathered_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_BRICK_SLAB = register("weathered_sandstone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WEATHERED_SANDSTONE_SLAB.get()).setId(createId("weathered_sandstone_brick_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_BRICK_STAIRS = register("weathered_sandstone_brick_stairs",
            () -> new StairBlock(WEATHERED_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(WEATHERED_SANDSTONE_STAIRS.get()).setId(createId("weathered_sandstone_brick_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_SANDSTONE_BRICK_WALL = register("weathered_sandstone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(WEATHERED_SANDSTONE_WALL.get()).forceSolidOn().setId(createId("weathered_sandstone_brick_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRACKED_WEATHERED_SANDSTONE_BRICKS = register("cracked_weathered_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEATHERED_SANDSTONE_BRICKS.get()).setId(createId("cracked_weathered_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);


    public static void register(IEventBus event) {
        BLOCKS.register(event);
    }

    /**
     * This method only ever gets called whenever you don't want a {@code BlockItem}, setting {@code includeItem}
     * as {@code false}. See below for the more common called method.
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, boolean includeItem) {
        RegistryObject<T> registry = BLOCKS.register(name, block);

        if (includeItem) {
            registerItem(name, registry);
        }

        return registry;
    }

    /**
     * Registers a block by calling the method above with {@code includeItem} set to {@code True}, because this block does
     * include an item.
     *
     * @param name The name of the block
     * @param block The supplier for the block
     * @param creativeModeTabs What tabs in Creative Mode the {@code BlockItem} should appear in. Then, register it in the tabs
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ResourceKey<CreativeModeTab>... creativeModeTabs) {
        RegistryObject<T> registry = register(name, block, true);
        ItemRegistry.registerInTab(registry, creativeModeTabs);

        return registry;
    }

    /**
     * Registers a {@code BlockItem} by calling {@code ItemRegistry.register()}, where its registries are set as
     * {@code Item}.
     */
    private static <T extends Block> RegistryObject<Item> registerItem(String name, RegistryObject<T> registry) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(registry.get(), new Item.Properties().setId(ItemRegistry.createId(name))));
    }

    private static ResourceKey createId(String name) {
        return RenovayMod.createId(name, Registries.BLOCK);
    }
}
