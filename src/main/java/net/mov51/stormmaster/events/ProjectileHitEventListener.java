package net.mov51.stormmaster.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import static net.mov51.stormmaster.StormMaster.depletionMaterial;

public class ProjectileHitEventListener implements Listener {
    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent e) {
        if(e.getEntity().getType() != org.bukkit.entity.EntityType.TRIDENT){
            return;
        }
        if(!(((Trident) e.getEntity()).getItem().getEnchantments().containsKey(Enchantment.CHANNELING))){
            return;
        }
        if(e.getHitBlock() == null){
            return;
        }
        if(e.getHitBlock().getType() != Material.DIAMOND_BLOCK){
            return;
        }
        for(Entity en: e.getEntity().getNearbyEntities(1.5,1.5,1.5)){
            if(en.getType() != org.bukkit.entity.EntityType.DROPPED_ITEM){
                continue;
            }
            if(((org.bukkit.entity.Item) en).getItemStack().getType() == org.bukkit.Material.NETHER_STAR){
                en.getWorld().strikeLightning(en.getLocation());
                en.getWorld().setStorm(true);
                en.getWorld().setThundering(true);
                en.getWorld().setThunderDuration(en.getWorld().getThunderDuration() + 24000);
                en.remove();
                e.getHitBlock().setType(depletionMaterial);
                break;
            }
        }
    }
}
