package com.github.epiicthundercat.hempfarmer.client;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryContainer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class PowerBatteryScreen extends AbstractContainerScreen<PowerBatteryContainer> {

    private static final Identifier BATTERY_GUI = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/gui/power_battery_gui.png");
    private static final Identifier FIRE_SPRITE    = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/sprites/container/power_battery/fire_sprite.png");
    private static final Identifier BATTERY_SPRITE = Identifier.fromNamespaceAndPath(HempFarmer.MODID, "textures/sprites/container/power_battery/battery_sprite.png");

    private static final int FIRE_X = 64, FIRE_Y = 43, FIRE_W = 14, FIRE_H = 14;
    // Battery: outer bounds for hit-test, inner fill rect for rendering
    private static final int BATTERY_X = 110, BATTERY_Y = 14, BATTERY_W = 14, BATTERY_H = 32;
    private static final int FILL_X = 111, FILL_Y = 18, FILL_W = 12, FILL_H = 27;

    public PowerBatteryScreen(PowerBatteryContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.inventoryLabelY = 57;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, BATTERY_GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        super.extractContents(graphics, mouseX, mouseY, partialTicks);

        int energy    = this.menu.getEnergy();
        int maxEnergy = this.menu.getMaxEnergy();

        // Fire indicator — full when fuel is fresh, tip shrinks from top as fuel burns (vanilla furnace pattern)
        int burnTime   = this.menu.getBurnTime();
        int burnLength = this.menu.getBurnLength();
        if (burnLength > 0 && burnTime > 0) {
            int filled = (int) ((float) burnTime / burnLength * FIRE_H);
            if (filled > 0) {
                int skip = FIRE_H - filled;
                graphics.blit(RenderPipelines.GUI_TEXTURED, FIRE_SPRITE,
                        relX + FIRE_X, relY + FIRE_Y + skip,
                        0, skip,
                        FIRE_W, filled,
                        FIRE_W, FIRE_H);
            }
        }

        // Battery fill — always rendered, fills bottom-up from fill_inner_rect
        // Sprite UV: fill inner rect is at sprite offset (FILL_X - BATTERY_X, FILL_Y - BATTERY_Y) = (1, 4)
        if (maxEnergy > 0) {
            int filled = (int) ((float) energy / maxEnergy * FILL_H);
            if (filled > 0) {
                int skip = FILL_H - filled;
                graphics.blit(RenderPipelines.GUI_TEXTURED, BATTERY_SPRITE,
                        relX + FILL_X, relY + FILL_Y + skip,
                        FILL_X - BATTERY_X, (FILL_Y - BATTERY_Y) + skip,
                        FILL_W, filled,
                        BATTERY_W, BATTERY_H);
            }
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
        if (mouseX >= guiLeft + BATTERY_X && mouseX < guiLeft + BATTERY_X + BATTERY_W
                && mouseY >= guiTop + BATTERY_Y && mouseY < guiTop + BATTERY_Y + BATTERY_H) {
            int energy    = this.menu.getEnergy();
            int maxEnergy = this.menu.getMaxEnergy();
            graphics.setTooltipForNextFrame(this.font, Component.literal(energy + " / " + maxEnergy + " FE"), mouseX, mouseY);
        }
    }
}
