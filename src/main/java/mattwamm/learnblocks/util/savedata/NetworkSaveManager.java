package mattwamm.learnblocks.util.savedata;


import mattwamm.learnblocks.common.blocks.PipeConnectableBlock;
import mattwamm.learnblocks.common.savedata.persistentTest;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class NetworkSaveManager extends PersistentState {
        // 0:"END", 1:"BYTE", 2:"SHORT", 3:"INT", 4:"LONG", 5:"FLOAT", 6:"DOUBLE", 7:"BYTE[]", 8:"STRING", 9:"LIST", 10:"COMPOUND", 11:"INT[]"\
        // 0:"PIPE", 1:"WATERTANK", 2:"BOILER", 3:"PUMP", 4:"VALVE"
        private static final String managerID = "pipeNetworks";
        private static final String blockLocations = "locations";
        private static final String blockNetworks = "networks";
        private static int networkNumber = 0;


        private static NbtCompound nbtCompound;
        public static NetworkSaveManager getNetworkSaveManager(NbtCompound nbt)
        {
                nbtCompound = nbt;
                return new NetworkSaveManager();
        }

        public static NbtCompound getNbt()
        {
                return nbtCompound;
        }


        public static void addNetwork(ServerWorld world){

        }
        public static void mergeNetwork(ServerWorld world){

        }
        public static void deleteNetwork(ServerWorld world){

        }

        public static void addBlock(ServerWorld world, BlockState state, BlockPos pos){
                String uniqueID = getLocationId(pos);
                NbtCompound compound = new NbtCompound();

                world.getPersistentStateManager().get(NetworkSaveManager::getNetworkSaveManager,managerID);

                NbtCompound blocks = getNbt().getCompound(blockLocations);
                NbtCompound networks = getNbt().getCompound(blockNetworks);


                //check all blocks around it for already existing networks
                //check if blocks are connectable from that side

                List<Integer> nearNetworks = new ArrayList<>();
                int biggestNetwork = 0;
                int biggestSize = 0;
                for (Direction dir: Direction.values()) {
                        BlockPos ofPos = pos.offset(dir);
                        BlockState worldState = world.getBlockState(ofPos);
                        if(state.getBlock() instanceof PipeConnectableBlock pipeConnectableBlock && pipeConnectableBlock.getSideConnection(dir.getOpposite(),worldState))
                        {
                                int networkNumber = ((NbtCompound) Objects.requireNonNull(blocks.get(getLocationId(ofPos)))).getInt("network");
                                if(!nearNetworks.contains((Integer)networkNumber))
                                {
                                       nearNetworks.add(networkNumber);
                                       int networkSize = ((NbtCompound) Objects.requireNonNull(networks.get(networkNumber + ""))).getInt("size");
                                       if(networkSize > biggestSize)
                                       {
                                               biggestNetwork = networkNumber;
                                               biggestSize = networkSize;
                                       }
                                }
                        }
                }

                //putting data in the compound
                compound.putInt("network", biggestNetwork);
                compound.putInt("typeBlock",pipeType.fromName(state.getBlock().getTranslationKey()));
                compound.putLongArray("position", (List<Long>) pos);

                //attach networks together by putting smaller networks into the larger network

                if(blocks != null){
                        blocks.put(uniqueID,compound);
                }
        }

        private static String getLocationId(BlockPos pos){
                return pos.getX() + "," + pos.getZ() + "," + pos.getX();
        }



        @Override
        public NbtCompound writeNbt(NbtCompound nbt) {
                return null;
        }

        public enum pipeType{
                PIPE(0,"block.learnblocks.pipe"),
                WATER_TANK(1, "block.learnblocks.water_tank"),
                BOILER(2, ""),
                VALVE(3, ""),
                PUMP(4, "")

                ;
                private final int blockType;
                private final String blockName;
                private final static Map<Integer, pipeType> map =
                        stream(pipeType.values()).collect(toMap(i -> i.blockType, i -> i));
                pipeType(int i, String name) {
                        blockType = i;
                        blockName = name;
                }
                public static pipeType valueOf(int index){
                        return map.get(index);
                }

                public static int fromName(String translatable){
                        for (pipeType type: pipeType.values()) {
                                if(type.blockName == translatable);
                                {
                                 return type.ordinal();
                                }
                        }
                        return -1;
                }



        }


}

