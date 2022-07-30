package mattwamm.learnblocks.util.registries;

import mattwamm.learnblocks.LearnBlocks;
import mattwamm.learnblocks.common.screen.MachineScreen;
import mattwamm.learnblocks.common.screen.MachineScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;


public class ScreenHandlers {

    public static final ScreenHandlerType<MachineScreenHandler> MACHINE_SCREEN_HANDLER;


    static {
        MACHINE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(LearnBlocks.MOD_ID,"machineinventoryblock"), MachineScreenHandler::new);
    }

    public static void registerScreenHandlers()
    {
        ScreenRegistry.register(MACHINE_SCREEN_HANDLER, MachineScreen::new);
    }


}
