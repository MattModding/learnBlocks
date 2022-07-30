package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import net.minecraft.util.Identifier;

public class ModItems {


    public static void registerItems()
    {
        registerBlockItem("machine", new BlockItem(ModBlocks.MACHINE, new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("machine_inventory", new BlockItem(ModBlocks.MACHINE_INVENTORY, new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("pipe",new BlockItem(ModBlocks.PIPE,new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("water_tank",new BlockItem(ModBlocks.WATER_TANK,new FabricItemSettings().group(ItemGroup.MISC)));
    }

    private static void registerBlockItem(String name, BlockItem item)
    {
        Registry.register(Registry.ITEM,
                new Identifier(LearnBlocks.MOD_ID, name),
                item
        );
    }

}
