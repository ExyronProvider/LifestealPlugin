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

            if ((killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) >= configuration.getMaximumHearts()) {
                killer.sendMessage(configuration.getMaximumHeartsMessage());
            } else {
                Objects.requireNonNull(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + (configuration.getHeartsGainByKill() * 2));
                killer.sendMessage(configuration.getMessageWhenPlayerKillsSomeone().replace("{p}", player.getName()).replace("{h}", String.valueOf(configuration.getHeartsGainByKill())));
            }

            if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) <= configuration.getMinimumHearts() || player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.getHeartsLostByDeath() * 2) < 1) {
                player.sendMessage(configuration.getMinimumHeartsMessage());
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(configuration.getDefaultHeartsAmount() * 2);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), configuration.getLessOrEquallyHeartsThanMinimumCommand().replace("{p}", player.getName()));
            } else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.getHeartsLostByDeath() * 2));
                player.sendMessage(configuration.getMessageWhenPlayerGotKilledByPlayer().replace("{p}", killer.getName()).replace("{h}", String.valueOf(configuration.getHeartsLostByDeath())));
            }

        } else if (configuration.isLoseHeartsWhatever()) {
            if ((player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) <= configuration.getMinimumHearts() || player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.getHeartsLostByDeath() * 2) < 1) {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(configuration.getDefaultHeartsAmount() * 2);
                player.sendMessage(configuration.getMinimumHeartsMessage());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), configuration.getLessOrEquallyHeartsThanMinimumCommand().replace("{p}", player.getName()));
            } else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (configuration.getHeartsLostByDeath() * 2));
                player.sendMessage(configuration.getMessageWhenPlayerGotKilledByWorld().replace("{h}", String.valueOf(configuration.getHeartsLostByDeath())));
            }
        }
    }
}