package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.crops.HempCrop;
import com.github.epiicthundercat.hempfarmer.blocks.crops.IndicaCrop;
import com.github.epiicthundercat.hempfarmer.blocks.crops.SativaCrop;
import com.github.epiicthundercat.hempfarmer.blocks.decoration.BurlapCarpetBlock;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderBE;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderBlock;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderContainer;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderRecipeHandler;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBE;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBlock;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryContainer;
import com.github.epiicthundercat.hempfarmer.common.FoodValues;
import com.github.epiicthundercat.hempfarmer.common.effect.HighEffect;
import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import com.github.epiicthundercat.hempfarmer.common.item.*;
import com.github.epiicthundercat.hempfarmer.common.item.food.PotBrownieItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

import static com.github.epiicthundercat.hempfarmer.HempFarmer.MODID;

public class Registration {


    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HempFarmer.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EFFECT.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU.register(bus);
        ENTITY_TYPES.register(bus);
        RECIPES.register(bus);

    }


    public static final RegistryObject<RecipeSerializer<GrinderRecipeHandler>> GRINDER_RECIPE_HANDLER = RECIPES.register("grinder_recipe", () -> GrinderRecipeHandler.SERIALIZER);


    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);
    //   public static final BlockBehaviour.Properties BLOCK_PROPERTIES = new BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.5f);// BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.5f);


    public static final RegistryObject<EntityType<ShotLeafEntity>> SHOT_LEAF_ENTITY = ENTITY_TYPES.register("shot_leaf_entity", () ->
            EntityType.Builder.<ShotLeafEntity>of(ShotLeafEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(8).updateInterval(10).build("shot_leaf_entity"));

    public static final RegistryObject<MobEffect> HIGH = EFFECT.register("high", () -> new HighEffect(MobEffectCategory.BENEFICIAL, 0xB77BAB));


    //Items Here

    //Joints
    public static final RegistryObject<Item> SATIVA_JOINT = ITEMS.register("sativa_joint", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> REGS_JOINT = ITEMS.register("regs_joint", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INDICA_JOINT = ITEMS.register("indica_joint", () -> new Item(ITEM_PROPERTIES));

    //Bud
    public static final RegistryObject<Item> SATIVA_BUD = ITEMS.register("sativa_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INDICA_BUD = ITEMS.register("indica_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BUD = ITEMS.register("bud", () -> new Item(ITEM_PROPERTIES));
    //Ground Bud
    public static final RegistryObject<Item> GROUND_SATIVA_BUD = ITEMS.register("ground_sativa_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GROUND_INDICA_BUD = ITEMS.register("ground_indica_bud", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GROUND_BUD = ITEMS.register("ground_bud", () -> new Item(ITEM_PROPERTIES));

    //Crop Drops
    public static final RegistryObject<Item> RAW_HEMP = ITEMS.register("raw_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_RAW_HEMP = ITEMS.register("lime_raw_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_RAW_HEMP = ITEMS.register("violet_raw_hemp", () -> new Item(ITEM_PROPERTIES));
    //Food Items

    public static final RegistryObject<Item> BOWL_HEMP_HEARTS = ITEMS.register("bowl_hemp_hearts", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(ModSetup.ITEM_GROUP).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> BOWL_LIME_HEMP_HEARTS = ITEMS.register("bowl_lime_hemp_hearts", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(ModSetup.ITEM_GROUP).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> BOWL_VIOLET_HEMP_HEARTS = ITEMS.register("bowl_violet_hemp_hearts", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(ModSetup.ITEM_GROUP).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> HEMP_MILK_BUCKET = ITEMS.register("hemp_milk_bucket", () -> new HempMilkBucketItem((new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> POT_BROWNIE = ITEMS.register("pot_brownie", () -> new PotBrownieItem((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(FoodValues.POT_BROWNIE)));
    public static final RegistryObject<Item> LIME_HEMP_HEARTS = ITEMS.register("lime_hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_HEMP_HEARTS = ITEMS.register("violet_hemp_hearts", () -> new Item(ITEM_PROPERTIES));

    //Random Items
    public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_OIL = ITEMS.register("lime_oil", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_DRY_HEMP = ITEMS.register("lime_dry_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_DRY_HEMP = ITEMS.register("violet_dry_hemp", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_OIL = ITEMS.register("violet_oil", () -> new Item(ITEM_PROPERTIES));





    public static final RegistryObject<Item> SUPERIOR_LEAF_WAND = ITEMS.register("superior_leaf_wand", () -> new LeafWandItem(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1).defaultDurability(100)));
    public static final RegistryObject<Item> SHOT_LEAF = ITEMS.register("shot_leaf", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ROLLING_PAPER = ITEMS.register("rolling_paper", () -> new Item(ITEM_PROPERTIES));

    //Will just be crafting component. (can maybe add abnility to bonemeal/ start fires?
    public static final RegistryObject<Item> LEAF_WAND = ITEMS.register("leaf_wand", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_PAPER = ITEMS.register("hemp_paper", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_OIL = ITEMS.register("hemp_oil", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> HEMP_HEARTS = ITEMS.register("hemp_hearts", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> DRY_HEMP = ITEMS.register("dry_hemp", () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> BROKEN_SUPERIOR_LEAF_WAND = ITEMS.register("broken_superior_leaf_wand", () -> new Item(ITEM_PROPERTIES));

    //Seeds and Seed types
    public static final RegistryObject<Item> SEEDS_SATIVA = ITEMS.register("seeds_sativa", () -> new SativaItem(Registration.SATIVA_CROP.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> SEEDS_SATIVA_CRUSHED = ITEMS.register("seeds_sativa_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_SATIVA_TOASTED = ITEMS.register("seeds_sativa_toasted", () -> new Item(((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(FoodValues.TOASTED_SEEDS))));
    public static final RegistryObject<Item> SEEDS_INDICA = ITEMS.register("seeds_indica", () -> new IndicaItem(Registration.INDICA_CROP.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> SEEDS_INDICA_CRUSHED = ITEMS.register("seeds_indica_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_INDICA_TOASTED = ITEMS.register("seeds_indica_toasted", () -> new Item(((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(FoodValues.TOASTED_SEEDS))));
    public static final RegistryObject<Item> SEEDS_HEMP = ITEMS.register("seeds_hemp", () -> new HempItem(Registration.HEMP_CROP.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> SEEDS_HEMP_CRUSHED = ITEMS.register("seeds_hemp_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SEEDS_HEMP_TOASTED = ITEMS.register("seeds_hemp_toasted", () -> new Item(((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(FoodValues.TOASTED_SEEDS))));

    //BURLAPs (for armor and carpet crafting)
    public static final RegistryObject<Item> OILY_BURLAP_ITEM = ITEMS.register("oily_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> LIME_BURLAP_ITEM = ITEMS.register("lime_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RESIN_BURLAP_ITEM = ITEMS.register("resin_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> VIOLET_BURLAP_ITEM = ITEMS.register("violet_burlap", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> BURLAP_ITEM = ITEMS.register("burlap", () -> new Item(ITEM_PROPERTIES));


    public static final RegistryObject<Item> LEAF = ITEMS.register("leaf", () -> new Item(ITEM_PROPERTIES));


    //Blocks Here
    public static final RegistryObject<Block> LIME_DIRT = BLOCKS.register("lime_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Item> LIME_DIRT_ITEM = fromBlock(LIME_DIRT);
    public static final RegistryObject<Block> OILY_DIRT = BLOCKS.register("oily_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Item> OILY_DIRT_ITEM = fromBlock(OILY_DIRT);
    public static final RegistryObject<Block> RESIN_DIRT = BLOCKS.register("resin_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Item> RESIN_DIRT_ITEM = fromBlock(RESIN_DIRT);
    public static final RegistryObject<Block> VIOLET_DIRT = BLOCKS.register("violet_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Item> VIOLET_DIRT_ITEM = fromBlock(VIOLET_DIRT);
    public static final RegistryObject<Block> INDICA_CROP = BLOCKS.register("indica_crop",
            () -> new IndicaCrop(Block.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Item> INDICA_CROP_ITEM = fromBlock(INDICA_CROP);
    public static final RegistryObject<Block> SATIVA_CROP = BLOCKS.register("sativa_crop",
            () -> new SativaCrop(Block.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Item> SATIVA_CROP_ITEM = fromBlock(SATIVA_CROP);
    public static final RegistryObject<Block> HEMP_CROP = BLOCKS.register("hemp_crop",
            () -> new HempCrop(Block.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Item> HEMP_CROP_ITEM = fromBlock(HEMP_CROP);

    public static final RegistryObject<Block> BURLAP_CARPET_BLOCK = BLOCKS.register("burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).sound(SoundType.WOOL).strength(0.1f)));
    public static final RegistryObject<Item> BURLAP_BLOCK_ITEM = fromBlock(BURLAP_CARPET_BLOCK);

    public static final RegistryObject<Block> RESIN_CARPET_BLOCK = BLOCKS.register("resin_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).sound(SoundType.WOOL).strength(0.3f)));
    public static final RegistryObject<Item> RESIN_BURLAP_BLOCK_ITEM = fromBlock(RESIN_CARPET_BLOCK);

    public static final RegistryObject<Block> OILY_BURLAP_CARPET_BLOCK = BLOCKS.register("oily_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).sound(SoundType.SLIME_BLOCK).strength(0.1f)));
    public static final RegistryObject<Item> OILY_BURLAP_BLOCK_ITEM = fromBlock(OILY_BURLAP_CARPET_BLOCK);

    public static final RegistryObject<Block> LIME_BURLAP_CARPET_BLOCK = BLOCKS.register("lime_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).sound(SoundType.WOOL).strength(0.1f)));
    public static final RegistryObject<Item> LIME_BURLAP_BLOCK_ITEM = fromBlock(LIME_BURLAP_CARPET_BLOCK);


    public static final RegistryObject<Block> VIOLET_BURLAP_CARPET_BLOCK = BLOCKS.register("violet_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).sound(SoundType.WOOL).strength(0.1f)));
    public static final RegistryObject<Item> VIOLET_BURLAP_BLOCK_ITEM = fromBlock(VIOLET_BURLAP_CARPET_BLOCK);


    //Power Battery Registrations - Container, Block Entity, Block, and Item //
    public static final RegistryObject<PowerBatteryBlock> POWER_BATTERY = BLOCKS.register("powerbattery", PowerBatteryBlock::new);
    public static final RegistryObject<Item> POWER_BATTERY_ITEM = fromBlock(POWER_BATTERY);
    public static final RegistryObject<BlockEntityType<PowerBatteryBE>> POWER_BATTERY_BE = BLOCK_ENTITIES.register("powerbattery",
            () -> BlockEntityType.Builder.of(PowerBatteryBE::new, POWER_BATTERY.get()).build(null));
    public static final RegistryObject<MenuType<PowerBatteryContainer>> POWER_BATTERY_CONTAINER = MENU.register("powerbattery",
            () -> IForgeMenuType.create((windowId, inv, data) -> new PowerBatteryContainer(windowId, data.readBlockPos(), inv, inv.player)));


    //Grinder Blocks and Data
    public static final RegistryObject<GrinderBlock> GRINDER = BLOCKS.register("grinder", GrinderBlock::new);
    public static final RegistryObject<Item> GRINDER_ITEM = fromBlock(GRINDER);
    public static final RegistryObject<BlockEntityType<GrinderBE>> GRINDER_BE = BLOCK_ENTITIES.register("grinder",
            () -> BlockEntityType.Builder.of(GrinderBE::new, GRINDER.get()).build(null));
    public static final RegistryObject<MenuType<GrinderContainer>> GRINDER_CONTAINER = MENU.register("grinder",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new GrinderContainer(windowId, data.readBlockPos(), inv, inv.player)));

    //TAG KEYS ARE CREATED HERE AND THEN CALLED IN THE TAGS
    //BUD
    public static final TagKey<Item> BUD_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "bud"));
    //Paper
    public static final TagKey<Item> PAPER_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "paper"));
    public static final TagKey<Item> DRY_HEMP_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "dry_hemp"));
    public static final TagKey<Item> OILY_DIRT_ITEM_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "oily_dirt"));

    public static final TagKey<Block> OILY_DIRT_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(HempFarmer.MODID, "oily_dirt"));
    public static final TagKey<Item> MILK_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "milk"));
    public static final TagKey<Item> SEED_TRIAD = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HempFarmer.MODID, "seed_triad"));

    public static final TagKey<Item> OIL = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "oil"));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {

        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));

    }

    public static int getIdFromBlock(BlockState blockState) {
        int id = Block.getId(blockState);
        return id;
    }


}
