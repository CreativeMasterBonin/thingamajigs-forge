package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ElectricHospitalBed extends ThingamajigsBedBlock{
    public ElectricHospitalBed(Properties properties) {
        super(properties.sound(SoundType.METAL).instrument(NoteBlockInstrument.DIDGERIDOO).pushReaction(PushReaction.BLOCK)
                .strength(1.1f,3f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter lvl, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH_ELECTRIC_SHAPE;}
            case SOUTH -> {return SOUTH_ELECTRIC_SHAPE;}
            case EAST -> {return EAST_ELECTRIC_SHAPE;}
            case WEST -> {return WEST_ELECTRIC_SHAPE;}
            default -> {return Shapes.block();}
        }
    }
}
