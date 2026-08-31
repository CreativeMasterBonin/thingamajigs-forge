package net.rk.thingamajigs.events;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.GameShuttingDownEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.ThingamajigsClient;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = Thingamajigs.MOD_ID)
public class ThingamajigsEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void close(GameShuttingDownEvent event){
        LogUtils.getLogger().debug("Shutting down narrators...");
        ThingamajigsClient.thingamajigsNarrator.clear();
        ThingamajigsClient.thingamajigsNarrator.destroy();
        LogUtils.getLogger().debug("Done");
    }
}
