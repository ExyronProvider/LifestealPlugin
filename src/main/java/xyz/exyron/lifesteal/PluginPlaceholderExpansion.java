package xyz.exyron.lifesteal;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PluginPlaceholderExpansion extends PlaceholderExpansion {
    private final PluginConfiguration configuration;

    public PluginPlaceholderExpansion(LifestealPlugin instance) {
        this.configuration = instance.getConfiguration();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "lifesteal";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Exyron.XYZ";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return null;

        if (params.equalsIgnoreCase("hearts")) {
            return String.valueOf((byte)Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 2);
        }

        if (params.equalsIgnoreCase("heartsformatted")) {
            return configuration.getPlaceholderFormat().replace("{h}", String.valueOf((byte)Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 2));
        }

        return null;
    }
}
