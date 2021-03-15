package me.dhillon.bot.utils;

import me.dhillon.bot.Bot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class ServerUtils {

    private static Bot ref;

    public static void setRef(Bot ref) {
        ServerUtils.ref = ref;
    }

    public static boolean userHasRole(String role, Member member){
        Role r = ref.getGuild().getRolesByName(role, true).get(0);
        return member.getRoles().contains(r);
    }

    public static Role getRole(String role){
        return ref.getGuild().getRolesByName(role, true).get(0);
    }

}
