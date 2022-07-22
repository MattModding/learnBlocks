package mattwamm.learnblocks.common.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MachineBlockEntity extends BlockEntity {

    private int number = 7;
    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.MACHINE_BLOCK_ENTITY, pos, state);
    }

    //call markDirty() every time nbt data is changed to save when the game is closed.
    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("number",number);
        super.writeNbt(nbt);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        number = nbt.getInt("number");
    }

    //for data sync from server to client for rendering and such.
    //Need to call world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS); to trigger the update.
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static void tick(World world, BlockPos pos, BlockState state, MachineBlockEntity be) {
        //doesn't do much yet.
    }

}
