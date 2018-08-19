package cloud.timo.serverinfo.commands;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.objects.PlayerObject;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import cloud.timo.serverinfo.ServerInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sebastian
 * Created in 25.07.2018
 */
public class ServerInfoCommand extends Command {
    private final ServerInfo plugin;

    public ServerInfoCommand(String name, ServerInfo plugin) {
        super(name);
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(!(commandSender instanceof ProxiedPlayer)){
            commandSender.sendMessage(new TextComponent("§cThis command is only for player."));
            return;
        }
        if (!(commandSender.hasPermission("serverinfo.command.serverinfo"))) {
            plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("noPermissions", true));
            return ;
        }
        if (args.length != 0) {
            plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("serverInfoUsage", true));
            return ;
        }
        ServerObject serverObject = TimoCloudAPI.getUniversalAPI().getPlayer(commandSender.getName()).getServer();
        StringBuilder onlinePlayers = new StringBuilder();
        for (PlayerObject onlinePlayer : serverObject.getOnlinePlayers()) {
            onlinePlayers.append(onlinePlayer.getName()).append(" ");
        }

        plugin.getMessageManager().sendMessage(commandSender, plugin.getFileManager().getMessage("serverInfoMessage", true)
                .replace("{socketAddress}", serverObject.getSocketAddress().toString())
                .replace("{base}", serverObject.getBase())
                .replace("{port}", String.valueOf(serverObject.getPort()))
                .replace("{name}", serverObject.getName())
                .replace("{onlinePlayers}", onlinePlayers.toString().trim())
                .replace("{group}", serverObject.getGroup().getName())
                .replace("{extra}", serverObject.getExtra())
                .replace("{ipAddress}", serverObject.getIpAddress().toString())
                .replace("{map}", serverObject.getMap())
                .replace("{state}", serverObject.getState())
                .replace("{onlinePlayerCount}", String.valueOf(serverObject.getOnlinePlayerCount()))
                .replace("{maxPlayerCount}", String.valueOf(serverObject.getMaxPlayerCount()))
                .replace("{motd}", serverObject.getMotd())
        );
        return ;
    }
}
