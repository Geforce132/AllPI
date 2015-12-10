package common.AllPI.api.utils;

import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class WorldUtils{
    
    /**
     * Performs a ray trace against all blocks (except liquids) from the starting X, Y, and Z
     * to the end point, and returns true if a block is within that path.
     * 
     * @param world The world you are in
     * @param x1 Starting X coordinate
     * @param y1 Starting Y coordinate
     * @param z1 Starting Z coordinate
     * @param x2 Ending X coordinate
     * @param y2 Ending Y coordinate
     * @param z2 Ending Z coordinate
     */
    public static boolean performRaytrace(World world, int x1, int y1, int z1, int x2, int y2, int z2){
        return performRaytrace(world, (double) x1, (double) y1, (double) z1, (double) x2, (double) y2, (double) z2);
    }

    /**
     * Performs a ray trace against all blocks (except liquids) from the starting X, Y, and Z
     * to the end point, and returns true if a block is within that path.
     * 
     * @param world The world you are in
     * @param x1 Starting X coordinate
     * @param y1 Starting Y coordinate
     * @param z1 Starting Z coordinate
     * @param x2 Ending X coordinate
     * @param y2 Ending Y coordinate
     * @param z2 Ending Z coordinate
     */
    public static boolean performRaytrace(World world, double x1, double y1, double z1, double x2, double y2, double z2) {
        return world.rayTraceBlocks(new Vec3(x1, y1, z1), new Vec3(x2, y2, z2)) != null;
    }
    
}