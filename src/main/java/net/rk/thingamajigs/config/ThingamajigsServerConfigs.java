package net.rk.thingamajigs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ThingamajigsServerConfigs {
    // Common configs (server and client shared, must be synced)
    // server must require users to match configs here or there will be issues
    // this means the server should OVERRIDE users' configs when in multiplayer mode

    public static class ThingamajigsCommon
    {
        // values to store in config file for modification
        public final ForgeConfigSpec.BooleanValue opBlocksEnabled;
        public final ForgeConfigSpec.IntValue maxThingamajigsStackSize;
        public final ForgeConfigSpec.IntValue mechanicalBellSpeed;
        public final ForgeConfigSpec.IntValue electronicBellSpeed;

        public final ForgeConfigSpec.IntValue britAlarmSpeed;

        public final ForgeConfigSpec.BooleanValue moneyExchangeEnabled;

        // setup config descriptions, titles, and options to change here

        // Format (in file) is:
        // note that # + String will have no space between them
        // a PUSH in the builder will add a [title] after the comment it is attached to
        // it will require a POP at the end to remove it
        /*
            # What this section is
            [title of option types]
            # What this config option does
            The config option (=, [], etc.) changeable value
        */
        ThingamajigsCommon(ForgeConfigSpec.Builder builder)
        {
            // Config Options List
            builder.comment(" Thingamajigs Common Config");
            builder.comment(" These settings affect both server and client. Some settings will require a world or game restart.");
            builder.comment(" Be careful to read each config option carefully (joining to server allows the server to override these settings with it's own).");
            builder.comment(" Special Map Builder Blocks In Creative Tab"); // op blocks toggle
            this.opBlocksEnabled = builder
                    .comment(" Enables or disables the special map maker blocks in the OP Blocks creative tab. (default: false)")
                    .comment(" Note: You need operator permissions to actually see the blocks in the creative tab.")
                    .define("opBlocksEnabled",false);

            builder.comment(" Max Stack Size For Thingamajig"); // thingamajig item stack amount
            this.maxThingamajigsStackSize = builder
                    .comment(" Sets the max stack size of the Thingamajig item.")
                    .comment(" Note: Requires a world restart.")
                    .defineInRange("maxThingamajigsStackSize",64, 16, 64);

            builder.comment(" Mechanical Bells Tick Speed"); // mech bell speed
            this.mechanicalBellSpeed = builder
                    .comment(" Sets the tick speed of Mechanical Bells")
                    .comment(" Warning: Setting this low will make it ring faster, but tick more often.")
                    .defineInRange("mechanicalBellSpeed",10, 8, 16);

            builder.comment(" Electronic Bells Tick Speed"); // elec bell speed
            this.electronicBellSpeed = builder
                    .comment(" Sets the tick speed of Electronic Bells")
                    .comment(" Warning: Setting this low will make it ring faster, but tick more often.")
                    .defineInRange("electronicBellSpeed",8, 8, 16);

            builder.comment(" British Alarm Tick Speed"); // brit alarm speed
            this.britAlarmSpeed = builder
                    .comment(" Sets the tick speed of Railway Alarms")
                    .comment(" Warning: Setting this low will make it sound faster, but tick more often.")
                    .defineInRange("britAlarmSpeed",10, 8, 16);
            builder.comment(" Money Exchanging"); // money exchange mechanic
            this.moneyExchangeEnabled = builder
                    .comment(" Enables the money exchanging mechanic. Basically makes the change machine useful or not.")
                    .define("moneyExchangeEnabled",false);
        }
    }

    // 0.5 sec => ~10 ticks

    // only Thingamajigs should ever need to access the config options here
    public static final ForgeConfigSpec CPSEC; // used in thingamajigs setup phase
    public static final ThingamajigsServerConfigs.ThingamajigsCommon COMMON; // used internally in this class

    // common way to setup pairs of configs in Forge (a way is shown in documentation)
    static {
        final Pair<ThingamajigsCommon, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ThingamajigsServerConfigs.ThingamajigsCommon::new);
        CPSEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
