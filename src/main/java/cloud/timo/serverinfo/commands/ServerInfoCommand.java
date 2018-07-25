package cloud.timo.serverinfo.commands;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import cloud.timo.serverinfo.ServerInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * @author Sebastian
 * Created in 25.07.2018
 * Copyright (c) 2015 - 2018 by CraftMal.de to present. All rights reserved.
 */
public class ServerInfoCommand implements CommandExecutor {
    private final ServerInfo plugin;

    public ServerInfoCommand(String name, ServerInfo plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand(name).setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender.hasPermission("serverinfo.command.serverinfo"))) {
            plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("noPermissions", true));
            return false;
        }
        if (args.length != 0) {
            plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("serverInfoUsage", true));
            return false;
        }
        ServerObject serverObject = TimoCloudAPI.getBukkitAPI().getThisServer();
        plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("serverInfoMessage", true)
                .replace("{socketAddress}", serverObject.getSocketAddress().toString())
                .replace("{base}", serverObject.getBase())
                .replace("{port}", String.valueOf(serverObject.getPort()))
                .replace("{name}", serverObject.getName())
                .replace("{onlinePlayers}", Arrays.toString(serverObject.getOnlinePlayers().toArray()))
                .replace("{group}", serverObject.getGroup().getName())
                .replace("{extra}", serverObject.getExtra())
                .replace("{ipAddress}", serverObject.getIpAddress().toString())
                .replace("{map}", serverObject.getMap())
                .replace("{state}", serverObject.getState())
                .replace("{onlinePlayerCount}", String.valueOf(serverObject.getOnlinePlayerCount()))
                .replace("{maxPlayerCount}", String.valueOf(serverObject.getMaxPlayerCount()))
                .replace("{motd}", serverObject.getMotd())
        );
        return false;
    }
}
