package xyz.exyron.lifesteal.commands.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdminHeartsTabComplete implements TabCompleter {
    private final List<String> subCommands = new ArrayList<>() {
        {
            add("info");
            add("reset");
            add("add");
            add("set");
            add("remove");
        }
    };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1)
            return subCommands;
        return null;
    }
}
