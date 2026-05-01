package com.github.epiicthundercat.hempfarmer.client.grinder;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderContainer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class GrinderScreen extends AbstractContainerScreen<GrinderContainer> {

    private static final Identifier GRINDER_GUI = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/gui/grinder_gui.png");
    private static final Identifier GRINDER_SPRITE = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/sprites/container/grinder/grinder_sprite.png");
    private static final Identifier BOLT_SPRITE = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/sprites/container/grinder/bolt_sprite.png");

    // From design spec
    private static final int GRINDER_X = 82, GRINDER_Y = 32, GRINDER_W = 14, GRINDER_H = 14;
    private static final int BOLT_X = 144, BOLT_Y = 22, BOLT_W = 16, BOLT_H = 24;

    public GrinderScreen(GrinderContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.inventoryLabelY = 57;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GRINDER_GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        super.extractContents(graphics, mouseX, mouseY, partialTicks);

        // Grinder progress — fills bottom-up as recipe progresses (grindTime counts down from grindLength)
        int grindTime   = this.menu.getGrindTime();
        int grindLength = this.menu.getGrindLength();
        if (grindLength > 0 && grindTime >= 0) {
            int progress = (int) ((float)(grindLength - grindTime) / grindLength * GRINDER_H);
            if (progress > 0) {
                int skip = GRINDER_H - progress;
                graphics.blit(RenderPipelines.GUI_TEXTURED, GRINDER_SPRITE,
                        relX + GRINDER_X, relY + GRINDER_Y + skip,
                        0, skip, GRINDER_W, progress, GRINDER_W, GRINDER_H);
            }
        }

        // Bolt — fills bottom-up as energy increases, like a furnace progress bar
        int energy    = this.menu.getEnergy();
        int maxEnergy = this.menu.getMaxEnergy();
        if (energy > 0 && maxEnergy > 0) {
            int filled = Math.max(1, (int) ((float) energy / maxEnergy * BOLT_H));
            int skip   = BOLT_H - filled;
            graphics.blit(RenderPipelines.GUI_TEXTURED, BOLT_SPRITE,
                    relX + BOLT_X, relY + BOLT_Y + skip,
                    0, skip, BOLT_W, filled, BOLT_W, BOLT_H);
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractLabels(graphics, mouseX, mouseY);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        int guiLeft = (this.width - this.imageWidth) / 2;
        int guiTop  = (this.height - this.imageHeight) / 2;
        if (mouseX >= guiLeft + BOLT_X && mouseX < guiLeft + BOLT_X + BOLT_W
                && mouseY >= guiTop + BOLT_Y && mouseY < guiTop + BOLT_Y + BOLT_H) {
            int energy    = this.menu.getEnergy();
            int maxEnergy = this.menu.getMaxEnergy();
            graphics.setTooltipForNextFrame(this.font, Component.literal(energy + " / " + maxEnergy + " FE"), mouseX, mouseY);
        }
    }
}
