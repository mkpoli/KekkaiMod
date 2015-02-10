package cn.kekkai.core.register;

import cn.kekkai.core.KQKMain;
import cn.kekkai.core.block.BlockKekkaiCenter;
import cn.kekkai.core.block.tile.TileKekkai;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.config.Configuration;

public class KQKRegister {
	public static final int GUI_ID_KEKKAI = 0;
	public static BlockKekkaiCenter blockKekkaiCenter;
	public static void init(Configuration conf) {
		blockKekkaiCenter = new BlockKekkaiCenter();
		GameRegistry.registerBlock(blockKekkaiCenter, "blockKekkaiCenter");
		GameRegistry.registerTileEntity(TileKekkai.class, "tileKekkaiCenter");
		NetworkRegistry.INSTANCE.registerGuiHandler(KQKMain.INSTANCE, new KQKGuiHandler());
	}
}
