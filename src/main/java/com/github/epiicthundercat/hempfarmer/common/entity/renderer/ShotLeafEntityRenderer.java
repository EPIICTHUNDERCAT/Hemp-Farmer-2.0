package com.github.epiicthundercat.hempfarmer.common.entity.renderer;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShotLeafEntityRenderer extends EntityRenderer<ShotLeafEntity> {

    protected static ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID, "textures/entity/shot_leaf.png");
    private float scale;
    private ItemRenderer itemRenderer;

    public ShotLeafEntityRenderer(EntityRendererProvider.Context pContext, float pScale) {
        super(pContext);
        this.scale = pScale;
        this.itemRenderer = pContext.getItemRenderer();
    }

    public ShotLeafEntityRenderer(EntityRendererProvider.Context pContext) {
        this(pContext, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(ShotLeafEntity pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(ShotLeafEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(this.scale, this.scale, this.scale);
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        this.itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, null, pEntity.getId());
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
