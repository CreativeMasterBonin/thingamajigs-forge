package net.rk.thingamajigs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ThingamajigsClientConfigs {

    public static class Client {
        public final ForgeConfigSpec.BooleanValue blueTabs;
        public final ForgeConfigSpec.IntValue configOffsetX;
        public final ForgeConfigSpec.IntValue configOffsetY;

        Client(ForgeConfigSpec.Builder builder){
            builder.comment(" Thingamajigs Client Config");
            builder.comment(" These settings affect the client.");
            builder.comment(" Be careful to read each config option carefully.");
            builder.comment(" Textured Creative Tabs"); // op blocks toggle
            this.blueTabs = builder
                    .comment(" Enables or disables the creative custom tabs and bg (default: false) NOT WORKING")
                    .comment(" Note: this feature makes the Thingamajigs creative tab look like other vanilla tabs, and requires a restart")
                    .define("blueTabs",false);

            builder.comment(" Config Screen X Offset");
            this.configOffsetX = builder
                    .comment(" The x offset of the custom config screen (default is -75)")
                    .comment(" DO NOT CHANGE IF YOU DON'T KNOW WHAT YOU ARE DOING!")
                    .defineInRange("configOffsetX",-75,-300,300);

            builder.comment(" Config Screen Y Offset");
            this.configOffsetY = builder
                    .comment(" The y offset of the custom config screen (default is -110)")
                    .comment(" DO NOT CHANGE IF YOU DON'T KNOW WHAT YOU ARE DOING!")
                    .defineInRange("configOffsetY",-110,-300,300);
        }
    }

    public static final ForgeConfigSpec CPSECCLIENT; // used in thingamajigs setup phase
    public static final Client CLIENT;

    public static void setBlueTabs(boolean newValue){
        CLIENT.blueTabs.set(newValue);
    }

    public static void setConfigOffsetX(int offsetX){
        CLIENT.configOffsetX.set(offsetX);
    }

    public static void setConfigOffsetY(int offsetY){
        CLIENT.configOffsetY.set(offsetY);
    }

    public static void saveConfig(){
        CPSECCLIENT.save();
    }

    static {
        final Pair<ThingamajigsClientConfigs.Client, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ThingamajigsClientConfigs.Client::new);
        CPSECCLIENT = commonSpecPair.getRight();
        CLIENT = commonSpecPair.getLeft();
    }
}
