package mattwamm.learnblocks.common.blocks.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public interface IPipeConnectable {


    /**
     * @param side the opposite side of the side you interact with: hitside.getOpposite()
     * @param state the block you are connecting this block to
     * @return returns if this side of the block is connectable
     * this method should be used to check if a block can connect to a side of a connectable block
     */
    public boolean getSideConnection(Direction side, BlockState state);
    /**
     * @param state the BlockState this block checks connection to at all
     * @return returns if this state is connected
     * this method should be used to check if a block is connected in any way to another block
     */
    public boolean isConnected(BlockState state);





}
