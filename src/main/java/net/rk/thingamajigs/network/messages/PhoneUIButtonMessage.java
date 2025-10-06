package net.rk.thingamajigs.network.messages;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PhoneUIButtonMessage {
    private int buttonID = 0;
    private int x = 0;
    private int y = 0;
    private int z = 0;

    public PhoneUIButtonMessage(){
        // blank constructor
    }

    public PhoneUIButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public PhoneUIButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(PhoneUIButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(PhoneUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            handleButtonAction(entity, buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player ply, int buttonID, int x, int y, int z) {
        Level lvl = ply.level();
        BlockPos bp = new BlockPos(x,y,z);
        //
        if (!lvl.hasChunkAt(bp)){
            return; // yes it IS necessary!
        }
        else{
            //0-11
            if(!lvl.isClientSide){
                if(buttonID == 0){
                    playLocalOrServerSound(false,lvl,bp, ThingamajigsSoundEvents.MOBILE_ONE.get());
                }
                else if(buttonID == 1){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_TWO.get());
                }
                else if(buttonID == 2){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_THREE.get());
                    // last top row
                }
                else if(buttonID == 3){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_FOUR.get());
                }
                else if(buttonID == 4){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_FIVE.get());
                }
                else if(buttonID == 5){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_SIX.get());
                    // last middle row
                }
                else if(buttonID == 6){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_SEVEN.get());
                }
                else if(buttonID == 7){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_EIGHT.get());
                }
                else if(buttonID == 8){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_NINE.get());
                    // last mid-bottom row
                }
                else if(buttonID == 9){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_POUND.get());
                }
                else if(buttonID == 10){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_ZER0.get());
                }
                else if(buttonID == 11){
                    playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.MOBILE_STAR.get());
                    // last bottom row
                }
            }
        }
    }

    public static void playLocalOrServerSound(boolean local, Level l, BlockPos p, SoundEvent event){
        SoundSource ss1 = SoundSource.PLAYERS;
        //
        if(local){
            l.playLocalSound(p,event,ss1,1.0F,1.0F,false);
        }
        else{
            l.playSound(null,p,event,ss1,1.0F,1.0F);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ThingamajigsPacketHandler.addNetworkMessage(PhoneUIButtonMessage.class,
                PhoneUIButtonMessage::buffer,
                PhoneUIButtonMessage::new,
                PhoneUIButtonMessage::handler);
    }
}
