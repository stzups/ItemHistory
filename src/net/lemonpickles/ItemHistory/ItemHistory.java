package net.lemonpickles.ItemHistory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ItemHistory extends JavaPlugin {
    Logger logger;
    TrackedList trackedItemTree;
    private TreeList treeList;
    public void onEnable(){
        long start = System.nanoTime();
        logger = Bukkit.getLogger();
        if(!getDataFolder().exists())if(!getDataFolder().mkdir())logger.warning("Could not create plugin folder directory");
        ConfigurationSerialization.registerClass(TrackedItem.class);
        trackedItemTree = new TrackedList(this);
        treeList = new TreeList(this);
        treeList.load();
        new ItemEvent(this);
        new ItemCmd(this);
        logger.info("Enabled ItemHistory in "+((System.nanoTime()-start)/1000000)+"ms");
    }
    public void onDisable(){
        long start = System.nanoTime();
        treeList.save();
        logger.info("Disabled ItemHistory in "+((System.nanoTime()-start)/1000000)+"ms");
    }
}
