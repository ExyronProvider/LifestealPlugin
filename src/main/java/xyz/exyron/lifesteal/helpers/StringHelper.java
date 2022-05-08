package xyz.exyron.lifesteal.helpers;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

public final class StringHelper {
    private StringHelper() {
    }

    public static String colorize(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
