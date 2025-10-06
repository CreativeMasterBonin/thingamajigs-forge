package net.rk.thingamajigs.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.rk.thingamajigs.renderers.ThingamajigsBEWLR;

import java.util.function.Consumer;

public class CustomBEWLRBlockItem extends BlockItem {
    public CustomBEWLRBlockItem(Block block, Properties blockProperties) {
        super(block, blockProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ThingamajigsBEWLR bewlr;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(bewlr == null){
                    bewlr = new ThingamajigsBEWLR(
                            Minecraft.getInstance().getBlockEntityRenderDispatcher(),Minecraft.getInstance().getEntityModels());
                }
                return bewlr;
            }
        });
    }
}
