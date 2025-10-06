package net.rk.thingamajigs.interfacing;

import net.minecraft.util.StringRepresentable;


public class CrossingEnums{
    public static final StringRepresentable.EnumCodec<CrossingEnums.CrossingState> CODEC = StringRepresentable.fromEnum(CrossingEnums.CrossingState::values);
    public enum CrossingState implements StringRepresentable {
        CLOSED("closed"),
        CLOSING("closing"),
        OPEN("open"),
        OPENING("opening");

        public String name;

        CrossingState(String str) {
            this.name = str;
        }

        public String convertToString(CrossingState state) {
            switch (state) {
                case OPEN -> {
                    name = "open";
                    return "open";
                }
                case OPENING -> {
                    name = "opening";
                    return "opening";
                }
                case CLOSED -> {
                    name = "closed";
                    return "closed";
                }
                case CLOSING -> {
                    name = "closing";
                    return "closing";
                }
            }
            return "none";
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}