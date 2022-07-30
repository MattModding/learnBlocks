package mattwamm.learnblocks.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import mattwamm.learnblocks.common.blocks.interfaces.IPipeConnectable;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PipeBlock extends Block implements IPipeConnectable {
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    private static final VoxelShape DEFAULT_SHAPE  = Block.createCuboidShape(6.0, 6.0, 6.0, 10, 10, 10);
    private static final Map<BlockState, VoxelShape> OUTLINE_SHAPES = Maps.newHashMap();
    private static final Map<Direction, VoxelShape> HORIZONTAL_SHAPES = ImmutableMap.of(
        Direction.NORTH, Block.createCuboidShape(6.0, 6.0, 0.0, 10.0, 10.0, 6.0),
        Direction.EAST, Block.createCuboidShape(10.0, 6.0, 6.0, 16.0, 10.0, 10.0),
        Direction.SOUTH, Block.createCuboidShape(6.0, 6.0, 10.0, 10.0, 10.0, 16.0),
        Direction.WEST, Block.createCuboidShape(0.0, 6.0, 6.0, 6.0, 10.0, 10.0),
        Direction.UP, Block.createCuboidShape(6.0, 10.0, 6.0, 10.0, 16.0, 10.0),
        Direction.DOWN, Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 6.0, 10.0)
    );
    public static final Map<Direction, BooleanProperty> DIRECTION_TO_PIPE_CONNECTION = ImmutableMap.of(
            Direction.NORTH, NORTH,
            Direction.EAST, EAST,
            Direction.SOUTH, SOUTH,
            Direction.WEST, WEST,
            Direction.UP, UP,
            Direction.DOWN, DOWN
            );

    public PipeBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(UP,false)
                .with(DOWN, false)
                .with(NORTH, false)
                .with(EAST,false)
                .with(SOUTH, false)
                .with(WEST, false)
        );
        for (BlockState blockState : this.getStateManager().getStates()) {
            OUTLINE_SHAPES.put(blockState, this.getShapeForState(blockState));
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP);
        builder.add(DOWN);
        builder.add(NORTH);
        builder.add(EAST);
        builder.add(SOUTH);
        builder.add(WEST);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // I KNOW ITS TERRIBLE BUT I JUST DON'T CARE ANYMORE
        BooleanProperty property = DIRECTION_TO_PIPE_CONNECTION.get(hit.getSide());
        boolean sideValue = state.get(property);
        ItemStack handStack = player.getStackInHand(hand);
        BlockItem handBlock = null;
        boolean connects = false;
        ItemPlacementContext context = null;
        if(handStack.getItem() instanceof BlockItem blockItem)
        {
            context = new ItemPlacementContext(player, hand, handStack, hit);
            handBlock = blockItem;
            if(blockItem.getBlock() instanceof IPipeConnectable pipeConnectable) {
                connects = pipeConnectable.getSideConnection(hit.getSide().getOpposite(),state);
            }
        }
        if(connects && context.canPlace())
        {
            world.setBlockState(pos, state.with(property, true));
        }else if(handBlock != null) {
            if(connects)
            {
                world.setBlockState(pos, state.with(property, !sideValue));
            }
            BlockState offset = world.getBlockState(pos.offset(hit.getSide()));

            if(offset.getBlock() instanceof PipeBlock)
                world.setBlockState(pos.offset(hit.getSide()) ,offset.with(DIRECTION_TO_PIPE_CONNECTION.get(hit.getSide().getOpposite()),!sideValue));
        }else{
            world.setBlockState(pos, state.with(property, !sideValue));
        }
        if (handBlock != null) {
            handBlock.place(context);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

        neighborUpdate(state,world,pos,this,pos,true);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPES.get(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getPlacementState(ctx.getWorld(), this.getDefaultState(), ctx.getBlockPos(), ctx.getSide());
    }

    private BlockState getPlacementState(BlockView world, BlockState state, BlockPos pos, Direction hitSide) {
        state = this.getDefaultState();
        for (Direction dir: Direction.values()) {
            BlockState connectBlock = world.getBlockState(pos.offset(dir));
            if(connectBlock.getBlock() instanceof IPipeConnectable connectableBlock &&  connectableBlock.getSideConnection(dir,connectBlock))
            {
                if(connectBlock.getBlock() instanceof PipeBlock && !(connectBlock.get(DIRECTION_TO_PIPE_CONNECTION.get(dir.getOpposite()))))
                    continue;
                state = state.with(DIRECTION_TO_PIPE_CONNECTION.get(dir), true);
            }
        }
        return state;
    }


    private VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelShape = DEFAULT_SHAPE;

        for (Direction direction : Direction.values()) {
            Boolean pipeConnection = state.get(DIRECTION_TO_PIPE_CONNECTION.get(direction));
            if (pipeConnection) {
                voxelShape = VoxelShapes.union(voxelShape, HORIZONTAL_SHAPES.get(direction));
            }
        }
        return voxelShape;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean getSideConnection(Direction side, BlockState state) {
        return true;
    }

    @Override
    public boolean isConnected(BlockState state) {
        return false;
    }
}
