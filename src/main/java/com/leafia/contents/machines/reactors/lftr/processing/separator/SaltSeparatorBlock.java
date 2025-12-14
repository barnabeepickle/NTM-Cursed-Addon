package com.leafia.contents.machines.reactors.lftr.processing.separator;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.IDynamicModels;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.leafia.contents.AddonBlocks;
import com.leafia.dev.machine.MachineTooltip;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SaltSeparatorBlock extends BlockDummyable {

	public SaltSeparatorBlock(Material materialIn,String s) {
		super(materialIn,s);
		ModBlocks.ALL_BLOCKS.remove(this);
		AddonBlocks.ALL_BLOCKS.add(this);
		IDynamicModels.INSTANCES.remove(this);
	}

	@Override
	public void addInformation(ItemStack stack,@Nullable World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
		MachineTooltip.addWIP(tooltip);
		tooltip.add("Yes, yes, I know this is just a sloppy recolor.");
		tooltip.add("I can't fucking make the texture for a new model ok?");
		super.addInformation(stack,worldIn,tooltip,flagIn);
	}

	@Override
	public int[] getDimensions() {
		return new int[] {5, 0, 1, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}

	@Override
	public @Nullable TileEntity createNewTileEntity(World worldIn,int meta) {
		if (meta >= 12)
			return new SaltSeparatorTE();
		if(meta >= 6)
			return new TileEntityProxyCombo(true,true,true);
		return null;
	}

	@Override
	protected void fillSpace(World world,int x,int y,int z,ForgeDirection dir,int o) {
		super.fillSpace(world, x, y, z, dir, o);
		this.makeExtra(world, x - dir.offsetX + 1, y, z - dir.offsetZ + 1);
		this.makeExtra(world, x - dir.offsetX + 1, y, z - dir.offsetZ - 1);
		this.makeExtra(world, x - dir.offsetX - 1, y, z - dir.offsetZ + 1);
		this.makeExtra(world, x - dir.offsetX - 1, y, z - dir.offsetZ - 1);
	}
}
