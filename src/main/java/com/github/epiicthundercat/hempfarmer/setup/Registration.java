package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.blocks.crops.IndicaCrop;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderBE;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderBlock;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderContainer;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBE;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBlock;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryContainer;
import com.github.epiicthundercat.hempfarmer.common.item.HempItem;
import com.github.epiicthundercat.hempfarmer.common.item.IndicaItem;
import com.github.epiicthundercat.hempfarmer.common.item.SativaItem;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.epiicthundercat.hempfarmer.HempFarmer.MODID;

public class Registration {


    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU.register(bus);
//        ENTITIES.register(bus);
//        STRUCTURES.register(bus);

    }

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);
    // public static final BlockBehaviour.Properties BLOCK_PROPERTIES = new BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.5f);// BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.5f);

    //Items Here

    //Joints
    public static final RegistryObject<Item> SATIVA_JOINT = ITEMS.register("sativa_joint", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> REGS_JOINT = ITEMS.register("regs_joint", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INDICA_JOINT = ITEMS.register("indica_joint", () -> new Item(ITEM_PROPERTIES));

    //Bud
    public static final RegistryObject<Item> SATIVA_BUD = ITEMS.register("sativa_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INDICA_BUD = ITEMS.register("indica_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BUD = ITEMS.register("bud", () -> new Item(ITEM_PROPERTIES));

    //Crop Drops
    public static final RegistryObject<Item> RAW_HEMP = ITEMS.register("raw_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_RAW_HEMP = ITEMS.register("lime_raw_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_RAW_HEMP = ITEMS.register("violet_raw_hemp", () -> new Item(ITEM_PROPERTIES));
    //Food Items
    public static final RegistryObject<Item> BOWL_HEMP_HEARTS = ITEMS.register("bowl_hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BOWL_LIME_HEMP_HEARTS = ITEMS.register("bowl_lime_hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BOWL_VIOLET_HEMP_HEARTS = ITEMS.register("bowl_violet_hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_MILK_BUCKET = ITEMS.register("hemp_milk_bucket", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> POT_BROWNIE = ITEMS.register("pot_brownie", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_HEMP_HEARTS = ITEMS.register("lime_hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_HEMP_HEARTS = ITEMS.register("violet_hemp_hearts", () -> new Item(ITEM_PROPERTIES));

    //Random Items
    public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_OIL = ITEMS.register("lime_oil", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_DRY_HEMP = ITEMS.register("lime_dry_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_DRY_HEMP = ITEMS.register("violet_dry_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_OIL = ITEMS.register("violet_oil", () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> VIOLET_BURLAP = ITEMS.register("violet_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SUPERIOR_LEAF_WAND = ITEMS.register("superior_leaf_wand", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHOT_LEAF = ITEMS.register("shot_leaf", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ROLLING_PAPER = ITEMS.register("rolling_paper", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RESIN_BURLAP = ITEMS.register("resin_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> OILY_BURLAP = ITEMS.register("oily_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_BURLAP = ITEMS.register("lime_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LEAF_WAND = ITEMS.register("leaf_wand", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_PAPER = ITEMS.register("hemp_paper", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_OIL = ITEMS.register("hemp_oil", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_HEARTS = ITEMS.register("hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> DRY_HEMP = ITEMS.register("dry_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BURLAP = ITEMS.register("burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BROKEN_SUPERIOR_LEAF_WAND = ITEMS.register("broken_superior_leaf_wand", () -> new Item(ITEM_PROPERTIES));

    //Seeds and Seed types
    public static final RegistryObject<Item> SEEDS_SATIVA = ITEMS.register("seeds_sativa", () -> new SativaItem(Registration.SATIVA_CROP.get(), ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_SATIVA_CRUSHED = ITEMS.register("seeds_sativa_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_SATIVA_TOASTED = ITEMS.register("seeds_sativa_toasted", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_INDICA = ITEMS.register("seeds_indica", () -> new IndicaItem(Registration.INDICA_CROP.get(), ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_INDICA_CRUSHED = ITEMS.register("seeds_indica_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_INDICA_TOASTED = ITEMS.register("seeds_indica_toasted", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_HEMP = ITEMS.register("seeds_hemp", () -> new HempItem(Registration.HEMP_CROP.get(), ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_HEMP_CRUSHED = ITEMS.register("seeds_hemp_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_HEMP_TOASTED = ITEMS.register("seeds_hemp_toasted", () -> new Item(ITEM_PROPERTIES));


    //Blocks Here
    //   public static final RegistryObject<Block> LIME_BURLAP_BLOCK = BLOCKS.register("lime_burlap_block", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Block> INDICA_CROP = BLOCKS.register("indica_crop",
            () -> new IndicaCrop(Block.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> SATIVA_CROP = BLOCKS.register("sativa_crop",
            () -> new IndicaCrop(Block.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> HEMP_CROP = BLOCKS.register("hemp_crop",
            () -> new IndicaCrop(Block.Properties.copy(Blocks.WHEAT)));

    //Power Battery Registrations - Container, Block Entity, Block, and Item //
    public static final RegistryObject<PowerBatteryBlock> POWER_BATTERY = BLOCKS.register("powerbattery", PowerBatteryBlock::new);
    public static final RegistryObject<Item> POWER_BATTERY_ITEM = fromBlock(POWER_BATTERY);
    public static final RegistryObject<BlockEntityType<PowerBatteryBE>> POWER_BATTERY_BE = BLOCK_ENTITIES.register("powerbattery",
            () -> BlockEntityType.Builder.of(PowerBatteryBE::new, POWER_BATTERY.get()).build(null));
    public static final RegistryObject<MenuType<PowerBatteryContainer>> POWER_BATTERY_CONTAINER = MENU.register("powerbattery",
            () -> IForgeMenuType.create((windowId, inv, data) -> new PowerBatteryContainer(windowId, data.readBlockPos(), inv, inv.player)));


    //Grinder Blcoks and Data
    public static final RegistryObject<GrinderBlock> GRINDER = BLOCKS.register("grinder", GrinderBlock::new);
    public static final RegistryObject<Item> GRINDER_ITEM = fromBlock(GRINDER);
    public static final RegistryObject<BlockEntityType<GrinderBE>> GRINDER_BE = BLOCK_ENTITIES.register("grinder",
            () -> BlockEntityType.Builder.of(GrinderBE::new, GRINDER.get()).build(null));
    public static final RegistryObject<MenuType<GrinderContainer>> GRINDER_CONTAINER = MENU.register("grinder",
            () -> IForgeMenuType.create((windowId, inv, data) -> new GrinderContainer(windowId, data.readBlockPos(), inv, inv.player)));


    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {

        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));

    }


}
