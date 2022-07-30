package mattwamm.learnblocks.client;

import net.fabricmc.api.ClientModInitializer;

import static mattwamm.learnblocks.util.registries.ScreenHandlers.registerScreenHandlers;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class LearnBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerScreenHandlers();
    }
}
