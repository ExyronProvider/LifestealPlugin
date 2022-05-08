package xyz.exyron.lifesteal;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.exyron.lifesteal.commands.AdminHeartsCommand;
import xyz.exyron.lifesteal.commands.HeartsCommand;
import xyz.exyron.lifesteal.commands.tabcomplete.AdminHeartsTabComplete;
import xyz.exyron.lifesteal.handlers.PlayerDeathHandler;
import xyz.exyron.lifesteal.handlers.PlayerJoinHandler;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;

public final class LifestealPlugin extends JavaPlugin {
    private PluginConfiguration configuration;

    @Override
    public void onEnable() {
        setupConfiguration();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().info("LifestealPlugin is disabling, because PlaceholderAPI is not installed.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        new PluginPlaceholderExpansion(this).register();

        Objects.requireNonNull(getCommand("hearts")).setExecutor(new HeartsCommand(this.configuration));
        Objects.requireNonNull(getCommand("adminhearts")).setExecutor(new AdminHeartsCommand(this.configuration));
        Objects.requireNonNull(getCommand("adminhearts")).setTabCompleter(new AdminHeartsTabComplete());

        getServer().getPluginManager().registerEvents(new PlayerJoinHandler(this.configuration), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathHandler(this.configuration), this);
    }

    private void setupConfiguration() {
        try {
            this.configuration = ConfigManager.create(PluginConfiguration.class, it -> {
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Caught exception while loading plugin's configuration.. ", exception);
            this.getPluginLoader().disablePlugin(this);
        }
    }

    public PluginConfiguration getConfiguration() {
        return this.configuration;
    }
}