package net.rk.thingamajigs.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.rk.thingamajigs.renderers.ThingamajigsBEWLR;

import java.util.function.Consumer;

public class CleverBlackboardBlockItem extends BlockItem {
    public CleverBlackboardBlockItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
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
