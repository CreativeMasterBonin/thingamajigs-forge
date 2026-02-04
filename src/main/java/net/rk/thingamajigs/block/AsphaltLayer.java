package net.rk.thingamajigs.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class AsphaltLayer extends SidewalkLayer{
    public static final EnumProperty<AsphaltAge> AGE = EnumProperty.create("age",AsphaltAge.class);
    public AsphaltLayer(Properties p){
        super(p.mapColor(MapColor.COLOR_BLACK)
                .strength(2f)
                .requiresCorrectToolForDrops()
                .friction(0.4F)
                .sound(SoundType.TUFF)
                .instrument(NoteBlockInstrument.BASEDRUM));
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
