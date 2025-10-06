package net.rk.thingamajigs.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class MetallicDoor extends DoorBlock {
    public MetallicDoor(BlockBehaviour.Properties p) {
        super(p.requiresCorrectToolForDrops().strength(4.75F,15F).sound(SoundType.LANTERN).noOcclusion(), BlockSetType.IRON);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.FALSE).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, Boolean.FALSE).setValue(HALF, DoubleBlockHalf.LOWER));
    }
}
