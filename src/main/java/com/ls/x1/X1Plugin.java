package com.ls.x1;

import com.ls.x1.utils.EC_Config;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class X1Plugin extends JavaPlugin {

    private static X1Plugin plugin;

    public static X1Plugin getPlugin() {
        return plugin;
    }

    private EC_Config cfg, messagesCfg;

    @Override
    public void onEnable() {
        plugin = this;

        cfg = new EC_Config(this, null, "config.yml", false);
        messagesCfg = new EC_Config(this, null, "messages.yml", false);
    }

    @Override
    public void onDisable() {
    }
}
