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
import com.github.epiicthundercat.hempfarmer.common.effect.CalmEffect;
import com.github.epiicthundercat.hempfarmer.common.effect.HighEffect;
import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import com.github.epiicthundercat.hempfarmer.common.item.*;
import com.github.epiicthundercat.hempfarmer.common.item.food.BowlFoodItem;
import com.github.epiicthundercat.hempfarmer.common.item.food.CannabisTeaItem;
import com.github.epiicthundercat.hempfarmer.common.item.food.PotBrownieItem;
import com.github.epiicthundercat.hempfarmer.common.item.joint.HempJointItem;
import com.github.epiicthundercat.hempfarmer.common.item.joint.IndicaJointItem;
import com.github.epiicthundercat.hempfarmer.common.item.joint.SativaJointItem;
import com.github.epiicthundercat.hempfarmer.event.loot.SeedDropModifier;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Set;


import static com.github.epiicthundercat.hempfarmer.HempFarmer.MODID;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HempFarmer.MODID);
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HempFarmer.MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, MODID);
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);
    // ArmorMaterial is a plain record in 26.1.2 — no registry needed


    public static void init(BusGroup bus) {
        EFFECT.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU.register(bus);
        ENTITY_TYPES.register(bus);
        RECIPES.register(bus);
        SOUNDS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
        RECIPE_TYPES.register(bus);
        LOOT_MODIFIERS.register(bus);
    }

    private static Item.Properties props(String name) {
        return new Item.Properties().setId(ITEMS.key(name));
    }

    public static final RegistryObject<RecipeSerializer<GrinderRecipeHandler>> GRINDER_RECIPE_HANDLER = RECIPES.register("grinder_recipe", () -> GrinderRecipeHandler.SERIALIZER);

    public static final RegistryObject<RecipeType<GrinderRecipeHandler>> GRINDER_RECIPE_TYPE = RECIPE_TYPES.register("grinder_recipe",
            () -> RecipeType.simple(Identifier.fromNamespaceAndPath(MODID, "grinder_recipe")));

    public static final RegistryObject<MapCodec<SeedDropModifier>> SEED_DROP_MODIFIER = LOOT_MODIFIERS.register("seed_drop_modifier", SeedDropModifier.CODEC::get);

    public static final RegistryObject<Item> SHOT_LEAF = ITEMS.register("shot_leaf", () -> new Item(props("shot_leaf")));

    public static final RegistryObject<CreativeModeTab> HEMP_FARMER_TAB = CREATIVE_MODE_TABS.register("hemp_farmer_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.hempfarmer"))
                    .icon(() -> new ItemStack(SHOT_LEAF.get()))
                    .displayItems((params, output) -> {
                        // Items are populated via BuildCreativeModeTabContentsEvent in ModSetup
                    })
                    .build());


    public static final RegistryObject<EntityType<ShotLeafEntity>> SHOT_LEAF_ENTITY = ENTITY_TYPES.register("shot_leaf_entity", () ->
            EntityType.Builder.<ShotLeafEntity>of(ShotLeafEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(8).updateInterval(10).build(ENTITY_TYPES.key("shot_leaf_entity")));

    public static final RegistryObject<MobEffect> HIGH = EFFECT.register("high", () -> new HighEffect(MobEffectCategory.BENEFICIAL, 0xB77BAB));
    public static final RegistryObject<MobEffect> CALM = EFFECT.register("calm", () -> new CalmEffect(MobEffectCategory.BENEFICIAL, 0xD4C1D1));

    //Sounds
    public static final RegistryObject<SoundEvent> SMOKE = SOUNDS.register("smoke", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MODID, "smoke")));
    public static final RegistryObject<SoundEvent> COUGH = SOUNDS.register("cough", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MODID, "cough")));
    public static final RegistryObject<SoundEvent> NELLY_SONG = SOUNDS.register("nelly_song", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MODID, "nelly_song")));


    //Items Here

    //CD
    public static final ResourceKey<JukeboxSong> NELLY_SONG_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(MODID, "nelly_song"));
    public static final RegistryObject<Item> NELLY_SONG_MUSIC_DISC = ITEMS.register("nelly_song_music_disc",
            () -> new Item(props("nelly_song_music_disc").stacksTo(1).rarity(Rarity.EPIC).jukeboxPlayable(NELLY_SONG_KEY)));
    //Lighter
    public static final RegistryObject<Item> LIGHTER = ITEMS.register("lighter", () -> new LighterItem(props("lighter").stacksTo(1).durability(100)));

    //Joints
    public static final RegistryObject<Item> SATIVA_JOINT = ITEMS.register("sativa_joint", () -> new SativaJointItem(props("sativa_joint").stacksTo(1)));
    public static final RegistryObject<Item> REGS_JOINT = ITEMS.register("regs_joint", () -> new HempJointItem(props("regs_joint").stacksTo(1)));
    public static final RegistryObject<Item> INDICA_JOINT = ITEMS.register("indica_joint", () -> new IndicaJointItem(props("indica_joint").stacksTo(1)));

    //Bud
    public static final RegistryObject<Item> SATIVA_BUD = ITEMS.register("sativa_bud", () -> new Item(props("sativa_bud")));
    public static final RegistryObject<Item> INDICA_BUD = ITEMS.register("indica_bud", () -> new Item(props("indica_bud")));
    public static final RegistryObject<Item> BUD = ITEMS.register("bud", () -> new Item(props("bud")));
    //Ground Bud
    public static final RegistryObject<Item> GROUND_SATIVA_BUD = ITEMS.register("ground_sativa_bud", () -> new Item(props("ground_sativa_bud")));
    public static final RegistryObject<Item> GROUND_INDICA_BUD = ITEMS.register("ground_indica_bud", () -> new Item(props("ground_indica_bud")));
    public static final RegistryObject<Item> GROUND_BUD = ITEMS.register("ground_bud", () -> new Item(props("ground_bud")));

    //Crop Drops
    public static final RegistryObject<Item> RAW_HEMP = ITEMS.register("raw_hemp", () -> new Item(props("raw_hemp")));
    public static final RegistryObject<Item> LIME_RAW_HEMP = ITEMS.register("lime_raw_hemp", () -> new Item(props("lime_raw_hemp")));
    public static final RegistryObject<Item> VIOLET_RAW_HEMP = ITEMS.register("violet_raw_hemp", () -> new Item(props("violet_raw_hemp")));

    //Food Items
    public static final RegistryObject<Item> BOWL_HEMP_HEARTS = ITEMS.register("bowl_hemp_hearts", () -> new BowlFoodItem(props("bowl_hemp_hearts").stacksTo(1).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> BOWL_LIME_HEMP_HEARTS = ITEMS.register("bowl_lime_hemp_hearts", () -> new BowlFoodItem(props("bowl_lime_hemp_hearts").stacksTo(1).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> BOWL_VIOLET_HEMP_HEARTS = ITEMS.register("bowl_violet_hemp_hearts", () -> new BowlFoodItem(props("bowl_violet_hemp_hearts").stacksTo(1).food(FoodValues.HEMP_BOWL)));
    public static final RegistryObject<Item> HEMP_MILK_BUCKET = ITEMS.register("hemp_milk_bucket", () -> new HempMilkBucketItem(props("hemp_milk_bucket").craftRemainder(Items.BUCKET).stacksTo(1).food(FoodValues.HEMP_MILK_FOOD, FoodValues.HEMP_MILK_CONSUMABLE)));
    public static final RegistryObject<Item> POT_BROWNIE = ITEMS.register("pot_brownie", () -> new PotBrownieItem(props("pot_brownie").food(FoodValues.POT_BROWNIE, FoodValues.POT_BROWNIE_CONSUMABLE)));
    public static final RegistryObject<Item> LIME_HEMP_HEARTS = ITEMS.register("lime_hemp_hearts", () -> new Item(props("lime_hemp_hearts")));
    public static final RegistryObject<Item> VIOLET_HEMP_HEARTS = ITEMS.register("violet_hemp_hearts", () -> new Item(props("violet_hemp_hearts")));

    //Random Items
    public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(props("resin")));
    public static final RegistryObject<Item> LIME_OIL = ITEMS.register("lime_oil", () -> new Item(props("lime_oil")));
    public static final RegistryObject<Item> LIME_DRY_HEMP = ITEMS.register("lime_dry_hemp", () -> new Item(props("lime_dry_hemp")));
    public static final RegistryObject<Item> VIOLET_DRY_HEMP = ITEMS.register("violet_dry_hemp", () -> new Item(props("violet_dry_hemp")));
    public static final RegistryObject<Item> VIOLET_OIL = ITEMS.register("violet_oil", () -> new Item(props("violet_oil")));

    public static final RegistryObject<Item> SUPERIOR_LEAF_WAND = ITEMS.register("superior_leaf_wand", () -> new LeafWandItem(props("superior_leaf_wand").stacksTo(1).durability(100)));
    public static final RegistryObject<Item> ROLLING_PAPER = ITEMS.register("rolling_paper", () -> new Item(props("rolling_paper")));

    public static final RegistryObject<Item> LEAF_WAND = ITEMS.register("leaf_wand", () -> new Item(props("leaf_wand")));
    public static final RegistryObject<Item> HEMP_PAPER = ITEMS.register("hemp_paper", () -> new Item(props("hemp_paper")));
    public static final RegistryObject<Item> HEMP_OIL = ITEMS.register("hemp_oil", () -> new Item(props("hemp_oil")));
    public static final RegistryObject<Item> HEMP_HEARTS = ITEMS.register("hemp_hearts", () -> new Item(props("hemp_hearts")));
    public static final RegistryObject<Item> DRY_HEMP = ITEMS.register("dry_hemp", () -> new Item(props("dry_hemp")));

    public static final RegistryObject<Item> BROKEN_SUPERIOR_LEAF_WAND = ITEMS.register("broken_superior_leaf_wand", () -> new Item(props("broken_superior_leaf_wand")));

    //Seeds and Seed types
    public static final RegistryObject<Item> SEEDS_SATIVA = ITEMS.register("seeds_sativa", () -> new SativaItem(Registration.SATIVA_CROP.get(), props("seeds_sativa")));
    public static final RegistryObject<Item> SEEDS_SATIVA_CRUSHED = ITEMS.register("seeds_sativa_crushed", () -> new Item(props("seeds_sativa_crushed")));
    public static final RegistryObject<Item> SEEDS_SATIVA_TOASTED = ITEMS.register("seeds_sativa_toasted", () -> new Item(props("seeds_sativa_toasted").food(FoodValues.TOASTED_SEEDS)));
    public static final RegistryObject<Item> SEEDS_INDICA = ITEMS.register("seeds_indica", () -> new IndicaItem(Registration.INDICA_CROP.get(), props("seeds_indica")));
    public static final RegistryObject<Item> SEEDS_INDICA_CRUSHED = ITEMS.register("seeds_indica_crushed", () -> new Item(props("seeds_indica_crushed")));
    public static final RegistryObject<Item> SEEDS_INDICA_TOASTED = ITEMS.register("seeds_indica_toasted", () -> new Item(props("seeds_indica_toasted").food(FoodValues.TOASTED_SEEDS)));
    public static final RegistryObject<Item> SEEDS_HEMP = ITEMS.register("seeds_hemp", () -> new HempItem(Registration.HEMP_CROP.get(), props("seeds_hemp")));
    public static final RegistryObject<Item> SEEDS_HEMP_CRUSHED = ITEMS.register("seeds_hemp_crushed", () -> new Item(props("seeds_hemp_crushed")));
    public static final RegistryObject<Item> SEEDS_HEMP_TOASTED = ITEMS.register("seeds_hemp_toasted", () -> new Item(props("seeds_hemp_toasted").food(FoodValues.TOASTED_SEEDS)));

    //BURLAPs (for armor and carpet crafting)
    public static final RegistryObject<Item> OILY_BURLAP_ITEM = ITEMS.register("oily_burlap", () -> new Item(props("oily_burlap")));
    public static final RegistryObject<Item> LIME_BURLAP_ITEM = ITEMS.register("lime_burlap", () -> new Item(props("lime_burlap")));
    public static final RegistryObject<Item> RESIN_BURLAP_ITEM = ITEMS.register("resin_burlap", () -> new Item(props("resin_burlap")));
    public static final RegistryObject<Item> VIOLET_BURLAP_ITEM = ITEMS.register("violet_burlap", () -> new Item(props("violet_burlap")));
    public static final RegistryObject<Item> BURLAP_ITEM = ITEMS.register("burlap", () -> new Item(props("burlap")));

    public static final RegistryObject<Item> LEAF = ITEMS.register("leaf", () -> new Item(props("leaf")));

    public static final RegistryObject<Item> CANNABIS_TEA = ITEMS.register("cannabis_tea", () -> new CannabisTeaItem(props("cannabis_tea").food(FoodValues.CANNABIS_TEA, FoodValues.CANNABIS_TEA_CONSUMABLE)));


    public static final ArmorMaterial BURLAP_ARMOR_MATERIAL = new ArmorMaterial(
            5,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS,       1);
                map.put(ArmorType.LEGGINGS,    2);
                map.put(ArmorType.CHESTPLATE,  2);
                map.put(ArmorType.HELMET,      1);
                map.put(ArmorType.BODY,        0);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, "repairs_burlap_armor")),
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(MODID, "burlap"))
    );

    public static final RegistryObject<Item> BURLAP_HELMET = ITEMS.register("burlap_helmet",
            () -> new Item(props("burlap_helmet").stacksTo(1).humanoidArmor(BURLAP_ARMOR_MATERIAL, ArmorType.HELMET)));

    public static final RegistryObject<Item> BURLAP_CHESTPLATE = ITEMS.register("burlap_chestplate",
            () -> new Item(props("burlap_chestplate").stacksTo(1).humanoidArmor(BURLAP_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));

    public static final RegistryObject<Item> BURLAP_LEGGINGS = ITEMS.register("burlap_leggings",
            () -> new Item(props("burlap_leggings").stacksTo(1).humanoidArmor(BURLAP_ARMOR_MATERIAL, ArmorType.LEGGINGS)));

    public static final RegistryObject<Item> BURLAP_BOOTS = ITEMS.register("burlap_boots",
            () -> new Item(props("burlap_boots").stacksTo(1).humanoidArmor(BURLAP_ARMOR_MATERIAL, ArmorType.BOOTS)));

    //Blocks Here
    public static final RegistryObject<Block> LIME_DIRT = BLOCKS.register("lime_dirt", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).setId(BLOCKS.key("lime_dirt"))));
    public static final RegistryObject<Item> LIME_DIRT_ITEM = fromBlock(LIME_DIRT);
    public static final RegistryObject<Block> OILY_DIRT = BLOCKS.register("oily_dirt", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.SLIME_BLOCK).setId(BLOCKS.key("oily_dirt"))));
    public static final RegistryObject<Item> OILY_DIRT_ITEM = fromBlock(OILY_DIRT);
    public static final RegistryObject<Block> RESIN_DIRT = BLOCKS.register("resin_dirt", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).setId(BLOCKS.key("resin_dirt"))));
    public static final RegistryObject<Item> RESIN_DIRT_ITEM = fromBlock(RESIN_DIRT);
    public static final RegistryObject<Block> VIOLET_DIRT = BLOCKS.register("violet_dirt", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).setId(BLOCKS.key("violet_dirt"))));
    public static final RegistryObject<Item> VIOLET_DIRT_ITEM = fromBlock(VIOLET_DIRT);
    public static final RegistryObject<Block> INDICA_CROP = BLOCKS.register("indica_crop",
            () -> new IndicaCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(BLOCKS.key("indica_crop"))));
    public static final RegistryObject<Block> SATIVA_CROP = BLOCKS.register("sativa_crop",
            () -> new SativaCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(BLOCKS.key("sativa_crop"))));
    public static final RegistryObject<Block> HEMP_CROP = BLOCKS.register("hemp_crop",
            () -> new HempCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(BLOCKS.key("hemp_crop"))));

    public static final RegistryObject<Block> BURLAP_CARPET_BLOCK = BLOCKS.register("burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOL).strength(0.1f).setId(BLOCKS.key("burlap_carpet"))));
    public static final RegistryObject<Item> BURLAP_BLOCK_ITEM = fromBlock(BURLAP_CARPET_BLOCK);

    public static final RegistryObject<Block> RESIN_CARPET_BLOCK = BLOCKS.register("resin_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOL).strength(0.3f).setId(BLOCKS.key("resin_carpet"))));
    public static final RegistryObject<Item> RESIN_BURLAP_BLOCK_ITEM = fromBlock(RESIN_CARPET_BLOCK);

    public static final RegistryObject<Block> OILY_BURLAP_CARPET_BLOCK = BLOCKS.register("oily_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.SLIME_BLOCK).strength(0.1f).setId(BLOCKS.key("oily_burlap_carpet"))));
    public static final RegistryObject<Item> OILY_BURLAP_BLOCK_ITEM = fromBlock(OILY_BURLAP_CARPET_BLOCK);

    public static final RegistryObject<Block> LIME_BURLAP_CARPET_BLOCK = BLOCKS.register("lime_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOL).strength(0.1f).setId(BLOCKS.key("lime_burlap_carpet"))));
    public static final RegistryObject<Item> LIME_BURLAP_BLOCK_ITEM = fromBlock(LIME_BURLAP_CARPET_BLOCK);

    public static final RegistryObject<Block> VIOLET_BURLAP_CARPET_BLOCK = BLOCKS.register("violet_burlap_carpet", () -> new BurlapCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOL).strength(0.1f).setId(BLOCKS.key("violet_burlap_carpet"))));
    public static final RegistryObject<Item> VIOLET_BURLAP_BLOCK_ITEM = fromBlock(VIOLET_BURLAP_CARPET_BLOCK);


    //Power Battery Registrations - Container, Block Entity, Block, and Item //
    public static final RegistryObject<PowerBatteryBlock> POWER_BATTERY = BLOCKS.register("powerbattery", () -> new PowerBatteryBlock(
            BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL)
                    .strength(2.0f)
                    .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
                    .requiresCorrectToolForDrops()
                    .setId(BLOCKS.key("powerbattery"))));
    public static final RegistryObject<Item> POWER_BATTERY_ITEM = fromBlock(POWER_BATTERY);
    public static final RegistryObject<BlockEntityType<PowerBatteryBE>> POWER_BATTERY_BE = BLOCK_ENTITIES.register("powerbattery",
            () -> new BlockEntityType<>(PowerBatteryBE::new, Set.of(POWER_BATTERY.get())));
    public static final RegistryObject<MenuType<PowerBatteryContainer>> POWER_BATTERY_CONTAINER = MENU.register("powerbattery",
            () -> IForgeMenuType.create((windowId, inv, data) -> new PowerBatteryContainer(windowId, data.readBlockPos(), inv, inv.player)));


    //Grinder Blocks and Data
    public static final RegistryObject<GrinderBlock> GRINDER = BLOCKS.register("grinder", () -> new GrinderBlock(
            BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    .strength(2.0f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .setId(BLOCKS.key("grinder"))));
    public static final RegistryObject<Item> GRINDER_ITEM = fromBlock(GRINDER);
    public static final RegistryObject<BlockEntityType<GrinderBE>> GRINDER_BE = BLOCK_ENTITIES.register("grinder",
            () -> new BlockEntityType<>(GrinderBE::new, Set.of(GRINDER.get())));
    public static final RegistryObject<MenuType<GrinderContainer>> GRINDER_CONTAINER = MENU.register("grinder",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new GrinderContainer(windowId, data.readBlockPos(), inv, inv.player)));

    static {
        PowerBatteryBE.blockEntityType = POWER_BATTERY_BE;
        GrinderBE.blockEntityType = GRINDER_BE;
    }

    //TAG KEYS ARE CREATED HERE AND THEN CALLED IN THE TAGS
    //BUD
    public static final TagKey<Item> BUD_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "bud"));
    //Paper
    public static final TagKey<Item> PAPER_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "paper"));
    public static final TagKey<Item> DRY_HEMP_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "dry_hemp"));
    public static final TagKey<Item> OILY_DIRT_ITEM_TAG = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "oily_dirt"));

    public static final TagKey<Block> OILY_DIRT_TAG = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "oily_dirt"));
    public static final TagKey<Item> MILK_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "milk"));
    public static final TagKey<Item> SEED_TRIAD = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HempFarmer.MODID, "seed_triad"));

    public static final TagKey<Item> OIL = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, "oil"));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(),
                new Item.Properties().setId(ITEMS.key(block.getId().getPath()))));
    }

    public static int getIdFromBlock(BlockState blockState) {
        return Block.getId(blockState);
    }
}
