package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.TubeManDeco;
import net.rk.thingamajigs.entity.customblock.StopGateBE;

import java.util.Objects;

@SuppressWarnings("deprecated,unused")
public class StopGateBERenderer implements BlockEntityRenderer<StopGateBE>{
    public final Minecraft mc;
    private final BlockRenderDispatcher dispatcher;
    private final ModelBlockRenderer blockRenderer;
    private final BlockModelShaper blockModelShaper;

    public StopGateBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = Objects.requireNonNull(dispatcher.getModelRenderer());
        blockModelShaper = Objects.requireNonNull(mc.getBlockRenderer().getBlockModelShaper());
    }

    @Override
    public void render(StopGateBE stopGateBE, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        BakedModel testRod = blockModelShaper.getBlockModel(Blocks.END_ROD.defaultBlockState());

        poseStack.scale(2.0f,1.0f,1.0f);
        poseStack.rotateAround(Axis.ZP.rotationDegrees(stopGateBE.gateAngle),0.5f,0.5f,0.5f);
        poseStack.translate(0.15D,0D,0D);
        this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                testRod,
                1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());

        poseStack.popPose();
    }

    @Override
    public boolean shouldRender(StopGateBE stopGate, Vec3 vec) {
        return true;
    }

    @Override
    public boolean shouldRenderOffScreen(StopGateBE stopGate) {
        return true;
    }
}
