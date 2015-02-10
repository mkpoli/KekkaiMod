package cn.kekkai.core.client.gui;

import cn.kekkai.core.block.tile.TileKekkai;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

public class ContainerKekkai extends Container {

	TileEntity te;
	
	public ContainerKekkai(TileEntity te) {
		this.te = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return ((TileKekkai) te).isUsableByPlayer(var1);
	}

	
}
	