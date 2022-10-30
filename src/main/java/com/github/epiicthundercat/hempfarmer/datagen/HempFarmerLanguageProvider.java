package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderBlock.MESSAGE_GRINDER;
import static com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBlock.MESSAGE_POWER_BATTERY;
import static com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryBlock.SCREEN_HEMP_FARMER_POWER_BATTERY;
import static com.github.epiicthundercat.hempfarmer.common.item.HempItem.HEMP_ITEM_MESSAGE;
import static com.github.epiicthundercat.hempfarmer.common.item.IndicaItem.INDICA_ITEM_MESSAGE;
import static com.github.epiicthundercat.hempfarmer.common.item.SativaItem.SATIVA_ITEM_MESSAGE;
import static com.github.epiicthundercat.hempfarmer.setup.ModSetup.TAB_NAME;

public class HempFarmerLanguageProvider extends LanguageProvider {

    public HempFarmerLanguageProvider(DataGenerator gen, String locale) {
        super(gen, HempFarmer.MODID, locale);
    }

    @Override
    protected void addTranslations() {

        add(INDICA_ITEM_MESSAGE, "Must Be Placed on Farmland");
        add(HEMP_ITEM_MESSAGE, "Must Be Placed on Farmland");
        add(SATIVA_ITEM_MESSAGE, "Must Be Placed on Farmland");

        add("itemGroup." + TAB_NAME, "Hemp Farmer");
        add(MESSAGE_POWER_BATTERY, "Power battery generating %s per tick!");
        add(SCREEN_HEMP_FARMER_POWER_BATTERY, "Power Battery");
        add(Registration.POWER_BATTERY.get(), "Power Battery");

        add(MESSAGE_GRINDER, "Grinding Hemp!");
        add(Registration.GRINDER.get(), "Hemp Grinder");

        // SATIVA
        add(Registration.SATIVA_JOINT.get(), "Sativa Joint");
        add(Registration.SATIVA_BUD.get(), "Sativa Bud");
        add(Registration.SEEDS_SATIVA.get(), "Sativa Seeds");
        add(Registration.SEEDS_SATIVA_CRUSHED.get(), "Crushed Sativa Seeds");
        add(Registration.SEEDS_SATIVA_TOASTED.get(), "Toasted Sativa Seeds");
        // LIME
        add(Registration.LIME_BURLAP.get(), "Lime Burlap");
        add(Registration.LIME_DRY_HEMP.get(), "Dry Lime Hemp");
        add(Registration.LIME_HEMP_HEARTS.get(), "Lime Hemp Hearts");
        add(Registration.LIME_OIL.get(), "Lime Oil");
        add(Registration.LIME_RAW_HEMP.get(), "Raw Lime Hemp");
        add(Registration.BOWL_LIME_HEMP_HEARTS.get(), "Bowl of Lime Hearts");
        // HEMP
        add(Registration.SEEDS_HEMP_CRUSHED.get(), "Crushed Hemp Seeds");
        add(Registration.SEEDS_HEMP_TOASTED.get(), "Toasted Hemp Seeds");
        add(Registration.HEMP_HEARTS.get(), "Hemp Hearts");
        add(Registration.BUD.get(), "Bud");
        add(Registration.REGS_JOINT.get(), "Regs Joint");
        add(Registration.HEMP_OIL.get(), "Hemp Oil");
        add(Registration.HEMP_MILK_BUCKET.get(), "Hemp Milk");
        add(Registration.HEMP_PAPER.get(), "Hemp Paper");
        add(Registration.BOWL_HEMP_HEARTS.get(), "Bowl of Hemp Hearts");
        add(Registration.DRY_HEMP.get(), "Dry hemp");
        add(Registration.RAW_HEMP.get(), "Raw Hemp");
        add(Registration.SEEDS_HEMP.get(), "Hemp Seeds");
        // VIOLET / INDICA
        add(Registration.VIOLET_RAW_HEMP.get(), "Raw Violet Hemp");
        add(Registration.VIOLET_HEMP_HEARTS.get(), "Violet Hemp Hearts");
        add(Registration.BOWL_VIOLET_HEMP_HEARTS.get(), "Bowl of Violet Hemp Hearts");
        add(Registration.SEEDS_INDICA.get(), "Indica Seeds");
        add(Registration.SEEDS_INDICA_CRUSHED.get(), "Crushed Indica Seeds");
        add(Registration.SEEDS_INDICA_TOASTED.get(), "Toasted Indica Seeds");
        add(Registration.VIOLET_BURLAP.get(), "Violet Burlap");
        add(Registration.VIOLET_OIL.get(), "Violet oil");
        add(Registration.VIOLET_DRY_HEMP.get(), "Dry Violet Hemp");
        add(Registration.INDICA_JOINT.get(), "Indica Joint");
        add(Registration.INDICA_BUD.get(), "Indica Bud");
        // RANDOM
        add(Registration.BROKEN_SUPERIOR_LEAF_WAND.get(), "Broken Superior Leaf Wand");
        add(Registration.LEAF_WAND.get(), "Leaf Wand");
        add(Registration.SUPERIOR_LEAF_WAND.get(), "Superior Leaf Wand");
        add(Registration.POT_BROWNIE.get(), "Pot Brownie");
        add(Registration.RESIN.get(), "Resin");
        add(Registration.BURLAP.get(), "Burlap");
        add(Registration.SHOT_LEAF.get(), "Shot Leaf");
        add(Registration.ROLLING_PAPER.get(), "Rolling Paper");
        add(Registration.RESIN_BURLAP.get(), "Resin Burlap");
        add(Registration.OILY_BURLAP.get(), "Oily Burlap");
        add(Registration.LEAF.get(), "Cannabis Leaf");
        add(Registration.HEMP_CROP.get(), "Hemp Seedling");
        add(Registration.SATIVA_CROP.get(), "Sativa Seedling");
        add(Registration.INDICA_CROP.get(), "Indica Seedling");
        add(Registration.OILY_DIRT.get(), "Oily Dirt");
        add(Registration.RESIN_DIRT.get(), "Resin Dirt");
        add(Registration.LIME_DIRT.get(), "Lime Dirt");
        add(Registration.VIOLET_DIRT.get(), "Violet Dirt");

    }
}
