package lv.mtm123.nou

import co.aikar.commands.BukkitCommandManager
import lv.mtm123.spigotutils.ConfigManager
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class Nou : JavaPlugin() {

    lateinit var cfg : FileConfiguration
    lateinit var cmdManager : BukkitCommandManager

    override fun onEnable() {
        ConfigManager.initialize(this)

        loadPlugin(false)
    }

    override fun onDisable() {
        logger.log(Level.INFO, "KTHXBYE")
    }

    fun loadPlugin(reload : Boolean){

        if(reload){
            HandlerList.unregisterAll(this)
        }else{
            cmdManager = BukkitCommandManager(this)
            cmdManager.registerCommand(CoreCommands())
        }

        cfg = ConfigManager.load("config.yml")

        server.pluginManager.registerEvents(PlayerListener(cfg), this)

    }

}