package cloud.timo.serverinfo.managers;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;


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
