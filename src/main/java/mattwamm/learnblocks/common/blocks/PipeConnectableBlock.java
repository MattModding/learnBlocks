package mattwamm.learnblocks.common.blocks;

import mattwamm.learnblocks.common.blocks.interfaces.IPipeConnectable;
import mattwamm.learnblocks.util.savedata.NetworkSaveManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class PipeConnectableBlock extends Block implements IPipeConnectable {
    public PipeConnectableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean getSideConnection(Direction side, BlockState state) {
        return false;
    }

    @Override
    public boolean isConnected(BlockState state) {
        return false;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        //delete itself from the networks
    }
}
