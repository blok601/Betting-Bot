package me.dhillon.bot.utils;

import net.dv8tion.jda.api.entities.MessageReaction;

public class EmoteUtils {

    public static boolean isBasketballReaction(MessageReaction.ReactionEmote emote){
        return emote.getAsCodepoints().equalsIgnoreCase("U+1F3C0");
    }

    public static boolean isHockeyReaction(MessageReaction.ReactionEmote emote){
        return emote.getAsCodepoints().equalsIgnoreCase("U+1F3D1");
    }

    public static boolean isDoneReaction(MessageReaction.ReactionEmote emote){
        return emote.getAsCodepoints().equalsIgnoreCase("U+2705");
    }

}
