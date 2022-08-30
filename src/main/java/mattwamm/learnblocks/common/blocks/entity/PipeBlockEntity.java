package mattwamm.learnblocks.common.blocks.entity;

import mattwamm.learnblocks.util.registries.BlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class PipeBlockEntity extends BlockEntity {

    private float pressure;
    private float temperature;
    private float insulation;

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.PIPE_BLOCK_ENTITY, pos, state);
        this.pressure = 0;
        this.insulation = 1;
    }


    public void addPressure(float add){
        this.pressure += add;
    }

    public void addTemperature(float add){
        this.temperature += add;
    }

    public void removePressure(float remove){
        this.pressure -= remove;
    }

    public void removeTemperature(float remove){
        this.temperature -= remove;
    }

    public float getPressure(){
        return this.pressure;
    }


    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putFloat("temp",temperature);
        nbt.putFloat("press",pressure);
        nbt.putFloat("ins",insulation);
        super.writeNbt(nbt);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        pressure = nbt.getFloat("press");
        temperature = nbt.getFloat("temp");
        insulation = nbt.getFloat("ins");
    }

}
