package mattwamm.learnblocks.util;

import static mattwamm.learnblocks.util.registries.BlockEntityTypes.registerBlockEntityTypes;
import static mattwamm.learnblocks.util.registries.ModBlocks.registerBlocks;
import static mattwamm.learnblocks.util.registries.ModItems.registerItems;

public class ModRegistries {

    public static void mainRegister()
    {
     registerBlocks();
     registerBlockEntityTypes();
     registerItems();
    }


}



