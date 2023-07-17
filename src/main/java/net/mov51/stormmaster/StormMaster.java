package net.mov51.stormmaster;

import net.mov51.stormmaster.events.ProjectileHitEventListener;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class StormMaster extends JavaPlugin {
    public static Plugin plugin;
    public static int thunderDuration = 0;
    public static Material depletionMaterial;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveConfig();
        thunderDuration = this.getConfig().getInt("thunder-duration");
        depletionMaterial = Material.getMaterial(this.getConfig().getString("depletion-block") != null ? this.getConfig().getString("depletion-block") : "BEDROCK");
        // Plugin startup logic
        this.getLogger().info("StormMaster is now enabled!");
        //register event
        getServer().getPluginManager().registerEvents(new ProjectileHitEventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getConfig().set("weather-duration", thunderDuration);
    }
}
