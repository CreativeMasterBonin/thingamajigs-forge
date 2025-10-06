package net.rk.thingamajigs.legacy;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class LoudFireAlarmRedstoneUpdatePro {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        new Object() {
            private int ticks = 0;
            private float waitTicks;
            private LevelAccessor world;

            public void start(LevelAccessor world, int waitTicks) {
                this.waitTicks = waitTicks;
                MinecraftForge.EVENT_BUS.register(this);
                this.world = world;
            }

            @SubscribeEvent
            public void tick(TickEvent.ServerTickEvent event) {
                if (event.phase == TickEvent.Phase.END) {
                    this.ticks += 1;
                    if (this.ticks >= this.waitTicks)
                        run();
                }
            }

            private void run() {
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        /*
                        _level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
                                ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("thingamajigs:code")), SoundSource.BLOCKS, 1, 1);
                         */
                        _level.playSound(null, new BlockPos((int)x, (int)y, (int)z), SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS,1, 1);
                    } else {
                        /*
                        _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("thingamajigs:code")),
                                SoundSource.BLOCKS, 1, 1, false);
                         */
                        _level.playSound(null, new BlockPos((int)x, (int)y, (int)z), SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS,1, 1);
                    }
                }
                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }.start(world, 40);
    }
}
