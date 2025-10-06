package net.rk.thingamajigs.screen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.MailboxBlockEntity;

public class ThingamajigsMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES, Thingamajigs.MOD_ID);

    // new registry obj
    public static final RegistryObject<MenuType<MailboxMenu>> MAILBOX_MENU = register("mailbox_menu",
            (IContainerFactory<MailboxMenu>) (id, inv1, yourData) -> {
        MailboxBlockEntity mbbe = (MailboxBlockEntity) inv1.player.level().getBlockEntity(yourData.readBlockPos());
        // ignore warnings as this method allows for better implementation for disabling features
        return new MailboxMenu(id, inv1, mbbe);
    }
    );

    public static final RegistryObject<MenuType<PhoneMenu>> PHONE_MENU =
            MENU_TYPES.register("phone_menu", () -> IForgeMenuType.create(PhoneMenu::new));

    public static final RegistryObject<MenuType<DJLaserLightMenu>> DJ_BE_MENU =
            MENU_TYPES.register("dj_be_menu", () -> IForgeMenuType.create(DJLaserLightMenu::new));

    public static final RegistryObject<MenuType<RailroadCrossingArmMenu>> RAILROAD_CROSSING_MENU =
            MENU_TYPES.register("railroad_crossing_arm_menu", () -> IForgeMenuType.create(RailroadCrossingArmMenu::new));


    // original implementation method
    @Deprecated
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENU_TYPES.register(name,() -> IForgeMenuType.create(factory));
    }

    // test method
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String key, MenuType.MenuSupplier<T> supplier) {
        return MENU_TYPES.register(key, () -> new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS));
    }

    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }
}
