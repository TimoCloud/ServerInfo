package cloud.timo.serverinfo;

import cloud.timo.serverinfo.commands.ServerInfoCommand;
import cloud.timo.serverinfo.managers.FileManager;
import cloud.timo.serverinfo.managers.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Sebastian
 * Created in 25.07.2018
 */
public class ServerInfo extends JavaPlugin {
    private static ServerInfo instance;
    private FileManager fileManager;
    private MessageManager messageManager;
    private ServerInfoCommand serverInfoCommand;

    @Override
    public void onEnable() {
        this.getLogger().info("ServerInfo has been enabled.");
        this.init();
    }

    private void init() {
        instance = this;
        fileManager = new FileManager();
        messageManager = new MessageManager();
        serverInfoCommand = new ServerInfoCommand("serverinfo", this);
    }

    @Override
    public void onDisable() {
        fileManager.deInit();
        this.getLogger().info("ServerInfo has been enabled.");
    }

    public static ServerInfo getInstance() {
        return instance;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ServerInfoCommand getServerInfoCommand() {
        return serverInfoCommand;
    }
}
