package xyz.exyron.lifesteal.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.exyron.lifesteal.PluginConfiguration;

public class HeartsCommand implements CommandExecutor {
    private final PluginConfiguration configuration;

    public HeartsCommand(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage(configuration.getHeartsCommandMessage().replace("{h}", String.valueOf((int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2)));
            return true;
        }

        sender.sendMessage("You cannot use this command from server console.");
        return false;
    }
}