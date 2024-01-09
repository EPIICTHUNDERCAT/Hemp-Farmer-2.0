package com.github.epiicthundercat.hempfarmer.common.entity.renderer;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import com.github.epiicthundercat.hempfarmer.setup.ModSetup;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShotLeafEntityRenderer extends EntityRenderer<ShotLeafEntity> {

    protected static ResourceLocation TEXTURE = new ResourceLocation(HempFarmer.MODID, "textures/entity/shot_leaf.png");
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
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        this.itemRenderer.renderStatic(pEntity.getItem(), ItemTransforms.TransformType.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.getId());
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }


}
