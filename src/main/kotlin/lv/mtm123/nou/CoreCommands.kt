package lv.mtm123.nou

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Dependency
import co.aikar.commands.annotation.Subcommand
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

@CommandAlias("nou|no u")
class CoreCommands : BaseCommand(){

    @Dependency
    lateinit var plugin : Nou

    @Subcommand("reload|r")
    fun onReload(sender : CommandSender){
        plugin.loadPlugin(true)
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6[&8Nou&6] &aPlugin reloaded!"))
    }

}