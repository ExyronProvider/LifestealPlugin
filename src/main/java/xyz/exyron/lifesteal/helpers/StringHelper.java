package xyz.exyron.lifesteal.helpers;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringHelper {
    private StringHelper() {
    }

    private final Pattern HEX_PATTERN = Pattern.compile("#[A-Fa-f0-9]{6}");

    private String colorize(@NotNull String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(result, ChatColor.of(matcher.group()).toString());
        }

        matcher.appendTail(result);
        message = result.toString();

        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
