package common.AllPI.api.utils;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PlayerUtils{
    
    /**
     * Gets the EntityPlayer instance of a player (if they're online) using their name.
     * 
     * @param playerName The name of the player to get
     */
    public static EntityPlayer getPlayerFromName(String playerName) {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            List<?> players = Minecraft.getMinecraft().theWorld.playerEntities;
            Iterator<?> iterator = players.iterator();
            
            while(iterator.hasNext()) {
                EntityPlayer tempPlayer = (EntityPlayer) iterator.next();
                
                if(tempPlayer.getName().matches(playerName)) {
                    return tempPlayer;
                }
            }
            
            return null;
        }
        else {
            List<?> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
            Iterator<?> iterator = players.iterator();
            
            while(iterator.hasNext()) {
                EntityPlayer tempPlayer = (EntityPlayer) iterator.next();
                
                if(tempPlayer.getName().matches(playerName)) {
                    return tempPlayer;
                }
            }
            
            return null;
        }
    }
    
    /**
     * Gets the EntityPlayer instance of a player (if they're online) using their UUID.
     * 
     * @param uuid The uuid of the player to get
     */
    public static EntityPlayer getPlayerByUUID(String uuid) {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            List<?> players = Minecraft.getMinecraft().theWorld.playerEntities;
            Iterator<?> iterator = players.iterator();
            
            while(iterator.hasNext()) {
                EntityPlayer tempPlayer = (EntityPlayer) iterator.next();
                
                if(tempPlayer.getGameProfile().getId().toString().matches(uuid)) {
                    return tempPlayer;
                }
            }
            
            return null;
        }
        else {
            List<?> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
            Iterator<?> iterator = players.iterator();
            
            while(iterator.hasNext()) {
                EntityPlayer tempPlayer = (EntityPlayer) iterator.next();
                
                if(tempPlayer.getGameProfile().getId().toString().matches(uuid)) {
                    return tempPlayer;
                }
            }
            
            return null;
        }
    }
    
    /**
     * Returns true if a player with the given name is in the world.
     * 
     * @param playerName The name of the player to get
     */
    public static boolean isPlayerOnline(String playerName) {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            for(int i = 0; i < Minecraft.getMinecraft().theWorld.playerEntities.size(); i++) {
                EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.playerEntities.get(i);
                
                if(player != null && player.getName().matches(playerName)) {
                    return true;
                }
            }
            
            return false;
        }
        else {
            return (MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(playerName) != null);      
        }
    }
    
    /**
     * Sends the given player a chat message.
     * 
     * @param player The player to send the message to
     * @param text The message to send
     * @param color The color of the message
     */
    public static void sendMessageToPlayer(EntityPlayer player, String text, EnumChatFormatting color) {
        player.addChatComponentMessage(new ChatComponentText(color + text));
    }
    
    /**
     * Sends the given {@link ICommandSender} a chat message.
     * 
     * @param sender The ICommandSender to send the message to
     * @param text The message to send
     * @param color The color of the message
     */
    public static void sendMessageToPlayer(ICommandSender sender, String text, EnumChatFormatting color) {
        sender.addChatMessage(new ChatComponentText(color + text));
    }
    
    /**
     * Sends the given player a chat message, followed by a link prefixed with a colon.
     * 
     * @param player The player to send the message to
     * @param text The message to send
     * @param link The link to suffix at the end of the message
     * @param color The color of the message
     */
    public static void sendMessageEndingWithLink(EntityPlayer player, String text, String link, EnumChatFormatting color) {
    	player.addChatMessage(new ChatComponentText(color + text + EnumChatFormatting.RESET + ": ").appendSibling(ForgeHooks.newChatWithLinks(link)));
    }
    
    /**
     * Sends the given {@link ICommandSender} a chat message, followed by a link prefixed with a colon.
     * 
     * @param sender The ICommandSender to send the message to
     * @param text The message to send
     * @param link The link to suffix at the end of the message
     * @param color The color of the message
     */
    public static void sendMessageEndingWithLink(ICommandSender sender, String text, String link, EnumChatFormatting color) {
        sender.addChatMessage(new ChatComponentText(color + text + EnumChatFormatting.RESET + ": ").appendSibling(ForgeHooks.newChatWithLinks(link)));
    }

    /**
     * Returns true if the player is holding the given item in their hand.
     * 
     * @param player The player instance
     * @param item The item to check for
     */
    public static boolean isHoldingItem(EntityPlayer player, Item item) {
        if(item == null && player.getCurrentEquippedItem() == null) {
            return true;
        }
        
        return (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == item);
    }
    
}
