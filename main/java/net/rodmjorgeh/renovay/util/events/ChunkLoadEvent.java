package net.rodmjorgeh.renovay.util.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.rodmjorgeh.renovay.RenovayMod;
import oshi.util.tuples.Pair;

import java.util.*;

@EventBusSubscriber(modid = RenovayMod.MOD_ID)
public class ChunkLoadEvent {
    private static final Map<ChunkPos, List<Pair<BlockPos, BlockState>>> chunkToBlock = new HashMap<>();

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        LevelChunk chunk = event.getChunk();
        LevelAccessor level = event.getLevel();
        ChunkPos pos = chunk.getPos();

        if (toLoadChunk(pos)) {
            Iterator<Pair<BlockPos, BlockState>> iter = chunkToBlock.get(pos).iterator();

            while (iter.hasNext()) {
                Pair<BlockPos, BlockState> pair = iter.next();
                BlockPos blockPos = pair.getA();
                int x = SectionPos.blockToSectionCoord(blockPos.getX());
                int z = SectionPos.blockToSectionCoord(blockPos.getZ());

                if (pos.x == x && pos.z == z) {
                    level.setBlock(blockPos, pair.getB(), 2);
                }
            }

            chunkToBlock.remove(pos);
        }
    }

    public static void add(ChunkPos chunkPos, BlockPos pos, BlockState state) {
        Pair<BlockPos, BlockState> pair = new Pair<>(pos, state);

        if (toLoadChunk(chunkPos)) {
            chunkToBlock.get(chunkPos).add(pair);
        }
        else {
            List<Pair<BlockPos, BlockState>> listToAdd = new ArrayList<>();
            listToAdd.add(pair);

            chunkToBlock.put(chunkPos, listToAdd);
        }
    }

    private static boolean toLoadChunk(ChunkPos pos) {
        return chunkToBlock.containsKey(pos);
    }
}
