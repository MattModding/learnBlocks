package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.blocks.entity.MachineBlockEntity;
import mattwamm.learnblocks.common.blocks.entity.PipeBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import static mattwamm.learnblocks.util.registries.ModBlocks.*;

public class BlockEntityTypes {

    public static BlockEntityType<MachineBlockEntity> MACHINE_BLOCK_ENTITY;
    public static BlockEntityType<MachineBlockEntity> MACHINE_BLOCK_INVENTORY_ENTITY;

    public static BlockEntityType<MachineBlockEntity> PIPE_BLOCK_ENTITY;

    public static void registerBlockEntityTypes(){
        MACHINE_BLOCK_ENTITY = registerBlockEntityType("machineblockentity", FabricBlockEntityTypeBuilder.create(MachineBlockEntity::new, MACHINE).build(null));
        MACHINE_BLOCK_INVENTORY_ENTITY = registerBlockEntityType("machineblockinventoryentity", FabricBlockEntityTypeBuilder.create(MachineBlockEntity::new, MACHINE_INVENTORY).build(null));
        PIPE_BLOCK_ENTITY = registerBlockEntityType("pipeblockentity", FabricBlockEntityTypeBuilder.create(PipeBlockEntity::new, PIPE).build(null));

    }


    private static BlockEntityType registerBlockEntityType(String name, BlockEntityType type){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LearnBlocks.MOD_ID,name),type);
    }


}
