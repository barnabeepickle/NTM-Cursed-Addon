package com.leafia.contents.machines.powercores.ams.stabilizer;

import com.hbm.tileentity.TileEntityProxyCombo;
import com.leafia.dev.blocks.blockbase.AddonBlockDummyable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AMSStabilizerBlock extends AddonBlockDummyable {
	public AMSStabilizerBlock(Material materialIn,String s) {
		super(materialIn,s);
	}

	@Override
	public int[] getDimensions() {
		return new int[]{5,0,0,0,2,2};
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
	public @Nullable TileEntity createNewTileEntity(World worldIn,int meta) {
		if (meta >= 12)
			return new AMSStabilizerTE();
		else if (meta >= 6)
			return new TileEntityProxyCombo(true,true,true);
		return null;
	}
}
