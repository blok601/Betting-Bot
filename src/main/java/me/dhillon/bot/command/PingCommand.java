package me.dhillon.bot.command;

import me.dhillon.bot.utils.ServerUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Collections;
import java.util.Set;

public class PingCommand extends Command{

    @Override
    public void run(Member member, String[] args, MessageChannel channel, Message message) {
        channel.sendMessage("Pong!").queue();
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public Role requiredRole() {
        return ServerUtils.getRole("Prime Members");
    }

    @Override
    public Set<ListenerAdapter> getHooks() {
        return Collections.emptySet();
    }
}
