package mattwamm.learnblocks.common.blocks;

import mattwamm.learnblocks.common.blocks.interfaces.IPipeConnectable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class TankBlock extends Block implements IPipeConnectable {
    public TankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean getSideConnection(Direction side, BlockState state) {
        return side == Direction.DOWN || side == Direction.UP;
    }

    @Override
    public boolean isConnected(BlockState state) {
        return false;
    }
}
