package com.Jacob.jacobsmod.entity.model;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.custom.TractorEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TractorModel extends EntityModel<TractorEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JacobsMod.MODID, "tractor"), "main");
	private final ModelPart group;
	private final ModelPart backwheels;
	private final ModelPart frontwheels;

	public TractorModel(ModelPart root) {
		this.group = root.getChild("group");
		this.backwheels = root.getChild("backwheels");
		this.frontwheels = root.getChild("frontwheels");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create().texOffs(38, 27).addBox(-11.0F, 1.0F, -18.0F, 5.0F, 11.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-11.0F, 10.0F, 0.0F, 8.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(58, 22).addBox(-11.0F, 12.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 56).addBox(-5.0F, 11.0F, -11.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 77).addBox(-5.0F, -8.0F, 0.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 77).addBox(-5.0F, -8.0F, 14.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 0).addBox(-3.0F, 10.0F, 14.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-2.0F, 9.0F, 13.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 45).addBox(-11.0F, 1.0F, 0.0F, 6.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-11.0F, -10.0F, -2.0F, 9.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(38, 27).mirror().addBox(-16.0F, 1.0F, -18.0F, 5.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-19.0F, 10.0F, 0.0F, 8.0F, 2.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 22).mirror().addBox(-17.0F, 12.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(34, 56).mirror().addBox(-19.0F, 11.0F, -11.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 77).mirror().addBox(-19.0F, -8.0F, 0.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 77).mirror().addBox(-19.0F, -8.0F, 14.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 0).mirror().addBox(-20.0F, 10.0F, 14.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 45).mirror().addBox(-22.0F, 9.0F, 13.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(22, 45).mirror().addBox(-17.0F, 1.0F, 0.0F, 6.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 24).mirror().addBox(-20.0F, -10.0F, -2.0F, 9.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 2.0F, -5.0F));

		PartDefinition backwheels = partdefinition.addOrReplaceChild("backwheels", CubeListBuilder.create().texOffs(66, 21).addBox(18.0F, 4.0F, -2.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(56, 56).addBox(18.0F, -12.0F, -2.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(18.0F, -7.0F, -7.0F, 7.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(18.0F, -7.0F, 9.0F, 7.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(19.0F, -9.0F, -4.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(28, 77).addBox(19.0F, -9.0F, 8.0F, 5.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 77).addBox(19.0F, -9.0F, -5.0F, 5.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 56).addBox(19.0F, -10.0F, -4.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(54, 8).addBox(19.0F, 3.0F, -4.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(66, 21).mirror().addBox(-9.0F, 4.0F, -2.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 56).mirror().addBox(-9.0F, -12.0F, -2.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-9.0F, -7.0F, -7.0F, 7.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-9.0F, -7.0F, 9.0F, 7.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 45).mirror().addBox(-8.0F, -9.0F, -4.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 77).mirror().addBox(-8.0F, -9.0F, 8.0F, 5.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 77).mirror().addBox(-8.0F, -9.0F, -5.0F, 5.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(34, 56).mirror().addBox(-8.0F, -10.0F, -4.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(54, 8).mirror().addBox(-8.0F, 3.0F, -4.0F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, 16.0F, 8.0F));

		PartDefinition cube_r1 = backwheels.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.0F, -11.3137F, -11.3137F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 24).addBox(24.0F, -11.3137F, -11.3137F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, -2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = backwheels.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-3.0F, -5.6569F, 14.9706F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(66, 31).mirror().addBox(-3.0F, 5.0711F, 4.2426F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(37, 24).mirror().addBox(-3.0F, -5.5858F, -1.4142F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 33).addBox(24.0F, -5.6569F, 14.9706F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(66, 31).addBox(24.0F, 5.0711F, 4.2426F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(37, 24).addBox(24.0F, -5.5858F, -1.4142F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition frontwheels = partdefinition.addOrReplaceChild("frontwheels", CubeListBuilder.create().texOffs(60, 74).addBox(15.0F, -8.0F, -25.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(76, 8).addBox(15.0F, -4.0F, -29.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 33).addBox(15.0F, -4.0F, -17.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 69).addBox(15.0F, 4.0F, -25.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(38, 0).addBox(16.0F, -6.0F, -27.0F, 4.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(60, 74).mirror().addBox(-5.0F, -8.0F, -25.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(76, 8).mirror().addBox(-5.0F, -4.0F, -29.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(37, 33).mirror().addBox(-5.0F, -4.0F, -17.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 69).mirror().addBox(-5.0F, 4.0F, -25.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 0).mirror().addBox(-4.0F, -6.0F, -27.0F, 4.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, 16.0F, 8.0F));

		PartDefinition cube_r3 = frontwheels.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-3.0F, 12.1421F, -5.6569F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(42, 71).mirror().addBox(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 0).addBox(17.0F, 12.1421F, -5.6569F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(42, 71).addBox(17.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, -25.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = frontwheels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 66).mirror().addBox(-3.0F, 7.8995F, 3.8995F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(24, 69).mirror().addBox(-3.0F, -4.2426F, 4.2426F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(62, 66).addBox(17.0F, 7.8995F, 3.8995F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 69).addBox(17.0F, -4.2426F, 4.2426F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, -25.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(TractorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		group.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backwheels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontwheels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}