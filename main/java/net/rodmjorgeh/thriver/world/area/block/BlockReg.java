package net.rodmjorgeh.thriver.world.area.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.area.block.state.properties.WoodTypeThr;
import net.rodmjorgeh.thriver.world.item.CreativeModeTabReg;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import net.rodmjorgeh.thriver.world.item.ItemReg;
import net.rodmjorgeh.thriver.world.area.maps.MapColorThr;

import java.util.function.Supplier;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(BuiltInRegistries.BLOCK, ThriverMod.MOD_ID);

    public static final Supplier<Block> PALM_LOG = register("palm_log",
            () -> new RotatedPillarBlock(Blocks.logProperties(MapColor.COLOR_BROWN, MapColorThr.PALM_TREE, SoundType.WOOD).setId(createId("palm_log"))),
            CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> PALM_WOOD = register("palm_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).setId(createId("palm_wood"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> STRIPPED_PALM_LOG = register("stripped_palm_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("stripped_palm_log"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> STRIPPED_PALM_WOOD = register("stripped_palm_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_WOOD)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("stripped_palm_wood"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_PLANKS = register("palm_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_planks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_SLAB = register("palm_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_STAIRS = register("palm_stairs",
            () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS)
                            .mapColor(MapColorThr.PALM_TREE)
                            .setId(createId("palm_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_FENCE = register("palm_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_fence"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_FENCE_GATE = register("palm_fence_gate",
            () -> new FenceGateBlock(WoodTypeThr.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_fence_gate"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_DOOR = register("palm_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_door"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_TRAPDOOR = register("palm_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_trapdoor"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_PRESSURE_PLATE = register("palm_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_pressure_plate"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_BUTTON = register("palm_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, Blocks.buttonProperties()
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_button"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> PALM_LEAVES = register("palm_leaves",
            () -> new PalmLeavesBlock(Blocks.leavesProperties(SoundType.GRASS).setId(createId("palm_leaves"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> PALM_SPROUT = register("palm_sprout",
            () -> new PalmSproutBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SAPLING).setId(createId("palm_sprout"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> POTTED_PALM_SPROUT = register("potted_palm_sprout",
            () -> new FlowerPotBlock(PALM_SPROUT.get(), Blocks.flowerPotProperties().setId(createId("potted_palm_sprout"))),
            false);
    public static final Supplier<Block> PALM_SIGN = register("palm_sign",
            () -> new StandingSignBlock(WoodTypeThr.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SIGN)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_sign"))),
            false);
    public static final Supplier<Block> PALM_WALL_SIGN = register("palm_wall_sign",
            () -> new WallSignBlock(WoodTypeThr.PALM, Blocks.wallVariant(PALM_SIGN.get(), true)
                    .mapColor(MapColorThr.PALM_TREE)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
                    .setId(createId("palm_sign"))),
            false);
    public static final Supplier<Block> PALM_HANGING_SIGN = register("palm_hanging_sign",
            () -> new CeilingHangingSignBlock(WoodTypeThr.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_HANGING_SIGN)
                    .mapColor(MapColorThr.PALM_TREE)
                    .setId(createId("palm_hanging_sign"))),
            false);
    public static final Supplier<Block> PALM_WALL_HANGING_SIGN = register("palm_wall_hanging_sign",
            () -> new WallHangingSignBlock(WoodTypeThr.PALM, Blocks.wallVariant(PALM_HANGING_SIGN.get(), true)
                    .mapColor(MapColorThr.PALM_TREE)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
                    .setId(createId("palm_wall_hanging_sign"))),
            false);

    public static final Supplier<Block> COCONUT = register("coconut",
            () -> new CoconutBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .randomTicks()
                    .strength(1.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .setId(createId("coconut"))),
            false);

    public static final Supplier<Block> REEDS = register("reeds",
            () -> new ReedBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WET_GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .setId(createId("reeds"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> TALL_REEDS = register("tall_reeds",
            () -> new TallReedBlock(BlockBehaviour.Properties.ofFullCopy(REEDS.get()).setId(createId("tall_reeds"))),
            false);
    public static final Supplier<Block> COIR_MAT = register("coir_mat",
            () -> new CoirMatBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.RAW_IRON)
                    .strength(0.75F, 2.0F)
                    .sound(SoundType.MOSS_CARPET)
                    .setId(createId("coir_mat"))),
            false);

    public static final Supplier<Block> SILT_MUD = register("silt_mud",
            () -> new MudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
                    .mapColor(MapColor.SAND)
                    .setId(createId("silt_mud"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> CRACKED_MUD_BRICKS = register("cracked_mud_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS).setId(createId("cracked_mud_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);

    public static final Supplier<Block> DOLLS_EYES = register("dolls_eyes",
            () -> new DollsEyesBlock(MobEffects.NIGHT_VISION, 20.0F,
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .setId(createId("dolls_eyes"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Block> DOLLS_EYES_CROP = register("dolls_eyes_crop",
            () -> new DollsEyesCropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .setId(createId("dolls_eyes_crop"))),
            false);
    public static final Supplier<Block> POTTED_DOLLS_EYES = register("potted_dolls_eyes",
            () -> new FlowerPotBlock(DOLLS_EYES.get(), Blocks.flowerPotProperties().setId(createId("potted_dolls_eyes"))),
            false);

    public static final Supplier<Block> BEIGE_WOOL = register("beige_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)
                    .mapColor(MapColorThr.COLOR_BEIGE)
                    .setId(createId("beige_wool"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Block> BEIGE_BANNER = register("beige_banner",
            () -> new BannerBlock(DyeColorThr.BEIGE, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_BANNER).setId(createId("beige_banner"))),
            false);
    public static final Supplier<Block> BEIGE_WALL_BANNER = register("beige_wall_banner",
            () -> new WallBannerBlock(DyeColorThr.BEIGE, Blocks.wallVariant(BEIGE_BANNER.get(), true)
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .setId(createId("beige_wall_banner"))),
            false);
    public static final Supplier<Block> BEIGE_BED = register("beige_bed",
            () -> new BedBlock(DyeColorThr.BEIGE, BlockBehaviour.Properties.of()
                    .mapColor(x -> x.getValue(BedBlock.PART) == BedPart.FOOT ? MapColorThr.COLOR_BEIGE : MapColor.WOOL)
                    .sound(SoundType.WOOD)
                    .strength(0.2F)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .setId(createId("beige_bed"))),
            false);
    public static final Supplier<Block> BEIGE_CANDLE = register("beige_candle",
            () -> new CandleBlock(Blocks.candleProperties(MapColorThr.COLOR_BEIGE).setId(createId("beige_candle"))),
            CreativeModeTabs.COLORED_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Block> BEIGE_CANDLE_CAKE = register("beige_candle_cake",
            () -> new CandleCakeBlock(BEIGE_CANDLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE).setId(createId("beige_candle_cake"))),
            false);
    public static final Supplier<Block> BEIGE_CARPET = register("beige_carpet",
            () -> new WoolCarpetBlock(DyeColorThr.BEIGE, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET)
                    .mapColor(MapColorThr.COLOR_BEIGE)
                    .setId(createId("beige_carpet"))),
            false);
    public static final Supplier<Block> BEIGE_CONCRETE = register("beige_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CONCRETE)
                    .mapColor(DyeColorThr.BEIGE)
                    .setId(createId("beige_concrete"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Block> BEIGE_CONCRETE_POWDER = register("beige_concrete_powder",
            () -> new ConcretePowderBlock(BEIGE_CONCRETE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CONCRETE_POWDER)
                    .mapColor(DyeColorThr.BEIGE)
                    .setId(createId("beige_concrete_powder"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Block> BEIGE_GLAZED_TERRACOTTA = register("beige_glazed_terracotta",
            () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_GLAZED_TERRACOTTA)
                    .mapColor(DyeColorThr.BEIGE)
                    .setId(createId("beige_glazed_terracotta"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Block> BEIGE_STAINED_GLASS = register("beige_stained_glass",
            () -> new StainedGlassBlock(DyeColorThr.BEIGE, BlockBehaviour.Properties.of()
                    .mapColor(MapColorThr.COLOR_BEIGE)
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isValidSpawn(BlockReg::falsePredicate)
                    .isRedstoneConductor(BlockReg::falsePredicate)
                    .isSuffocating(BlockReg::falsePredicate)
                    .isViewBlocking(BlockReg::falsePredicate)
                    .setId(createId("beige_stained_glass"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Block> BEIGE_STAINED_GLASS_PANE = register("beige_stained_glass_pane",
            () -> new StainedGlassPaneBlock(DyeColorThr.BEIGE, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_STAINED_GLASS_PANE)
                    .mapColor(MapColorThr.COLOR_BEIGE)
                    .setId(createId("beige_stained_glass_pane"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final DeferredHolder<Block, Block> BEIGE_SHULKER_BOX = register("beige_shulker_box",
            () -> new ShulkerBoxBlock(DyeColorThr.BEIGE, Blocks.shulkerBoxProperties(MapColorThr.COLOR_BEIGE).setId(createId("beige_terracotta"))),
            false);
    public static final Supplier<Block> BEIGE_TERRACOTTA = register("beige_terracotta",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TERRACOTTA)
                    .mapColor(MapColorThr.TERRACOTTA_BEIGE)
                    .setId(createId("beige_terracotta"))),
            CreativeModeTabs.COLORED_BLOCKS);

    public static final Supplier<Block> SANDSTONE_BRICKS = register("sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(createId("sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> SANDSTONE_BRICK_SLAB = register("sandstone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB).setId(createId("sandstone_brick_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> SANDSTONE_BRICK_STAIRS = register("sandstone_brick_stairs",
            () -> new StairBlock(SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS).setId(createId("sandstone_brick_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> SANDSTONE_BRICK_WALL = register("sandstone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_WALL).forceSolidOn().setId(createId("sandstone_brick_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> CRACKED_SANDSTONE_BRICKS = register("cracked_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(SANDSTONE_BRICKS.get()).setId(createId("cracked_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);

    public static final Supplier<Block> RED_SANDSTONE_BRICKS = register("red_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE).setId(createId("red_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> RED_SANDSTONE_BRICK_SLAB = register("red_sandstone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB).setId(createId("red_sandstone_brick_slab"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> RED_SANDSTONE_BRICK_STAIRS = register("red_sandstone_brick_stairs",
            () -> new StairBlock(RED_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_STAIRS).setId(createId("red_sandstone_brick_stairs"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> RED_SANDSTONE_BRICK_WALL = register("red_sandstone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_WALL).forceSolidOn().setId(createId("red_sandstone_brick_wall"))),
            CreativeModeTabs.BUILDING_BLOCKS);
    public static final Supplier<Block> CRACKED_RED_SANDSTONE_BRICKS = register("cracked_red_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(RED_SANDSTONE_BRICKS.get()).setId(createId("cracked_red_sandstone_bricks"))),
            CreativeModeTabs.BUILDING_BLOCKS);


    public static void register(IEventBus event) { BLOCKS.register(event); }

    /**
     * This method only ever gets called whenever you don't want a {@link BlockItem}, setting {@code includeItem}
     * as {@code false}. See below for the more common called method.
     */
    private static <T extends Block> DeferredHolder<Block, T> register(String name, Supplier<T> block, boolean includeItem) {
        DeferredHolder<Block, T> registry = BLOCKS.register(name, block);

        if (includeItem) {
            registerItem(name, registry);
        }

        return registry;
    }

    /**
     * Registers a block by calling the method above with {@code includeItem} set to {@code True}, because this block does
     * include an item.
     *
     * @param name The name of the blocks
     * @param block The supplier for the block
     * @param creativeModeTabs What tabs in Creative Mode the {@link BlockItem} should appear in. Then, register it in the tabs
     */
    private static <T extends Block> Supplier<T> register(String name, Supplier<T> block, ResourceKey<CreativeModeTab>... creativeModeTabs) {
        Supplier<T> registry = register(name, block, true);
        CreativeModeTabReg.registerInTab(registry, creativeModeTabs);

        return registry;
    }

    /**
     * Registers a {@link BlockItem} by calling {@link ItemReg}{@code .register()}, where its registries are set as
     * {@link Item}.
     */
    private static <T extends Block> Supplier<BlockItem> registerItem(String name, Supplier<T> registry) {
        return ItemReg.ITEMS.register(name, () -> new BlockItem(registry.get(), new Item.Properties().setId(ItemReg.createId(name))));
    }

    private static ResourceKey createId(String name) {
        return ResourceMod.createId(name, Registries.BLOCK);
    }

    private static boolean falsePredicate(BlockState a, BlockGetter b, BlockPos c, EntityType<?> d) {
        return false;
    }
    private static boolean falsePredicate(BlockState a, BlockGetter b, BlockPos c) {
        return false;
    }
}
