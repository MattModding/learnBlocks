package mattwamm.learnblocks.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MachineBlock extends Block {

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    public MachineBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(
                CHARGED,false
        )
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    //if its not a full block enable this
//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
//        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);
//    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,1,1);
        world.setBlockState(pos,state.with(CHARGED,true));
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (world.getBlockState(pos).get(CHARGED)){
            // Summoning the Lighting Bolt at the block
            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(lightningEntity);
        }

        world.setBlockState(pos, state.with(CHARGED, false));
        super.onSteppedOn(world, pos, state, entity);
    }
}
