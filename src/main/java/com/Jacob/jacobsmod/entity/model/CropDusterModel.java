package com.Jacob.jacobsmod.entity.model;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.custom.CropDusterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;


public class CropDusterModel extends EntityModel<CropDusterEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JacobsMod.MODID, "crop_duster"), "main");
    private final ModelPart boat;

    public CropDusterModel(ModelPart root) {
        this.boat = root.getChild("boat");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition boat = partdefinition.addOrReplaceChild("boat", CubeListBuilder.create().texOffs(18, 21).addBox(15.0F, -15.0F, -8.0F, 1.0F, 15.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(0.0F, -15.0F, -8.0F, 1.0F, 15.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 23.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        boat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(CropDusterEntity cropDusterEntity, float v, float v1, float v2, float v3, float v4) {

    }
}
