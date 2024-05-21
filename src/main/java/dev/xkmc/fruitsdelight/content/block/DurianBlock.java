package dev.xkmc.fruitsdelight.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DurianBlock extends FallingBlock {

	public static final VoxelShape SHAPE = Shapes.or(
			box(3, 0, 3, 13, 13, 13),
			box(7, 13, 7, 9, 16, 9)
	);

	public DurianBlock(Properties pProperties) {
		super(pProperties);
	}

	protected void falling(FallingBlockEntity be) {
		be.setHurtsEntities(2, 40);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

}
