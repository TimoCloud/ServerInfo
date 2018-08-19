package cloud.timo.serverinfo;

import cloud.timo.serverinfo.commands.ServerInfoCommand;
import cloud.timo.serverinfo.managers.FileManager;
import cloud.timo.serverinfo.managers.MessageManager;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Sebastian
 * Created in 25.07.2018
 */
public class ServerInfo extends Plugin {
    private static ServerInfo instance;
    private FileManager fileManager;
    private MessageManager messageManager;
    private ServerInfoCommand serverInfoCommand;

    @Override
    public void onEnable() {
        this.getLogger().info("ServerInfo by " + getDescription().getAuthor() + " (Version: " + getDescription().getVersion() + ") has been enabled.");
        this.init();
    }

    private void init() {
        instance = this;
        fileManager = new FileManager();
        fileManager.load();
        messageManager = new MessageManager();
        serverInfoCommand = new ServerInfoCommand("serverinfo", this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("ServerInfo by " + getDescription().getAuthor() + " (Version: " + getDescription().getVersion() + ") has been disabled.");
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
