package net.mov51.stormmaster;

import net.mov51.stormmaster.events.ProjectileHitEventListener;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class StormMaster extends JavaPlugin {
    public static Plugin plugin;
    public static int thunderDuration = 0;
    public static World thunderWorld;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveConfig();
        thunderDuration = this.getConfig().getInt("thunder-duration");
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
