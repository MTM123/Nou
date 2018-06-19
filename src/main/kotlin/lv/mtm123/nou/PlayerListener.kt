package lv.mtm123.nou

import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.regex.Pattern

class PlayerListener constructor(cfg : FileConfiguration): Listener{

    private val replaceNameOnJoin = cfg.getBoolean("replace-names")
    private val patterns = loadPatterns(cfg)
    private val msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("message"))
    private val replaceMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("replace-message"))

    @EventHandler(priority = EventPriority.HIGHEST)
    fun PlayerJoinEvent.onJoin(){

        if(!replaceNameOnJoin)
            return

        joinMessage = replaceMsg.replace("%player%", player.name)

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun AsyncPlayerChatEvent.onAsyncChat(){

        for(p in patterns){

            val matcher = p.matcher(message)
            if(matcher.find()){
                player.sendMessage(msg.replace("%player%", player.name))
                break
            }

        }

    }

    private fun loadPatterns(cfg : FileConfiguration) : Set<Pattern>{

        val patterns = HashSet<Pattern>()

        cfg.getStringList("regex-patterns").forEach{s -> patterns.add(Pattern.compile(s))}

        return patterns

    }

}