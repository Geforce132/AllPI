package common.AllPI.api.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ScreenShotHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientUtils {
    
    /**
     * Closes any GUIs the player currently is looking at.
     */
    @SideOnly(Side.CLIENT)
    public static void closePlayerScreen() {
        Minecraft.getMinecraft().thePlayer.closeScreen();
    }
    
    /**
     * Takes a screenshot of the player's view (the same as pressing F3), and sends the player a chat notification.
     */
    @SideOnly(Side.CLIENT)
    public static void takeScreenshot() {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(Minecraft.getMinecraft().mcDataDir, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, Minecraft.getMinecraft().getFramebuffer()));    
        }
    }
    
    /**
     * @return The current Minecraft in-game time in a 12-hour AM/PM format.
     */
    @SideOnly(Side.CLIENT)
    public static String getFormattedMinecraftTime() {
        Long time = Long.valueOf(Minecraft.getMinecraft().theWorld.provider.getWorldTime());
        
        int hours24 = (int) ((float) time.longValue() / 1000L + 6L) % 24;
        int hours = hours24 % 12;
        int minutes = (int) ((float) time.longValue() / 16.666666F % 60.0F);
        
        return String.format("%02d:%02d %s", new Object[]{Integer.valueOf(hours < 1 ? 12 : hours), Integer.valueOf(minutes), hours24 < 12 ? "AM" : "PM"});
    }
    
    /**
     * @return True if the client is hosting a LAN world.
     */
    @SideOnly(Side.CLIENT)
    public static boolean isInLANWorld(){
        return (Minecraft.getMinecraft().getIntegratedServer() != null && Minecraft.getMinecraft().getIntegratedServer().getPublic());
    }
    
}