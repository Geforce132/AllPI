package common.AllPI.api.utils;

import java.util.Iterator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EntityUtils{
	
	/**
	 * Checks to see if the {@link EntityLivingBase} has the given Potion effect.
	 * 
	 * @param mob EntityLivingBase to check
	 * @param potion The potion effect to check for
	 * @return True if the entity has the potion effect, false otherwise
	 */  
    public static boolean doesMobHavePotionEffect(EntityLivingBase mob, Potion potion) {
        Iterator<?> iterator = mob.getActivePotionEffects().iterator();

        while(iterator.hasNext()) {
            PotionEffect effect = (PotionEffect) iterator.next();
            String eName = effect.getEffectName();
            
            if(eName.matches(potion.getName())) {
                return true;
            }
            else {
                continue;
            }
        }
        
        return false;
    }
    
}