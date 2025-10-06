package net.rk.thingamajigs.interfacing;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

import java.util.Optional;
import java.util.function.Supplier;

public interface WeatheringCopperChair extends ChangeOverTimeBlock<WeatheringCopperChair.RustState> {
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ThingamajigsBlocks.COPPER_CHAIR.get(), ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get())
                .put(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get(), ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get())
                .put(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get(), ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get()).build();
    });
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> {
        return NEXT_BY_BLOCK.get().inverse();
    });

    static Optional<Block> getPrevious(Block previousBlock) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(previousBlock));
    }

    static Block getFirst(Block blockA) {
        Block block = blockA;

        for(Block block1 = PREVIOUS_BY_BLOCK.get().get(blockA); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState p_154900_) {
        return getPrevious(p_154900_.getBlock()).map((p_154903_) -> {
            return p_154903_.withPropertiesOf(p_154900_);
        });
    }

    static Optional<Block> getNext(Block blk) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(blk));
    }

    static BlockState getFirst(BlockState bs5) {
        return getFirst(bs5.getBlock()).withPropertiesOf(bs5);
    }

    default Optional<BlockState> getNext(BlockState gnbs) {
        return getNext(gnbs.getBlock()).map((p_154896_) -> {
            return p_154896_.withPropertiesOf(gnbs);
        });
    }

    default float getChanceModifier() {
        return this.getAge() == WeatheringCopperChair.RustState.UNAFFECTED ? 0.75F : 1.0F;
    }

    enum RustState {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        OXIDIZED;
    }
}
