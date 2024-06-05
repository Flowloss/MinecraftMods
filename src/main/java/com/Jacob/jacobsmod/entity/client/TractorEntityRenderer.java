package com.Jacob.jacobsmod.entity.client;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.custom.TractorEntity;
import com.Jacob.jacobsmod.entity.model.TractorModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TractorEntityRenderer extends EntityRenderer<TractorEntity> {
    private final EntityModel<TractorEntity> listModel;
    private static final ResourceLocation TEXTURE = new ResourceLocation(JacobsMod.MODID, "textures/entity/tractor.png");
    
    public TractorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;

        listModel = new TractorModel(context.bakeLayer(TractorModel.LAYER_LOCATION));
    }

    public ResourceLocation getTextureLocation(TractorEntity tractorEntity) {
        return TEXTURE;
    }

    @Override
    public void render(TractorEntity tractorEntity, float p_113930_, float p_113931_, PoseStack p_113932_, MultiBufferSource multiBufferSource, int p_113934_) {
        p_113932_.pushPose();
//        p_113932_.translate(0.0F, 0.375F, 0.0F);
        p_113932_.translate(0.0F, 1.5F, 0.0F);
//        p_113932_.mulPose(Axis.YP.rotationDegrees(180.0F - p_113930_));
        p_113932_.mulPose(Axis.YP.rotationDegrees(90.0F -  p_113930_));
        float f = (float)tractorEntity.getHurtTime() - p_113931_;
        float f1 = tractorEntity.getDamage() - p_113931_;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            p_113932_.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)tractorEntity.getHurtDir()));
        }
        
        p_113932_.scale(-1.0F, -1.0F, 1.0F);
        p_113932_.mulPose(Axis.YP.rotationDegrees(90.0F));
        listModel.setupAnim(tractorEntity, p_113931_, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(listModel.renderType(TEXTURE));
        listModel.renderToBuffer(p_113932_, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!tractorEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = multiBufferSource.getBuffer(RenderType.waterMask());
            if (listModel instanceof WaterPatchModel) {
                WaterPatchModel waterpatchmodel = (WaterPatchModel)listModel;
                waterpatchmodel.waterPatch().render(p_113932_, vertexconsumer1, p_113934_, OverlayTexture.NO_OVERLAY);
            }
        }

        p_113932_.popPose();
        super.render(tractorEntity, p_113930_, p_113931_, p_113932_, multiBufferSource, p_113934_);
    }
}
