package me.dhillon.bot.command;

import me.dhillon.bot.Bot;
import me.dhillon.bot.utils.ServerUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Collections;
import java.util.Set;

public class StopCommand extends Command{

    private Bot bot;
    public StopCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run(Member member, String[] args, MessageChannel channel, Message message) {
        bot.getJda().shutdown();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public Role requiredRole() {
        return ServerUtils.getRole("VIP");
    }

    @Override
    public Set<ListenerAdapter> getHooks() {
        return Collections.emptySet();
    }
}
