package net.rk.thingamajigs.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.legacy.ClearLanternItemActions;

public class ClearLantern extends Item {
    private final Block pStandingBlock = ThingamajigsBlocks.GROUND_CLEAR_LANTERN.get();
    private final Block pWallBlock = ThingamajigsBlocks.WALL_CLEAR_LANTERN.get();

    public ClearLantern(Properties pProperties) {
        super(pProperties);
    }

    @Override public InteractionResult useOn(UseOnContext context) {
        InteractionResult retval = super.useOn(context);
        ClearLanternItemActions.rightClickOnBlock(
                context.getLevel(),context.getClickedPos().getX(),context.getClickedPos().getY(),context.getClickedPos().getZ(),context.getClickedFace(),context.getPlayer(),context.getItemInHand()
        );
        return retval;
    }
}
