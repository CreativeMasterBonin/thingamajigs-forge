package net.rk.thingamajigs.block;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class AsphaltLayer extends SidewalkLayer{
    public static final EnumProperty<AsphaltAge> AGE = EnumProperty.create("age",AsphaltAge.class);

    public AsphaltLayer(Properties p){
        super(p.mapColor(MapColor.COLOR_BLACK)
                .requiresCorrectToolForDrops()
                .sound(SoundType.TUFF)
                .instrument(NoteBlockInstrument.BASEDRUM));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING,Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(LAYERS,1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    public enum AsphaltAge implements StringRepresentable {
        NEW("new"),
        OK("ok"),
        MEDIOCRE("mediocre"),
        OLD("old");

        final String name;

        AsphaltAge(String sname){
            this.name = sname;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
