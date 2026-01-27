package net.rk.thingamajigs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ThingamajigsClientConfigs {
    public static class Client {
        public final ForgeConfigSpec.BooleanValue blueTabs;

        Client(ForgeConfigSpec.Builder builder){
            builder.comment(" Thingamajigs Client Config");
            builder.comment(" These settings affect the client.");
            builder.comment(" Be careful to read each config option carefully.");
            builder.comment(" Textured Creative Tabs"); // op blocks toggle
            this.blueTabs = builder
                    .comment(" Enables or disables the creative custom tabs and bg (default: false) NOT WORKING")
                    .comment(" Note: this feature makes the Thingamajigs creative tab look like other vanilla tabs, and requires a restart")
                    .define("blueTabs",false);
        }
    }

    public static final ForgeConfigSpec CPSECCLIENT; // used in thingamajigs setup phase
    public static final Client CLIENT;

    static {
        final Pair<ThingamajigsClientConfigs.Client, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ThingamajigsClientConfigs.Client::new);
        CPSECCLIENT = commonSpecPair.getRight();
        CLIENT = commonSpecPair.getLeft();
    }
}
