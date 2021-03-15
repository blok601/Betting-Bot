package me.dhillon.bot.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Set;


public abstract class Command {

    public abstract void run(Member member, String[] args, MessageChannel channel, Message message);

    public abstract String getName();

    public abstract Role requiredRole();

    public abstract Set<ListenerAdapter> getHooks();
}