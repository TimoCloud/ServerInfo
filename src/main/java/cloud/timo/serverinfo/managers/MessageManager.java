package cloud.timo.serverinfo.managers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author Sebastian
 * Created in 25.07.2018
 */
public class MessageManager {

    public void sendMessage(Object o, String message){
        CommandSender commandSender = (CommandSender) o;
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
