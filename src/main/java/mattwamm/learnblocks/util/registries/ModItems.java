package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.Items.PipeWrenchItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import net.minecraft.util.Identifier;

public class ModItems {

    public static Item PIPE_WRENCH;
    public static void registerItems()
    {
        registerBlockItem("machine", new BlockItem(ModBlocks.MACHINE, new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("machine_inventory", new BlockItem(ModBlocks.MACHINE_INVENTORY, new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("pipe",new BlockItem(ModBlocks.PIPE,new FabricItemSettings().group(ItemGroup.MISC)));
        registerBlockItem("water_tank",new BlockItem(ModBlocks.WATER_TANK,new FabricItemSettings().group(ItemGroup.MISC)));
        PIPE_WRENCH = registerItem(new PipeWrenchItem(new Item.Settings().maxCount(1).group(ItemGroup.MISC)), "pipe_wrench");

    }

    private static void registerBlockItem(String name, BlockItem item)
    {
        Registry.register(Registry.ITEM,
                new Identifier(LearnBlocks.MOD_ID, name),
                item
        );
    }

    private static Item registerItem(Item item, String name) {
        return Registry.register(Registry.ITEM, LearnBlocks.MOD_ID + ":" + name, item);
    }

}
