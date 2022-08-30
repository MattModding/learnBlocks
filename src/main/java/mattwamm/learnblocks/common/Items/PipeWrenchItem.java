package mattwamm.learnblocks.common.Items;

import mattwamm.learnblocks.common.blocks.PipeConnectableBlock;
import mattwamm.learnblocks.common.blocks.PipeConnectableEntityBlock;
import mattwamm.learnblocks.common.blocks.entity.PipeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class PipeWrenchItem extends Item {

    public PipeWrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = PipeWrenchItem.raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (((HitResult)hitResult).getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }
        if(world.isClient) {
            user.sendMessage(Text.of("paap1"));
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {

            if(world.isClient) {
                user.sendMessage(Text.of("paap2"));
            }

            BlockEntity hitEntity = world.getBlockEntity(hitResult.getBlockPos());
            if(hitEntity instanceof PipeBlockEntity pipeEntity)
            {
                if(world.isClient) {
                    user.sendMessage(Text.of(pipeEntity.getPressure() + ""));
                }
                return TypedActionResult.success(itemStack, world.isClient());
            }
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
