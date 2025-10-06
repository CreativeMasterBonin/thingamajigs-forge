package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.rk.thingamajigs.entity.customblock.ItemDisplayBE;
import org.joml.Quaternionf;

import java.util.logging.Logger;

public class ItemDisplayBERenderer implements BlockEntityRenderer<ItemDisplayBE> {
    public ItemDisplayBERenderer(BlockEntityRendererProvider.Context berpContext) {}

    // thx quat and others on neodisc for spinning help

    @Override
    public void render(ItemDisplayBE itemdbe, float ptick, PoseStack ps1, MultiBufferSource buf1, int pl, int po) {
        try{
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack itemStack = itemdbe.getRenderStack();

            ps1.pushPose();

            ps1.translate(0.5f, 0.5f, 0.5f);

            ps1.mulPose(new Quaternionf().rotationY(itemdbe.rot * (float) (Math.PI / 270.0))); // spinnnnnnnnnnn!

            ps1.scale(0.46f, 0.46f, 0.46f);

            if(!itemdbe.hidePose){
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(itemdbe.getLevel(), itemdbe.getBlockPos()),
                        OverlayTexture.NO_OVERLAY, ps1, buf1, itemdbe.getLevel(), 1);
            }

            ps1.popPose();
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs couldn't render the ItemDisplayrendered BE item!" + e.getMessage());
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
