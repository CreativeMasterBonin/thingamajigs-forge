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
import java.util.logging.Logger;

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
            String sentNumberOld = number.getString().toLowerCase()
                    .replaceAll("a","2")
                    .replaceAll("b","2")
                    .replaceAll("c","2")
                    .replaceAll("d","3")
                    .replaceAll("e","3")
                    .replaceAll("f","3")
                    .replaceAll("g","4")
                    .replaceAll("h","4")
                    .replaceAll("i","4")
                    .replaceAll("j","5")
                    .replaceAll("k","5")
                    .replaceAll("l","5")
                    .replaceAll("m","6")
                    .replaceAll("n","6")
                    .replaceAll("o","6")
                    .replaceAll("p","7")
                    .replaceAll("r","7")
                    .replaceAll("s","7")
                    .replaceAll("t","8")
                    .replaceAll("u","8")
                    .replaceAll("v","8")
                    .replaceAll("w","9")
                    .replaceAll("x","9")
                    .replaceAll("y","9")
                    .replaceAll("z","")
                    ;
            String sentNumber = number.getString().toLowerCase()
                    .replaceAll("a","2")
                    .replaceAll("b","2")
                    .replaceAll("c","2")
                    .replaceAll("d","3")
                    .replaceAll("e","3")
                    .replaceAll("f","3")
                    .replaceAll("g","4")
                    .replaceAll("h","4")
                    .replaceAll("i","4")
                    .replaceAll("j","5")
                    .replaceAll("k","5")
                    .replaceAll("l","5")
                    .replaceAll("m","6")
                    .replaceAll("n","6")
                    .replaceAll("o","6")
                    .replaceAll("p","7")
                    .replaceAll("q","7")
                    .replaceAll("r","7")
                    .replaceAll("s","7")
                    .replaceAll("t","8")
                    .replaceAll("u","8")
                    .replaceAll("v","8")
                    .replaceAll("w","9")
                    .replaceAll("x","9")
                    .replaceAll("y","9")
                    .replaceAll("z","9")
                    ;
            String areaCode = "0";
            if(number.getString().length() >= 3){
                areaCode = number.getString().substring(0,3);
            }

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
                    else if(sentNumber.equals("0005551234")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.POOP.get());
                        return;
                    }
                    else if((areaCode.equals("212") // ny
                            || areaCode.equals("228")
                            || areaCode.equals("254")
                            || areaCode.equals("260")
                            || areaCode.equals("473")
                            || areaCode.equals("475")
                            || areaCode.equals("477")
                            || areaCode.equals("533")
                            || areaCode.equals("673")
                            || areaCode.equals("674")
                            || areaCode.equals("677")
                            || areaCode.equals("777")
                            || areaCode.equals("982") && ply.isOnFire())){
                        playLocalOrServerSound(false,lvl,bp,SoundEvents.FIRECHARGE_USE);
                        return;
                    }
                    else if(areaCode.equals("911")
                            || areaCode.equals("811")
                            || areaCode.equals("711")
                            || areaCode.equals("611")
                            || areaCode.equals("511")
                            || areaCode.equals("411")
                            || areaCode.equals("311")
                            || areaCode.equals("211")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.CODE.get());
                        return;
                    }
                    else if(sentNumber.equals("") || sentNumber.isEmpty() || sentNumber.isBlank() || sentNumber.equals("-1")){
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_INCOMPLETE_CALL.get());
                        return;
                    }
                    else{
                        playLocalOrServerSound(false,lvl,bp,ThingamajigsSoundEvents.PHONE_NO_SERVICE.get());
                        return;
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
