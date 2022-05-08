package xyz.exyron.lifesteal.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.exyron.lifesteal.PluginConfiguration;

import java.util.Objects;

public class AdminHeartsCommand implements CommandExecutor {
    private final PluginConfiguration configuration;

    public AdminHeartsCommand(PluginConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(configuration.getAdminPermision()) && !sender.isOp()) {
            sender.sendMessage(Objects.requireNonNull(configuration.getNoPermissionMessage()));
            return false;
        }

        if (args.length < 2 || args.length > 3) {
            sender.sendMessage(configuration.getAdminHeartsUsage());
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[1]);

        if (target == null) {
            sender.sendMessage(configuration.getTargetOffline());
            return false;
        }

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "info" -> {
                    sender.sendMessage(configuration.getHeartsAmount().replace("{p}", target.getName()).replace("{a}", String.valueOf((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2)));
                    return true;
                }

                case "reset" -> {
                    byte defaultHeartsAmount = configuration.getDefaultHeartsAmount();

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(defaultHeartsAmount * 2);
                    sender.sendMessage(configuration.getSuccessfullyResetHearts().replace("{p}", target.getName()).replace("{a}", String.valueOf(defaultHeartsAmount)));
                    return true;
                }

                default -> {
                    sender.sendMessage(configuration.getAdminHeartsUsage());
                    return false;
                }
            }
        } else {
            switch (args[0].toLowerCase()) {
                case "add" -> {
                    byte newHealthAmount;

                    try {
                        newHealthAmount = Byte.parseByte(args[2]);
                        if (newHealthAmount < 1) throw new Exception();
                    } catch (Exception e) {
                        sender.sendMessage(configuration.getWrongAmount());
                        return false;
                    }

                    if (((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) + newHealthAmount > configuration.getMaximumHearts()) {
                        sender.sendMessage(configuration.getCannotAddHearts().replace("{a}", String.valueOf(newHealthAmount)));
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + (newHealthAmount * 2));
                    sender.sendMessage(configuration.getSuccessfullyAddedHearts().replace("{p}", target.getName()).replace("{a}", String.valueOf(newHealthAmount)));
                    return true;
                }

                case "set" -> {
                    byte newHeartsAmount;

                    try {
                        newHeartsAmount = Byte.parseByte(args[2]);
                        if (newHeartsAmount < 1 || newHeartsAmount > configuration.getMaximumHearts())
                            throw new Exception();
                    } catch (Exception e) {
                        sender.sendMessage(configuration.getWrongAmount());
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(newHeartsAmount * 2);
                    sender.sendMessage(configuration.getSetHeartsMessage().replace("{p}", target.getName()).replace("{a}", String.valueOf(newHeartsAmount)));
                    return true;
                }

                case "remove" -> {
                    byte newHeartsAmount;

                    try {
                        newHeartsAmount = Byte.parseByte(args[2]);
                        if (newHeartsAmount < 1) throw new Exception();
                    } catch (Exception e) {
                        sender.sendMessage(configuration.getWrongAmount());
                        return false;
                    }

                    if ((((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) - newHeartsAmount) < 1) {
                        sender.sendMessage(configuration.getCannotRemoveHearts().replace("{a}", String.valueOf(newHeartsAmount)));
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (newHeartsAmount * 2));
                    sender.sendMessage(configuration.getSuccessfullyRemovedHearts().replace("{p}", target.getName()).replace("{a}", String.valueOf(newHeartsAmount)));
                    return true;
                }

                default -> {
                    sender.sendMessage(configuration.getAdminHeartsUsage());
                    return false;
                }
            }

        }
    }
}