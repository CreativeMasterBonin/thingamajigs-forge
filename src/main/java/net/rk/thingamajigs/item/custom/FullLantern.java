package net.rk.thingamajigs.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.legacy.FullLanternItemActions;

public class FullLantern extends Item {
    private final Block pStandingBlock = ThingamajigsBlocks.GROUND_FULL_LANTERN.get();
    private final Block pWallBlock = ThingamajigsBlocks.WALL_FULL_LANTERN.get();

    public FullLantern(Properties p) {
        super(p);
    }

    @Override public InteractionResult useOn(UseOnContext context) {
        FullLanternItemActions.rightClickOnBlock(
                context.getLevel(),context.getClickedPos().getX(),context.getClickedPos().getY(),context.getClickedPos().getZ(),context.getClickedFace(),context.getPlayer(),context.getItemInHand()
        );
        return InteractionResult.SUCCESS;
    }
}
