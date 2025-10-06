package net.rk.thingamajigs.network.messages;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.rk.thingamajigs.entity.customblock.RailroadCrossingBE;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;

import java.util.function.Supplier;
import java.util.logging.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RailroadCrossingArmUpdateMessage {
    BlockPos bp = new BlockPos(0,0,0);
    float startArmAngle = 0;
    float endArmAngle = 0;
    float gateLength = 0;
    float gateOffset = 0;
    float rotation = 0;

    public RailroadCrossingArmUpdateMessage(){}

    public RailroadCrossingArmUpdateMessage(FriendlyByteBuf buf){
        this.bp = buf.readBlockPos();
        this.startArmAngle = buf.readFloat();
        this.endArmAngle = buf.readFloat();
        this.gateLength = buf.readFloat();
        this.gateOffset = buf.readFloat();
        this.rotation = buf.readFloat();
    }

    public RailroadCrossingArmUpdateMessage(BlockPos bp, float startArmAngle, float endArmAngle, float gateLength,
                                            float gateOffset, float rotation){
        this.bp = bp;
        this.startArmAngle = startArmAngle;
        this.endArmAngle = endArmAngle;
        this.gateLength = gateLength;
        this.gateOffset = gateOffset;
        this.rotation = rotation;
    }

    public static void buffer(RailroadCrossingArmUpdateMessage message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.bp);
        buffer.writeFloat(message.startArmAngle);
        buffer.writeFloat(message.endArmAngle);
        buffer.writeFloat(message.gateLength);
        buffer.writeFloat(message.gateOffset);
        buffer.writeFloat(message.rotation);
    }

    public static void handler(RailroadCrossingArmUpdateMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            RailroadCrossingBE be = (RailroadCrossingBE)entity.level().getBlockEntity(message.bp);
            handleit(entity,be,message.bp,message.startArmAngle,message.endArmAngle,
                    message.gateLength, message.gateOffset,message.rotation
            );
        });
        context.setPacketHandled(true);
    }

    public static void handleit(Player ply, RailroadCrossingBE be, BlockPos bp,
                                float startArmAngle, float endArmAngle, float gateLength,
                                float gateOffset, float rotation){
        Level lvl = ply.level();
        if (!lvl.hasChunkAt(bp)){
            return;
        }
        else{
            RailroadCrossingBE rcbe = be; //(RailroadCrossingBE)lvl.getBlockEntity(bp);
            if(rcbe == null){
                Logger.getAnonymousLogger().warning("Railroad Crossing BE at: " + bp.toShortString() + " is null! This is not normal!");
                return;
            }
            rcbe.armLength = gateLength;
            rcbe.yAngle = rotation;
            rcbe.startingArmAngle = startArmAngle;
            rcbe.endArmAngle = endArmAngle;
            rcbe.armGateOffsetZ = gateOffset;

            rcbe.updateBlock();
            return;
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ThingamajigsPacketHandler.addNetworkMessage(RailroadCrossingArmUpdateMessage.class,
                RailroadCrossingArmUpdateMessage::buffer,
                RailroadCrossingArmUpdateMessage::new,
                RailroadCrossingArmUpdateMessage::handler);
    }
}
