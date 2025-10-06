package net.rk.thingamajigs.xtrablock;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;

public class DyedEquipableCarvedPumpkinBlock extends DyedCarvedPumpkinBlock implements Equipable {
    public DyedEquipableCarvedPumpkinBlock(Properties p) {
        super(p);
    }

    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.PUMPKIN_CARVE;
    }
}
