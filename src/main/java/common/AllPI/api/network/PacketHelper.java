package common.AllPI.api.network;

import java.util.HashMap;
import java.util.Iterator;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHelper {
	
	private static HashMap<String, Byte> packetByteDiscriminators = new HashMap<String, Byte>();
	
	public static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(SimpleNetworkWrapper network, Class<REQ> requestMessageType, Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Side side) {
		String packetPackageName = requestMessageType.getPackage().getName();
		Iterator<String> iterator = packetByteDiscriminators.keySet().iterator();
		
		byte discriminator = -1;
		while(iterator.hasNext()) {
			String pack = iterator.next();
			
			if(pack.contains(packetPackageName)) {
				packetByteDiscriminators.get(packetPackageName);
			}
		}
		
		if(discriminator == -1) {
			packetByteDiscriminators.put(packetPackageName, (byte) 0);
		}
		
		network.registerMessage(messageHandler, requestMessageType, packetByteDiscriminators.get(packetPackageName), side);
	}

}
