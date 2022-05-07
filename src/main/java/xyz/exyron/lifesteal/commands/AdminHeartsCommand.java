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
        if (!sender.hasPermission(configuration.adminPermision) && !sender.isOp()) {
            sender.sendMessage(Objects.requireNonNull(configuration.noPermissionMessage));
            return false;
        }

        if (args.length < 2 || args.length > 3) {
            sender.sendMessage(configuration.adminHeartsUsage);
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[1]);

        if (target == null) {
            sender.sendMessage(configuration.targetOffline);
            return false;
        }

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "info" -> {
                    sender.sendMessage(configuration.heartsAmount.replace("@p", target.getName()).replace("@a", String.valueOf((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2)));
                    return true;
                }

                case "reset" -> {
                    byte defaultHeartsAmount = configuration.defaultHeartsAmount;

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(defaultHeartsAmount * 2);
                    sender.sendMessage(configuration.successfullyResetHearts.replace("@p", target.getName()).replace("@a", String.valueOf(defaultHeartsAmount)));
                    return true;
                }

                default -> {
                    sender.sendMessage(configuration.adminHeartsUsage);
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
                        sender.sendMessage(configuration.wrongAmount);
                        return false;
                    }

                    byte heartsLimit = configuration.maximumHearts;
                    if (((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) + newHealthAmount > heartsLimit) {
                        sender.sendMessage(configuration.cannotAddHearts.replace("@a", String.valueOf(newHealthAmount)));
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + (newHealthAmount * 2));
                    sender.sendMessage(configuration.successfullyAddedHearts.replace("@p", target.getName()).replace("@a", String.valueOf(newHealthAmount)));
                    return true;
                }

                case "set" -> {
                    byte newHeartsAmount;

                    try {
                        newHeartsAmount = Byte.parseByte(args[2]);
                        if (newHeartsAmount < 1 || newHeartsAmount > configuration.maximumHearts) throw new Exception();
                    } catch (Exception e) {
                        sender.sendMessage(configuration.wrongAmount);
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(newHeartsAmount * 2);
                    sender.sendMessage(configuration.setHeartsMessage.replace("@p", target.getName()).replace("@a", String.valueOf(newHeartsAmount)));
                    return true;
                }

                case "remove" -> {
                    byte newHeartsAmount;

                    try {
                        newHeartsAmount = Byte.parseByte(args[2]);
                        if (newHeartsAmount < 1) throw new Exception();
                    } catch (Exception e) {
                        sender.sendMessage(configuration.wrongAmount);
                        return false;
                    }

                    if ((((byte) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) - newHeartsAmount) < 1) {
                        sender.sendMessage(configuration.cannotRemoveHearts.replace("@a", String.valueOf(newHeartsAmount)));
                        return false;
                    }

                    Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - (newHeartsAmount * 2));
                    sender.sendMessage(configuration.successfullyRemovedHearts.replace("@p", target.getName()).replace("@a", String.valueOf(newHeartsAmount)));
                    return true;
                }

                default -> {
                    sender.sendMessage(configuration.adminHeartsUsage);
                    return false;
                }
            }

        }
    }
}