package net.rk.thingamajigs.network.messages;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.rk.thingamajigs.entity.customblock.DJLaserLightBE;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DJLaserLightUpdateMessage {
    BlockPos bp = new BlockPos(0,0,0);
    boolean customcolors = false; boolean randomcolors = false;
    int height = 0; int color = 0;
    float angle = 0; float vertangle = 0;
    int oscangle = 0; int mm = 0;
    float lasersize = 0; float r = 0; float g = 0; float b = 0;
    int lightmode = 0; int voscangle = 0; int vmm = 0;

    int laserCount = 0;

    public DJLaserLightUpdateMessage(){}

    public DJLaserLightUpdateMessage(FriendlyByteBuf buffer) {
        this.bp = buffer.readBlockPos();
        this.customcolors = buffer.readBoolean();
        this.randomcolors = buffer.readBoolean();
        this.height = buffer.readInt();
        this.color = buffer.readInt();
        this.angle = buffer.readFloat();
        this.vertangle = buffer.readFloat();
        this.oscangle = buffer.readInt();
        this.mm = buffer.readInt();
        this.lasersize = buffer.readFloat();
        this.r = buffer.readFloat();
        this.g = buffer.readFloat();
        this.b = buffer.readFloat();
        this.lightmode = buffer.readInt();
        this.voscangle = buffer.readInt();
        this.vmm = buffer.readInt();
        this.laserCount = buffer.readInt();
    }

    public DJLaserLightUpdateMessage(BlockPos bp,boolean cc,boolean rc,int h,int c,float ang,
                                     float vertang,int oscang,
                                     int mm,float lsize,
                                     float r,float g,float b,int lmode,int voscang,int vmm, int laserCount) {
        if(mm > 32){
            mm = 32;
        }
        else if(mm < 0){
            mm = 0;
        }

        if(vmm > 32){
            vmm = 32;
        }
        else if(vmm < 0){
            vmm = 0;
        }

        if(oscang > 45){
            oscang = 45;
        }
        else if(oscang < 0){
            oscang = 0;
        }

        if(voscang > 45){
            voscang = 45;
        }
        else if(voscang < 0){
            voscang = 0;
        }

        if(h > 32){
            h = 32;
        }
        else if (h < 0) {
            h = 0;
        }

        if(lsize > 3.01){
            lsize = (float)3.01;
        }
        else if (lsize < 0.01) {
            lsize = (float)0.01;
        }

        if(r > 1.0){
            r = (float)1.0;
        }
        else if (r < 0.0) {
            r = (float)0.0;
        }

        if(g > 1.0){
            g = (float)1.0;
        }
        else if (g < 0.0) {
            g = (float)0.0;
        }

        if(b > 1.0){
            b = (float)1.0;
        }
        else if (b < 0.0) {
            b = (float)0.0;
        }
        this.bp = bp;
        this.customcolors = cc;
        this.randomcolors = rc;
        this.height = h;
        this.color = c;
        this.angle = ang;
        this.vertangle = vertang;
        this.oscangle = oscang;
        this.mm = mm;
        this.lasersize = lsize;
        this.r = r;
        this.g = g;
        this.b = b;
        this.lightmode = lmode;
        this.voscangle = voscang;
        this.vmm = vmm;

        if(laserCount > 7){
            laserCount = 7;
        }
        else if(laserCount < 1){
            laserCount = 1;
        }

        this.laserCount = laserCount;
    }

    public static void buffer(DJLaserLightUpdateMessage message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.bp);
        buffer.writeBoolean(message.customcolors);
        buffer.writeBoolean(message.randomcolors);
        buffer.writeInt(message.height);
        buffer.writeInt(message.color);
        buffer.writeFloat(message.angle);
        buffer.writeFloat(message.vertangle);
        buffer.writeInt(message.oscangle);
        buffer.writeInt(message.mm);
        buffer.writeFloat(message.lasersize);
        buffer.writeFloat(message.r);
        buffer.writeFloat(message.g);
        buffer.writeFloat(message.b);
        buffer.writeInt(message.lightmode);
        buffer.writeInt(message.voscangle);
        buffer.writeInt(message.vmm);
        buffer.writeInt(message.laserCount);
    }

    public static void handler(DJLaserLightUpdateMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            DJLaserLightBE be = (DJLaserLightBE)entity.level().getBlockEntity(message.bp);
            handleit(entity,be,message.bp,
                    message.customcolors,message.randomcolors,
                    message.height,message.color,
                    message.angle,message.vertangle,
                    message.oscangle,message.mm,
                    message.lasersize,message.r,message.g,message.b,
                    message.lightmode,message.voscangle,message.vmm,message.laserCount
            );
        });
        context.setPacketHandled(true);
    }

    public static void handleit(Player ply, DJLaserLightBE be, BlockPos bp,
                                boolean cc, boolean rc, int h, int c,
                                float ang, float vertang, int oscang, int mm,
                                float lsize, float r, float g, float b,
                                int lmode, int voscang, int vmm, int laserCount) {
        Level lvl = ply.level();
        if (!lvl.hasChunkAt(bp)){
            return;
        }
        else{
            if(mm > 32){
                mm = 32;
            }
            else if(mm < 0){
                mm = 0;
            }

            if(vmm > 32){
                vmm = 32;
            }
            else if(vmm < 0){
                vmm = 0;
            }

            if(oscang > 45){
                oscang = 45;
            }
            else if(oscang < 0){
                oscang = 0;
            }

            if(voscang > 45){
                voscang = 45;
            }
            else if(voscang < 0){
                voscang = 0;
            }

            if(h > 32){
                h = 32;
            }
            else if (h < 0) {
                h = 0;
            }

            if(lsize > 3.01){
                lsize = (float)3.01;
            }
            else if (lsize < 0.01) {
                lsize = (float)0.01;
            }

            // rgb
            if(r > 1.0){
                r = (float)1.0;
            }
            else if (r < 0.0) {
                r = (float)0.0;
            }

            if(g > 1.0){
                g = (float)1.0;
            }
            else if (g < 0.0) {
                g = (float)0.0;
            }

            if(b > 1.0){
                b = (float)1.0;
            }
            else if (b < 0.0) {
                b = (float)0.0;
            }

            be.useCustomColor = cc;
            be.randomlyGenerateColor = rc;
            be.height = h;
            be.color = c;
            be.angle = ang;
            be.verticalAngle = vertang;
            be.oscAngle = oscang;
            be.degreeOfMotionMultiplier = mm;
            be.laserSize = lsize;
            be.red = r;
            be.green = g;
            be.blue = b;
            be.lightMode = lmode;
            be.vertOscAngle = voscang;
            be.dOfMotMultiVert = vmm;

            if(laserCount > 7){
                laserCount = 7;
            }
            else if (laserCount < 1){
                laserCount = 1;
            }

            be.laserCount = laserCount;

            be.updateBlock();
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ThingamajigsPacketHandler.addNetworkMessage(DJLaserLightUpdateMessage.class,
                DJLaserLightUpdateMessage::buffer,
                DJLaserLightUpdateMessage::new,
                DJLaserLightUpdateMessage::handler);
    }
}
