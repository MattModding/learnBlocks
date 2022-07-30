package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.blocks.MachineBlock;
import mattwamm.learnblocks.common.blocks.MachineInventoryBlock;
import mattwamm.learnblocks.common.blocks.PipeBlock;
import mattwamm.learnblocks.common.blocks.TankBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    public static final Block MACHINE = new MachineBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Block MACHINE_INVENTORY = new MachineInventoryBlock(FabricBlockSettings.of(Material.METAL).strength(4.0F));
    public static final Block PIPE = new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));

    public static final Block WATER_TANK = new TankBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    //add .requiresTool() to the block settings to activate json needs tool.

    public static void registerBlocks(){
        registerBlock("machine", MACHINE);
        registerBlock("machine_inventory", MACHINE_INVENTORY);
        registerBlock("pipe", PIPE);
        registerBlock("water_tank", WATER_TANK);
    }

    private static void registerBlock(String name, Block block)
    {
        Registry.register(
                Registry.BLOCK,
                new Identifier(LearnBlocks.MOD_ID, name),
                block);
    }


}
