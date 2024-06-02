package com.Jacob.jacobsmod.entity.client;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.custom.CropDusterEntity;
import com.Jacob.jacobsmod.entity.model.CropDusterModel;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class CropDusterEntityRenderer extends EntityRenderer<CropDusterEntity> {
    private final EntityModel<CropDusterEntity> listModel;
    private static final ResourceLocation TEXTURE = new ResourceLocation(JacobsMod.MODID, "textures/entity/crop_duster.png");
    
    public CropDusterEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;

        listModel = new CropDusterModel(context.bakeLayer(CropDusterModel.LAYER_LOCATION));
    }

    public ResourceLocation getTextureLocation(CropDusterEntity cropDusterEntity) {
        return TEXTURE;
    }

    @Override
    public void render(CropDusterEntity cropDusterEntity, float p_113930_, float p_113931_, PoseStack p_113932_, MultiBufferSource multiBufferSource, int p_113934_) {
        p_113932_.pushPose();
        p_113932_.translate(0.0F, 0.375F, 0.0F);
        p_113932_.mulPose(Axis.YP.rotationDegrees(180.0F - p_113930_));
        float f = (float)cropDusterEntity.getHurtTime() - p_113931_;
        float f1 = cropDusterEntity.getDamage() - p_113931_;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            p_113932_.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)cropDusterEntity.getHurtDir()));
        }

        float f2 = cropDusterEntity.getBubbleAngle(p_113931_);
        if (!Mth.equal(f2, 0.0F)) {
            p_113932_.mulPose((new Quaternionf()).setAngleAxis(cropDusterEntity.getBubbleAngle(p_113931_) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }
        
        p_113932_.scale(-1.0F, -1.0F, 1.0F);
        p_113932_.mulPose(Axis.YP.rotationDegrees(90.0F));
        listModel.setupAnim(cropDusterEntity, p_113931_, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(listModel.renderType(TEXTURE));
        listModel.renderToBuffer(p_113932_, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!cropDusterEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = multiBufferSource.getBuffer(RenderType.waterMask());
            if (listModel instanceof WaterPatchModel) {
                WaterPatchModel waterpatchmodel = (WaterPatchModel)listModel;
                waterpatchmodel.waterPatch().render(p_113932_, vertexconsumer1, p_113934_, OverlayTexture.NO_OVERLAY);
            }
        }

        p_113932_.popPose();
        super.render(cropDusterEntity, p_113930_, p_113931_, p_113932_, multiBufferSource, p_113934_);
    }
}
