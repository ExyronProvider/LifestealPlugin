package xyz.exyron.lifesteal;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import xyz.exyron.lifesteal.helpers.StringHelper;

@Header("# LifeSteal plugin distributed by Exyron.XYZ")

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {

    @Comment("# Placeholder text (%lifesteal_heartsformatted%)")
    public String placeholderFormat = "&c@h&4❤";
    @Comment("# Should player lose heart/s even if he will not be killed by player?")
    public boolean loseHeartsWhatever = false;
    @Comment("# Number of hearts gained by kill")
    public byte heartsGainByKill = 1;
    @Comment("# Number of hearts lost by death")
    public byte heartsLostByDeath = 1;
    @Comment("# Default number of hearts")
    public byte defaultHeartsAmount = 10;
    @Comment("# Minimum number of hearts, when player will exceed this minimum server will execute command")
    public byte minimumHearts = 2;
    @Comment("# Maximum number of hearts, player can't have more than that.")
    public byte maximumHearts = 30;
    @Comment("# Permission to /adminhearts command")
    public String adminPermision = "lifesteal.adminhearts";

    @Comment("# /adminhearts command usage")
    public String adminHeartsUsage = StringHelper.colorize("&a/ahearts &2[ info / reset / add / remove / set ] [ player ] ( value )").toString();
    @Comment("# Message sent when player provied a wrong amount")
    public String wrongAmount = StringHelper.colorize("&cYou provided a wrong amount!").toString();
    @Comment("# Message sent when provided player is offline")
    public String targetOffline = StringHelper.colorize("&cProvided player is currently offline!").toString();
    @Comment("# Message sent when player cannot remove hearts from player")
    public String cannotRemoveHearts = StringHelper.colorize("&aYou cannot remove &2@a &ahearts from this player, because he's hearts amount will be negative!").toString();
    @Comment("# Message sent when player cannot add hearts to player")
    public String cannotAddHearts = StringHelper.colorize("&aYou cannot add &2@a &ahearts to this player, because he's hearts amount will be above the limit!").toString();
    @Comment("# Message sent when player executes /adminhearts info (nick)")
    public String heartsAmount = StringHelper.colorize("&2@p's &ahearts amount is &2@a").toString();
    @Comment("# Message sent when player successfully removes amount of hearts from someone")
    public String successfullyRemovedHearts = StringHelper.colorize("&aSucessfully removed @a hearts from player @p").toString();
    @Comment("# Message send when player successfully adds amount of hearts to someone")
    public String successfullyAddedHearts = StringHelper.colorize("&aSucessfully added &2@a &ahearts to player &2@p").toString();
    @Comment("# Message sent when player successfully reset someone's hearts amount to default value")
    public String successfullyResetHearts = StringHelper.colorize("&aYou sucessfully reset &2@p's &ahearts amount to &2@a").toString();
    @Comment("# Message sent when player successfully set someone's hearts amount to value")
    public String setHeartsMessage = StringHelper.colorize("&aYou successfully set &2@p &ahearts amount to &2@a").toString();
    @Comment("# Message sent on /hearts command")
    public String heartsCommandMessage = StringHelper.colorize("&aYou currently have &2@h &ahearts!").toString();
    @Comment("# Message sent when player got killed by other player (@h - hearts amount, @p - killer name)")
    public String messageWhenPlayerGotKilledByPlayer = StringHelper.colorize("&cYou lost &4@h &aheart, because &4@p&c killed you!").toString();
    @Comment("# Message sent when player kills somone (@p - killed player name, @h - amount of hearts gained)")
    public String messageWhenPlayerKillsSomeone = StringHelper.colorize("&cYou just killed &4@p&c, and got &4@h &chearts from him!").toString();
    @Comment("# Message sent when player got killed by world (@h - hearts amount)")
    public String messageWhenPlayerGotKilledByWorld = StringHelper.colorize("&cYou lost &4@h &cheart, because something killed you!").toString();
    @Comment("# Message sent when player has the minimum amount of hearts")
    public String minimumHeartsMessage = StringHelper.colorize("&cHearts were not taken away from you because you reached the minimum number of hearts!").toString();
    @Comment("# Message sent when player do not have permission to use command")
    public String noPermissionMessage = StringHelper.colorize("&cYou don't have permision to use this command!").toString();
    @Comment("# Message sent when player has the maximum amount of hearts")
    public String maximumHeartsMessage = StringHelper.colorize("&cHearts have not been added because you have reached the maximum number of hearts!").toString();
    @Comment("# Command executed when player reaches the minimum amount of hearts (@p - player)")
    public String lessOrEquallyHeartsThanMinimumCommand = StringHelper.colorize("ban @p You have reached a minimum amount of hearts, cya!").toString();
}
