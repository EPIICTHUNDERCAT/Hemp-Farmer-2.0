package com.github.epiicthundercat.hempfarmer.blocks.crops;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IndicaCrop extends CropBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D)
    };

    public IndicaCrop(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        return Registration.INDICA_CROP.get().defaultBlockState();
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return Registration.SEEDS_INDICA.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        if (!isMaxAge(state)) {
            return List.of(new ItemStack(Registration.SEEDS_INDICA.get()));
        }
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        List<ItemStack> drops = new ArrayList<>();

        int seeds = CropDropConfig.INDICA_SEED_MIN.get();
        for (int i = seeds; i < CropDropConfig.INDICA_SEED_MAX.get(); i++) {
            if (rand.nextDouble() < CropDropConfig.INDICA_SEED_BONUS_CHANCE.get()) seeds++;
        }
        if (seeds > 0) drops.add(new ItemStack(Registration.SEEDS_INDICA.get(), seeds));

        int budRange = CropDropConfig.INDICA_BUD_MAX.get() - CropDropConfig.INDICA_BUD_MIN.get();
        int buds = CropDropConfig.INDICA_BUD_MIN.get() + (budRange > 0 ? rand.nextInt(budRange + 1) : 0);
        if (buds > 0) drops.add(new ItemStack(Registration.INDICA_BUD.get(), buds));

        int hempRange = CropDropConfig.INDICA_HEMP_MAX.get() - CropDropConfig.INDICA_HEMP_MIN.get();
        int hemp = CropDropConfig.INDICA_HEMP_MIN.get() + (hempRange > 0 ? rand.nextInt(hempRange + 1) : 0);
        if (hemp > 0) drops.add(new ItemStack(Registration.VIOLET_RAW_HEMP.get(), hemp));

        if (rand.nextDouble() < CropDropConfig.INDICA_LEAF_CHANCE.get()) {
            drops.add(new ItemStack(Registration.LEAF.get()));
        }

        return drops;
    }
}