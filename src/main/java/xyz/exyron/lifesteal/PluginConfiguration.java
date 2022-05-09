package xyz.exyron.lifesteal;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import xyz.exyron.lifesteal.helpers.StringHelper;

@Header("# LifeSteal plugin distributed by Exyron.XYZ")

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {

    @Comment("# Placeholder text (%lifesteal_heartsformatted%)")
    private String placeholderFormat = StringHelper.colorize("&c{h}&4❤");
    @Comment("# Should player lose heart/s even if he will not be killed by player?")
    private boolean loseHeartsWhatever = false;
    @Comment("# Number of hearts gained by kill")
    private byte heartsGainByKill = 1;
    @Comment("# Number of hearts lost by death")
    private byte heartsLostByDeath = 1;
    @Comment("# Default number of hearts")
    private byte defaultHeartsAmount = 10;
    @Comment("# Minimum number of hearts, when player will exceed this minimum server will execute command")
    private byte minimumHearts = 2;
    @Comment("# Maximum number of hearts, player can't have more than that.")
    private byte maximumHearts = 30;
    @Comment("# Permission to /adminhearts command")
    private String adminPermision = "lifesteal.adminhearts";

    @Comment("# /adminhearts command usage")
    private String adminHeartsUsage = StringHelper.colorize("&a/ahearts &2[ info/reset/add/remove/set ] [ player ] ( value )");
    @Comment("# Message sent when player provied a wrong amount")
    private String wrongAmount = StringHelper.colorize("&cYou provided a wrong amount!");
    @Comment("# Message sent when provided player is offline")
    private String targetOffline = StringHelper.colorize("&cProvided player is currently offline!");
    @Comment("# Message sent when player cannot remove hearts from player ({a} - amount that cannot bed removed)")
    private String cannotRemoveHearts = StringHelper.colorize("&aYou cannot remove &2{a} &ahearts from this player, because he's hearts amount will be negative!");
    @Comment("# Message sent when player cannot add hearts to player ({a} - amount that cannot be added)")
    private String cannotAddHearts = StringHelper.colorize("&aYou cannot add &2{a} &ahearts to this player, because he's hearts amount will be above the limit!");
    @Comment("# Message sent when player executes /adminhearts info (nick) ({p} - player's name, {a} - current amount of hearts)")
    private String heartsAmount = StringHelper.colorize("&2{p}'s &ahearts amount is &2{a}");
    @Comment("# Message sent when player successfully removes amount of hearts from someone ({a} - amount of removed hearts, {p} - player's name)")
    private String successfullyRemovedHearts = StringHelper.colorize("&aSucessfully removed {a} hearts from player {p}");
    @Comment("# Message send when player successfully adds amount of hearts to someone ({a} - amount of added hearts, {p} - player's name)")
    private String successfullyAddedHearts = StringHelper.colorize("&aSucessfully added &2{a} &ahearts to player &2{p}");
    @Comment("# Message sent when player successfully reset someone's hearts amount to default value ({p} - player's name, {a} - default amount of hearts)")
    private String successfullyResetHearts = StringHelper.colorize("&aYou sucessfully reset &2{p}'s &ahearts amount to &2{a}");
    @Comment("# Message sent when player successfully set someone's hearts amount to value ({p} - player's name, {a} - amount of hearts set)")
    private String setHeartsMessage = StringHelper.colorize("&aYou successfully set &2{p} &ahearts amount to &2{a}");
    @Comment("# Message sent on /hearts command ({h} - player's hearts amount)")
    private String heartsCommandMessage = StringHelper.colorize("&aYou currently have &2{h} &ahearts!");
    @Comment("# Message sent when player got killed by other player ({h} - hearts amount, {p} - killer name)")
    private String messageWhenPlayerGotKilledByPlayer = StringHelper.colorize("&cYou lost &4{h} &cheart, because &4{p}&c killed you!");
    @Comment("# Message sent when player kills somone ({p} - killed player name, {h} - amount of hearts gained)")
    private String messageWhenPlayerKillsSomeone = StringHelper.colorize("&cYou just killed &4{p}&c, and got &4{h} &chearts from him!");
    @Comment("# Message sent when player got killed by world ({h} - hearts amount)")
    private String messageWhenPlayerGotKilledByWorld = StringHelper.colorize("&cYou lost &4{h} &cheart, because something killed you!");
    @Comment("# Message sent when player has the minimum amount of hearts")
    private String minimumHeartsMessage = StringHelper.colorize("&cHearts were not taken away from you because you reached the minimum number of hearts!");
    @Comment("# Message sent when player do not have permission to use command")
    private String noPermissionMessage = StringHelper.colorize("&cYou don't have permision to use this command!");
    @Comment("# Message sent when player has the maximum amount of hearts")
    private String maximumHeartsMessage = StringHelper.colorize("&cHearts have not been added because you have reached the maximum number of hearts!");
    @Comment("# Command executed when player reaches the minimum amount of hearts ({p} - player)")
    private String lessOrEquallyHeartsThanMinimumCommand = "ban {p} You have reached a minimum amount of hearts, cya!";

    public String getPlaceholderFormat() {
        return placeholderFormat;
    }

    public boolean isLoseHeartsWhatever() {
        return loseHeartsWhatever;
    }

    public byte getHeartsGainByKill() {
        return heartsGainByKill;
    }

    public byte getHeartsLostByDeath() {
        return heartsLostByDeath;
    }

    public byte getDefaultHeartsAmount() {
        return defaultHeartsAmount;
    }

    public byte getMinimumHearts() {
        return minimumHearts;
    }

    public byte getMaximumHearts() {
        return maximumHearts;
    }

    public String getAdminPermision() {
        return adminPermision;
    }

    public String getAdminHeartsUsage() {
        return adminHeartsUsage;
    }

    public String getWrongAmount() {
        return wrongAmount;
    }

    public String getTargetOffline() {
        return targetOffline;
    }

    public String getCannotRemoveHearts() {
        return cannotRemoveHearts;
    }

    public String getCannotAddHearts() {
        return cannotAddHearts;
    }

    public String getHeartsAmount() {
        return heartsAmount;
    }

    public String getSuccessfullyRemovedHearts() {
        return successfullyRemovedHearts;
    }

    public String getSuccessfullyAddedHearts() {
        return successfullyAddedHearts;
    }

    public String getSuccessfullyResetHearts() {
        return successfullyResetHearts;
    }

    public String getSetHeartsMessage() {
        return setHeartsMessage;
    }

    public String getHeartsCommandMessage() {
        return heartsCommandMessage;
    }

    public String getMessageWhenPlayerGotKilledByPlayer() {
        return messageWhenPlayerGotKilledByPlayer;
    }

    public String getMessageWhenPlayerKillsSomeone() {
        return messageWhenPlayerKillsSomeone;
    }

    public String getMessageWhenPlayerGotKilledByWorld() {
        return messageWhenPlayerGotKilledByWorld;
    }

    public String getMinimumHeartsMessage() {
        return minimumHeartsMessage;
    }

    public String getNoPermissionMessage() {
        return noPermissionMessage;
    }

    public String getMaximumHeartsMessage() {
        return maximumHeartsMessage;
    }

    public String getLessOrEquallyHeartsThanMinimumCommand() {
        return lessOrEquallyHeartsThanMinimumCommand;
    }
}
