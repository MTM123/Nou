package lv.mtm123.nou

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

@CommandAlias("nou")
class CoreCommands : BaseCommand(){

    @Dependency
    lateinit var plugin : Nou

    @Default
    @CommandAlias("nour")
    @CommandPermission("nou.reload")
    @Subcommand("reload|r")
    fun onReload(sender : CommandSender){
        plugin.loadPlugin(true)
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6[&8Nou&6] &aPlugin reloaded!"))
    }

}