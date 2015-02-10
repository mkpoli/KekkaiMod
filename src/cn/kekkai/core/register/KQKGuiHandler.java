package cn.kekkai.core.register;

import cn.kekkai.core.block.tile.TileKekkai;
import cn.kekkai.core.client.gui.ContainerKekkai;
import cn.kekkai.core.client.gui.GuiKekkai;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class KQKGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == KQKRegister.GUI_ID_KEKKAI) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || !(tileEntity instanceof TileKekkai))
				return null;
			return new ContainerKekkai((TileKekkai) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == KQKRegister.GUI_ID_KEKKAI) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || !(tileEntity instanceof TileKekkai))
				return null;
			return new GuiKekkai((TileKekkai) tileEntity);
		}
		return null;
	}

}
