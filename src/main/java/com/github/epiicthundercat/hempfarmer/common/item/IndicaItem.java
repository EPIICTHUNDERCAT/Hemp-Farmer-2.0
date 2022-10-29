package com.github.epiicthundercat.hempfarmer.common.item;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public class IndicaItem extends ItemNameBlockItem {
    public IndicaItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = this.place(new BlockPlaceContext(context));
        if (result.equals(InteractionResult.FAIL)) {
            Player player = context.getPlayer();
            BlockState targetState = context.getLevel().getBlockState(context.getClickedPos());
            if (player != null && context.getClickedFace().equals(Direction.UP) && (targetState.is(BlockTags.DIRT) || targetState.getBlock() instanceof FarmBlock)) {
                player.displayClientMessage(translate("block.indica.invalid_placement"), true);
            }
        }
        return !result.consumesAction() && this.isEdible() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : result;
    }

    public static MutableComponent translate(String key, Object... args) {
        return new TranslatableComponent(HempFarmer.MODID + "." + key, args);
    }
}