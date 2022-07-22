package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.blocks.MachineBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class modBlocks {

    public static final Block MACHINE = new MachineBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    //add .requiresTool() to the block settings to activate json needs tool.

    public static void registerBlocks(){
        registerBlock("machine", MACHINE);

    }

    private static void registerBlock(String name, Block block)
    {
        Registry.register(
                Registry.BLOCK,
                new Identifier(LearnBlocks.MOD_ID, name),
                block);
    }


}
