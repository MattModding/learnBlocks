package mattwamm.learnblocks.common.blocks.blockentities;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.blocks.blockentities.MachineBlockEntity;
import mattwamm.learnblocks.util.registries.modBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import static mattwamm.learnblocks.util.registries.modBlocks.MACHINE;

public class BlockEntityTypes {

    public static BlockEntityType<MachineBlockEntity> MACHINE_BLOCK_ENTITY;

    public static void registerBlockEntityTypes(){
        MACHINE_BLOCK_ENTITY = registerBlockEntityType("machineblockentity", FabricBlockEntityTypeBuilder.create(MachineBlockEntity::new, MACHINE).build(null));
    }


    private static BlockEntityType registerBlockEntityType(String name, BlockEntityType type){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LearnBlocks.MOD_ID,name),type);
    }


}
