package cloud.timo.serverinfo.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Sebastian
 * Created in 25.07.2018
 * Copyright (c) 2015 - 2018 by CraftMal.de to present. All rights reserved.
 */
public class FileManager {
    private static final String path = "plugins/ServerInfo/";
    private File directory = new File(path);
    private File configFile = new File(path + "config.yml");
    private FileConfiguration config;

    public FileManager() {
        init();
    }

    public void init() {
        try {
            directory.mkdirs();
            configFile.createNewFile();
            reload();
            makeDefaults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deInit() {
        save();
    }

    public void makeDefaults() {
        config.options().copyDefaults(true);
        this.config.addDefault("Prefix", "&8[&4ServerInfo&8]&7");
        this.config.addDefault("noPermissions", "&cYou don't have permissions to do that.");
        this.config.addDefault("serverInfoUsage", "&cPlease use /serverinfo");
        this.config.addDefault("serverInfoMessage", "Server Info&8:" +
                "\n &7SocketAddress&8: &a{socketAddress}" +
                "\n &7Base&8: &a{base}" +
                "\n &7Port&8: &a{port}" +
                "\n &7Name&8: &a{name}" +
                "\n &7OnlinePlayers&8: &a{onlinePlayers}" +
                "\n &7Group&8: &a{group}" +
                "\n &7Extra&8: &a{extra}" +
                "\n &7IpAddress&8: &a{ipAddress}" +
                "\n &7Map&8: &a{map}" +
                "\n &7State&8: &a{state}" +
                "\n &7OnlinePlayerCount&8: &a{onlinePlayerCount}" +
                "\n &7MaxPlayerCount&8: &a{maxPlayerCount}" +
                "\n &7Motd&8: &a{motd}");        save();
    }

    private void reload() {
        setConfig(YamlConfiguration.loadConfiguration(configFile));
    }


    private void save() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    private void setConfig(FileConfiguration config) {
        this.config = config;
    }


    public String getMessage(String path, Boolean prefix){
        if(prefix) return config.getString("Prefix") + " " + config.getString(path);
        return config.getString(path);
    }

}


