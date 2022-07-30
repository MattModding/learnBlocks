package mattwamm.learnblocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.MinecraftClient;

import static mattwamm.learnblocks.util.ModRegistries.mainRegister;

public class LearnBlocks implements ModInitializer {

    public static final String MOD_ID = "learnblocks";

    @Override
    public void onInitialize() {
        mainRegister();
    }
}
