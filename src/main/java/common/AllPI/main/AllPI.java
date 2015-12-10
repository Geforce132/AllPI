package common.AllPI.main;

import java.util.Arrays;

import common.AllPI.main.network.CommonProxy;
import common.AllPI.main.network.ConfigurationHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid=Reference.MODID, name=Reference.MODNAME, version=Reference.MODVERSION)
public class AllPI {
    
    @Instance(Reference.MODID)
    public static AllPI instance = new AllPI();
	public static ConfigurationHandler configHandler = new ConfigurationHandler();
    
    @SidedProxy(clientSide="common.AllPI.main.network.ClientProxy", serverSide="common.AllPI.main.network.CommonProxy")
    public static CommonProxy proxy = new CommonProxy();
    
    public static SimpleNetworkWrapper network;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		configHandler.setupAPIPackets(network);
		    	
        ModMetadata modMeta = event.getModMetadata();
        modMeta.authorList = Arrays.asList(new String[] {
            "Geforce"
        });
        modMeta.autogenerated = false;
        modMeta.credits = "";
        modMeta.description = "AllPI is a open-source modding library designed to provide multiple APIs at once. You're welcome to contribute to it!";
        modMeta.url = "http://github.com/Geforce132/AllPI";
        
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.setupProxy();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

}