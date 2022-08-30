package mattwamm.learnblocks;

import mattwamm.learnblocks.common.savedata.persistentTest;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.core.jmx.Server;

import static mattwamm.learnblocks.util.ModRegistries.mainRegister;

public class LearnBlocks implements ModInitializer {

    public static final String MOD_ID = "learnblocks";

    @Override
    public void onInitialize() {
        mainRegister();
//
//        UseBlockCallback.EVENT.register((player,world,hand,blockHit) ->{
//            if (!world.isClient && world instanceof ServerWorld serverWorld) {
//                persistentTest test = null;
//                if(serverWorld.getPersistentStateManager().get(persistentTest::fromNbt,"testID") != null)
//                {
//
//                    test = serverWorld.getPersistentStateManager().get(persistentTest::fromNbt,"testID");
//                }
//
//                if(test != null)
//                {
//                    player.sendMessage(Text.of(test.getInteg() + ""));
//                }
//
//                ((ServerWorld)world).getPersistentStateManager().set("testID",new persistentTest(5));
//
//            }
//            return ActionResult.PASS;
//        });

    }
}
