package net.rk.thingamajigs.network.messages;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
    private Component number = Component.empty();

    public PhoneUIButtonMessage(){
        // blank constructor
    }

    public PhoneUIButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.number = buffer.readComponent();
    }

    public PhoneUIButtonMessage(int buttonID, int x, int y, int z, Component number) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.number = number;
    }

    public static void buffer(PhoneUIButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
        buffer.writeComponent(message.number);
    }

    public static void handler(PhoneUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            Component number = message.number;
            handleButtonAction(entity, buttonID, x, y, z, number);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player ply, int buttonID, int x, int y, int z, Component number) {
        Level lvl = ply.level();
        BlockPos bp = new BlockPos(x,y,z);
        //
        if (!lvl.hasChunkAt(bp)){
            return; // yes it IS necessary!
        }
        else{
            String sentNumber = number.getString();

            if(!lvl.isClientSide){
                // for fun numbers
                if(buttonID == 32){
                    if(sentNumber.equals("1111111111")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_NOT_A_NUMBER.get());
                        return;
                    }
                    else if(sentNumber.equals("2307771234")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_SONG_MAYBE.get());
                        return;
                    }
                    else if(sentNumber.equals("5712226788")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_BIRDS_PERHAPS.get());
                        return;
                    }
                    else if(sentNumber.equals("3897043333")){
                        playLocalOrServerSound(false,lvl,bp,SoundEvents.GENERIC_EXPLODE);
                        return;
                    }
                    else if(sentNumber.equals("") || sentNumber.isEmpty() || sentNumber.isBlank() || sentNumber.equals("-1")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_INCOMPLETE_CALL.get());
                        return;
                    }
                    else{
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_NO_SERVICE.get());
                    }
                }

                //0-11
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
            l.playLocalSound(p,event,ss1,1.0f,1.0f,false);
        }
        else{
            l.playSound(null,p,event,ss1,1.0f,1.0f);
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
