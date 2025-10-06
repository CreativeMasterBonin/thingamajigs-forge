package net.rk.thingamajigs.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;

public class ThingamajigsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITY_TYPES, Thingamajigs.MOD_ID);

    // chair entity (shouldn't be spawned by egg or commands, only for internal use by chair block)
    public static final RegistryObject<EntityType<ChairEntity>> CHAIR =
            register("seat",
                    EntityType.Builder.<ChairEntity>of((type, world) -> new ChairEntity(world), MobCategory.MISC)
                            .sized(0.0F, 0.0F)
                            .setCustomClientFactory((spawnEntity, world) -> new ChairEntity(world)));

    public static final RegistryObject<EntityType<StoolEntity>> STOOL =
            register("stool",
                    EntityType.Builder.<StoolEntity>of((type, world) -> new StoolEntity(world), MobCategory.MISC)
                            .sized(0.0F, 0.0F)
                            .setCustomClientFactory((spawnEntity, world) -> new StoolEntity(world)));

    public static final RegistryObject<EntityType<ToiletEntity>> TOILET_ENTITY =
            register("toilet_entity",
                    EntityType.Builder.<ToiletEntity>of((type, world) -> new ToiletEntity(world), MobCategory.MISC)
                            .sized(0.0F, 0.0F)
                            .setCustomClientFactory((spawnEntity, world) -> new ToiletEntity(world)));

    public static final RegistryObject<EntityType<InfiMoveCart>> INFIMOVE_MINECART = register("infimove_minecart",
            EntityType.Builder.<InfiMoveCart>of(InfiMoveCart::new,MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    // entities
}
