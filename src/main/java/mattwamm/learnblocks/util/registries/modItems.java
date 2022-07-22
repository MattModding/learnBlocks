package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import net.minecraft.util.Identifier;

public class modItems {


    public static void registerItems()
    {
        registerBlockItem("machine", new BlockItem(modBlocks.MACHINE, new FabricItemSettings().group(ItemGroup.MISC)));
    }

    private static void registerItem(String name, Item item)
    {
        Registry.register(Registry.ITEM,
                new Identifier(LearnBlocks.MOD_ID, name),
                item);
    }

    private static void registerBlockItem(String name, BlockItem item)
    {
        Registry.register(Registry.ITEM,
                new Identifier(LearnBlocks.MOD_ID, name),
                item);
    }

}
