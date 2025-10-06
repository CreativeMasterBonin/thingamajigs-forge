package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.block.*;

public class DyedStemGrownBlock extends StemGrownBlock {
    public static Block stem = Blocks.PUMPKIN_STEM;
    public static Block attachedStem = Blocks.ATTACHED_PUMPKIN_STEM;

    public DyedStemGrownBlock(Properties p) {
        super(p);
    }

    public DyedStemGrownBlock(Properties p, Block sb, Block asb) {
        super(p);
        stem = sb;
        attachedStem = asb;
    }

    @Override
    public StemBlock getStem() {
        return (StemBlock)stem;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock)attachedStem;
    }
}
