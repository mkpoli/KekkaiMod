package cn.kekkai.core;

import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import cn.annoreg.core.RegistrationManager;
import cn.annoreg.mc.RegMessageHandler;
import cn.kekkai.core.data.MsgRChange;
import cn.kekkai.core.proxy.KQKCommonProxy;
import cn.kekkai.core.register.KQKRegister;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * @author mkpoli
 *
 */
@Mod(modid = "kekkai", name = "KekkaiCraft", version = KQKMain.VERSION)
public class KQKMain {
	
	public static final String VERSION = "0.0.1dev";
	
	@Instance("kekkai")
	public static KQKMain INSTANCE;
	
	@SidedProxy(clientSide = "cn.kekkai.core.proxy.KQKClientProxy", serverSide = "cn.kekkai.core.proxy.KQKCommonProxy")
	public static KQKCommonProxy proxy;
	
	public static Logger log = FMLLog.getLogger();
	
	public static Configuration config;
	
//	public static SimpleNetworkWrapper netHandler = NetworkRegistry.INSTANCE.newSimpleChannel(APGeneralProps.NET_CHANNEL);
	private static int nextNetID = 0;
	
	/**
	 * 网络发包处理实例
	 */
	@RegMessageHandler.WrapperInstance
	public static SimpleNetworkWrapper netHandler = NetworkRegistry.INSTANCE.newSimpleChannel("kqknet");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.info("Starting KekkaiCraft " + VERSION);
		log.info("Copyright (c) mkpoli, 2015-");
		
		KQKRegister.init(config);
		//FMLCommonHandler.instance().bus().register(new APTickEvents());
		//MinecraftForge.EVENT_BUS.register(new APEventListener());
		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
//		RegistrationManager.INSTANCE.registerAll(this, "Init");
		proxy.init();
		netHandler.registerMessage(MsgRChange.Handler.class, MsgRChange.class, getNextChannelID(), Side.SERVER);
	}
	
	public static int getNextChannelID() {
		return nextNetID++;
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
//        RegistrationManager.INSTANCE.registerAll(this, "PostInit");
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
//        RegistrationManager.INSTANCE.registerAll(this, "StartServer");
	}
}
	