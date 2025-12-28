package com.leafia.contents.gear.advisor;

import com.hbm.blocks.gas.BlockGasAsbestos;
import com.hbm.blocks.gas.BlockGasCoal;
import com.hbm.main.ClientProxy;
import com.hbm.main.MainRegistry;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.I18nUtil;
import com.leafia.dev.items.itembase.AddonItemBase;
import com.leafia.init.LeafiaSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvisorItem extends AddonItemBase {
	final static int len = 10000;
	public static void showMessage(ITextComponent msg,int millisec,int id) {
		id*=10;
		MainRegistry.proxy.displayTooltipLegacy(msg.getFormattedText(),millisec,1121+id);
	}
	public static void showMessage(String msg,int millisec,int id) {
		id*=10;
		for (String s : msg.split("\\$")) {
			MainRegistry.proxy.displayTooltipLegacy(TextFormatting.GOLD+s,millisec,1121+id);
			id++;
		}
	}
	@SideOnly(Side.CLIENT)
	public static void playWarning() {
		Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(LeafiaSoundEvents.advisor_warning,1));
	}
	public AdvisorItem(String s) {
		super(s);
	}
	public static class Warns {
		static int gas = 0;
		public static void tick() {
			gas = decrement(gas);
		}
		static int decrement(int v) { return Math.max(v-1,0); }
	}
	static final String msgRoot = "item.advisor.message.";
	static String msg(String key) {
		return I18nUtil.resolveKey(msgRoot+key);
	}
	static final int gasCooldown = 20*60;
	public void gasAlert(World world,BlockPos pos,EntityPlayer player) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block instanceof BlockGasCoal) {
			if (!ArmorRegistry.hasProtection(player,EntityEquipmentSlot.HEAD,HazardClass.PARTICLE_COARSE)) {
				if (Warns.gas <= 0)
					playWarning();
				showMessage(msg("coal"),len,0);
				Warns.gas = gasCooldown;
			}
		}
		if (block instanceof BlockGasAsbestos) {
			if (!ArmorRegistry.hasProtection(player,EntityEquipmentSlot.HEAD,HazardClass.PARTICLE_FINE)) {
				if (Warns.gas <= 0)
					playWarning();
				showMessage(msg("asbestos"),len,1);
				Warns.gas = gasCooldown;
			}
		}
	}
	@Override
	public void onUpdate(ItemStack stack,World world,Entity entity,int itemSlot,boolean isSelected) {
		if (world.isRemote && MainRegistry.proxy instanceof ClientProxy && entity instanceof EntityPlayer player) {
			//showMessage("Selected: "+isSelected,1000,0);
			BlockPos pos = new BlockPos(entity.posX,entity.posY,entity.posZ);
			{
				// BLOCK ALERT
				gasAlert(world,pos,player);
				gasAlert(world,pos.up(),player);
			}
		}
	}
	@Override
	public void addInformation(ItemStack stack,@Nullable World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.DARK_RED+"W.I.P.");
		super.addInformation(stack,worldIn,tooltip,flagIn);
	}
}
