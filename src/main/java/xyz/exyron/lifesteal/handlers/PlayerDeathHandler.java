package xyz.exyron.lifesteal.handlers;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.exyron.lifesteal.PluginConfiguration;

import java.util.Objects;

public class PlayerDeathHandler implements Listener {
    private final PluginConfiguration configuration;

    public PlayerDeathHandler(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.getKiller() != null) {
            Player killer = player.getKiller();

            if ((killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) >= configuration.maximumHearts) {
                killer.sendMessage(configuration.maximumHeartsMessage);
            } else {
                Objects.requireNonNull(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + (configuration.heartsGainByKill * 2));
                killer.sendMessage(configuration.messageWhenPlayerKillsSomeone.replace("@p", player.getName()).replace("@h", String.valueOf(configuration.heartsGainByKill)));
            }

            if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) <= configuration.minimumHearts || player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.heartsLostByDeath * 2) < 1) {
                player.sendMessage(configuration.minimumHeartsMessage);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(configuration.defaultHeartsAmount * 2);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), configuration.lessOrEquallyHeartsThanMinimumCommand.replace("@p", player.getName()));
            } else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.heartsLostByDeath * 2));
                player.sendMessage(configuration.messageWhenPlayerGotKilledByPlayer.replace("@p", killer.getName()).replace("@h", String.valueOf(configuration.heartsLostByDeath)));
            }

        } else if (configuration.loseHeartsWhatever) {
            if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) <= configuration.minimumHearts || player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.heartsLostByDeath * 2) < 1) {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(configuration.defaultHeartsAmount * 2);
                player.sendMessage(configuration.minimumHeartsMessage);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), configuration.lessOrEquallyHeartsThanMinimumCommand.replace("@p", player.getName()));
            } else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.heartsLostByDeath * 2));
                player.sendMessage(configuration.messageWhenPlayerGotKilledByWorld.replace("@h", String.valueOf(configuration.heartsLostByDeath)));
            }
        }
    }
}