package xyz.exyron.lifesteal.handlers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import xyz.exyron.lifesteal.LifestealPlugin;
import xyz.exyron.lifesteal.PluginConfiguration;

import java.util.Objects;

public class PlayerJoinHandler implements Listener {
    private static final @NotNull LifestealPlugin instance = LifestealPlugin.getPlugin(LifestealPlugin.class);
    private final PluginConfiguration configuration;


    public PlayerJoinHandler(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(configuration.defaultHeartsAmount * 2);
            return;
        }

        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    }
}