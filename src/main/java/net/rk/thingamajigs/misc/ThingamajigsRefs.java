package net.rk.thingamajigs.misc;

public class ThingamajigsRefs {
    public static final String[] strRef = {
            "thingamajigs_block",
            "thingamajigs_item",
            "thingamajigs_be",
            "thingamajigs_ent",
            "thingamajigs_ext"
    };

    // refs and research for mod
    /*
        Mob Effect Instance constructor ref

        effect
        duration
        amplifier
        ambient
        visible
        showIcon
        hiddenEffect
        factorData
    */

    /*
        ForgeFlowingFluid setup ref

        type = fluidType
        still_provider = fluid.source
        flowing_provider = fluid.flowing
        (other properties)
        bucket_item = Item
        liquid_block = Block
    */

    // old firework display code

    /*
    public void fireRandomRocket(BlockState blkState, Level world1, BlockPos blockPos1){
        int x = blockPos1.getX();
        int y = blockPos1.getY();
        int z = blockPos1.getZ();

        Random rnd1 = new Random();
        int rnum1 = rnd1.nextInt(12);
        if (world1 instanceof ServerLevel serverLevel){
            if(rnum1 == 0){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:2,Colors:[I;16742144,16711680],FadeColors:[I;15073024]}]}}}}");
            }
            else if(rnum1 == 1){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:0,Trail:1b,Colors:[I;7996927],FadeColors:[I;16734123]}]}}}}");
            }
            else if(rnum1 == 2){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:4,Flicker:1b,Colors:[I;5635840,65433],FadeColors:[I;65527]}]}}}}");
            }
            else if(rnum1 == 3){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:3,Colors:[I;2303]}]}}}}");
            }
            else if(rnum1 == 4){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Colors:[I;16744015],FadeColors:[I;13681407]}]}}}}");
            }
            else if(rnum1 == 5){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Flicker:1b,Trail:1b,Colors:[I;16770563,50175],FadeColors:[I;16580576,2373555]}]}}}}");
            }
            else if(rnum1 == 6){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:0,Trail:1b,Colors:[I;9904639]}]}}}}");
            }
            else if(rnum1 == 7){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Colors:[I;16717032]}]}}}}");
            }
            else if(rnum1 == 8){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:4,Flicker:1b,Colors:[I;12517293],FadeColors:[I;13106687]}]}}}}");
            }
            else if(rnum1 == 9){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:2,Trail:1b,Colors:[I;15597457]}]}}}}");
            }
            else if(rnum1 == 10){
                Objects.requireNonNull(world1.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:3,Flicker:1b,Colors:[I;32029]}]}}}}");
            }
            else{
                Objects.requireNonNull(world1.getServer()).getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world1).getLevel(), 4, "", Component.literal(""), world1.getServer(), null).withSuppressedOutput(), "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Flicker:1b,Trail:1b,Colors:[I;65306],FadeColors:[I;4607]}]}}}}");
            }
            //
        }
    }
    */

    // more old code

    /*
        if (world instanceof ServerLevel serverLevel){
            if(rn == 0){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:2,Colors:[I;16742144,16711680],FadeColors:[I;15073024]}]}}}}");
            }
            else if(rn == 1){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:0,Trail:1b,Colors:[I;7996927],FadeColors:[I;16734123]}]}}}}");
            }
            else if(rn == 2){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:4,Flicker:1b,Colors:[I;5635840,65433],FadeColors:[I;65527]}]}}}}");
            }
            else if(rn == 3){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:3,Colors:[I;2303]}]}}}}");
            }
            else if(rn == 4){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Colors:[I;16744015],FadeColors:[I;13681407]}]}}}}");
            }
            else if(rn == 5){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Flicker:1b,Trail:1b,Colors:[I;16770563,50175],FadeColors:[I;16580576,2373555]}]}}}}");
            }
            else if(rn == 6){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:0,Trail:1b,Colors:[I;9904639]}]}}}}");
            }
            else if(rn == 7){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Colors:[I;16717032]}]}}}}");
            }
            else if(rn == 8){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:4,Flicker:1b,Colors:[I;12517293],FadeColors:[I;13106687]}]}}}}");
            }
            else if(rn == 9){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:2,Trail:1b,Colors:[I;15597457]}]}}}}");
            }
            else if(rn == 10){
                Objects.requireNonNull(world.getServer()).getCommands().
                        performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput()
                                , "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:3,Flicker:1b,Colors:[I;32029]}]}}}}");
            }
            else{
                Objects.requireNonNull(world.getServer()).getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, ((ServerLevel) world).getLevel(), 4, "", Component.literal(""), world.getServer(), null).withSuppressedOutput(), "/summon firework_rocket ~ ~2 ~ {LifeTime:30,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:1,Flicker:1b,Trail:1b,Colors:[I;65306],FadeColors:[I;4607]}]}}}}");
            }
            //
            return InteractionResult.SUCCESS;
        }
        else{
            return InteractionResult.CONSUME;
        }
        */

    // ??? idk WHAT the heck this was supposed to be or do
    /*
    public void ignoreMeowing(){
        CompoundTag explosion = new CompoundTag();
        CompoundTag attributes = new CompoundTag();
        List<Integer> colorsList = Lists.newArrayList();
        colorsList.add(((DyeItem)itemStack.getItem()).getDyeColor().getFireworkColor());
    }
    */
}
