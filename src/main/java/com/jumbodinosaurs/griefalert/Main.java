package com.jumbodinosaurs.griefalert;

import com.jumbodinosaurs.devlib.log.LogManager;
import com.jumbodinosaurs.devlib.util.GeneralUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener
{

    private static final File recordedBlockPlacements = GeneralUtil.checkFor(GeneralUtil.userDir,
            "GriefAlertRecordedPlacements");

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event)
    {
        /*
         * Process for Block Break Event
         * Craft File/File Path and Check for Existence
         * Check Username in File
         *
         *  */


        //Craft File/File Path and Check for Existence
        Location location = event.getBlock().getLocation();
        String recordPath = "/%s/%s/%s/%s.txt";
        recordPath = String.format(recordPath, Objects.requireNonNull(location.getWorld()).getName(),
                location.getBlockX(),
                location.getBlockY(), location.getBlockZ());


        String username = event.getPlayer().getName();

        try
        {
            File recordFile = new File(recordedBlockPlacements.getAbsolutePath() + recordPath);


            if (recordFile.exists())
            {
                String recordPlacer = GeneralUtil.scanFileContents(recordFile);
                //We clean this up because the can in method isn't clean
                recordPlacer = recordPlacer.replaceAll("\n", "");


                //Check Username in File
                if (!username.equals(recordPlacer))
                {
                    alert(event, recordPlacer);
                    LogManager.consoleLogger.info("Breaker:(" + username + ")");
                    LogManager.consoleLogger.info("RecordPlacer:(" + recordPlacer + ")");
                    LogManager.consoleLogger.info(
                            username + " Broke Block at " + event.getBlock().getLocation());
                }
            }
        }
        catch (Exception e)
        {
            //Do nothing
        }


    }

    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event)
    {

        /*
         * Process for Block Place Event
         * Craft File/File Path
         * Write Username to File
         *
         *  */

        //Craft File/File Path
        Location location = event.getBlock().getLocation();

        // /World/X/Y/Z.txt
        String recordPath = "/%s/%s/%s/%s.txt";
        recordPath = String.format(recordPath, Objects.requireNonNull(location.getWorld()).getName(),
                location.getBlockX(),
                location.getBlockY(), location.getBlockZ());


        //Write Username to File

        String username = event.getPlayer().getName();

        try
        {
            File recordFile = GeneralUtil.checkForLocalPath(recordedBlockPlacements, recordPath);
            GeneralUtil.writeContents(recordFile, username, false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        LogManager.consoleLogger.info(username + " Placed Block at " + event.getBlock().getLocation().toString());
//        LogManager.consoleLogger.info("Placer:(" + username + ")");
    }


    public static void alert(BlockBreakEvent event, String recordedPlacer)
    {
        /* Process for Alerting OPS
         * Craft Message
         * Send Message to All Ops
         *
         *  */


        // Craft Message
        String username = event.getPlayer().getName();
        Location location = event.getBlock().getLocation();

        String baseText = username + " has broken " + recordedPlacer + " block at " + "[%s, %s, %s]";
        baseText = String.format(baseText, location.getBlockX(), location.getBlockY(), location.getBlockZ());
        TextComponent message = new TextComponent(baseText);


        String actionText = "/tp %s %s %s";
        actionText = String.format(actionText, location.getBlockX(), location.getBlockY(), location.getBlockZ());
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, actionText));

        String hoverText = "Click To Teleport";
        Text content = new Text(hoverText);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, content));


        //Send Message to All Ops
        for (Player player : getProvidingPlugin(Main.class).getServer().getOnlinePlayers())
        {
            if (player.isOp())
            {
                player.spigot().sendMessage(message);
            }
        }

        LogManager.consoleLogger.info(username + "Broke Someone else's Block at " + location);
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
