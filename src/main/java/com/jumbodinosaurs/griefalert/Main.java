package com.jumbodinosaurs.griefalert;

import com.jumbodinosaurs.devlib.log.LogManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event)
    {
        LogManager.consoleLogger.info(event.getPlayer().getDisplayName());
    }

    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event)
    {

        LogManager.consoleLogger.info(event.getPlayer().getDisplayName());
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
        LogManager.consoleLogger.info("Stopping Grief Alert");
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
        getServer().getPluginManager().registerEvents(this, this);
        LogManager.consoleLogger.info("Starting Grief Alert");

    }
}
