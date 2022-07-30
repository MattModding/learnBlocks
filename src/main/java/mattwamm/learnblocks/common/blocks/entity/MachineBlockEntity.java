package mattwamm.learnblocks.common.blocks.entity;

import mattwamm.learnblocks.common.blocks.interfaces.ImplementedInventory;
import mattwamm.learnblocks.util.registries.BlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MachineBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int number = 7;



    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.MACHINE_BLOCK_ENTITY, pos, state);
    }

    //call markDirty() every time nbt data is changed to save when the game or server is closed.
    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        nbt.putInt("number",number);
        super.writeNbt(nbt);                //then write the NbtCompound
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);                 //first read the NbtCompound
        number = nbt.getInt("number");
        Inventories.readNbt(nbt, items);
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

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        // Just return an array of all slots
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @org.jetbrains.annotations.Nullable Direction dir) {
        return dir != Direction.UP;

    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }
}
