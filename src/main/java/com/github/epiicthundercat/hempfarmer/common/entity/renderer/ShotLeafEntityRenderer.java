package com.github.epiicthundercat.hempfarmer.common.entity.renderer;

import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
public class ShotLeafEntityRenderer extends ThrownItemRenderer<ShotLeafEntity> {
    public ShotLeafEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
}
