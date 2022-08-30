package mattwamm.learnblocks.common.savedata;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.ForcedChunkState;
import net.minecraft.world.PersistentState;

public class persistentTest extends PersistentState {

    private int integ;

    public persistentTest(int newInteg){
        this.integ = newInteg;

    }
    public int getInteg(){
        return this.integ;
    }


    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("testID",5);
        return null;
    }

    public static persistentTest fromNbt(NbtCompound nbt) {
        return new persistentTest(nbt.getInt("testID"));
    }

    public NbtCompound writeNbtNest(NbtCompound nbt) {
        nbt.putInt("testID",5);
        NbtCompound networkManager = new NbtCompound();

        nbt.put("pipe_networks",networkManager);

        return null;
    }


}
