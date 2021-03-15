package me.dhillon.bot.command;

import com.google.common.collect.Sets;
import me.dhillon.bot.Bot;
import me.dhillon.bot.command.hook.AddGamesReactionHook;
import me.dhillon.bot.utils.ServerUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class AddGamesCommand extends Command {

    // Start with NBA and NHL
    private Bot bot;

    public AddGamesCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run(Member member, String[] args, MessageChannel channel, Message message) {
        createFirstQuestion(channel);
    }

    @Override
    public String getName() {
        return "addgames";
    }

    @Override
    public Role requiredRole() {
        return ServerUtils.getRole("VIP");
    }

    private void createFirstQuestion(MessageChannel messageChannel){
        EmbedBuilder embedBuilder = new EmbedBuilder()
        .setTitle("Which Sports?")
                .addField("", "React to this message with the sports you want to include today!", true).setColor(Color.BLUE)
                .addField("", "React with the checkmark once you have chosen the sports!", true);
        messageChannel.sendMessage(embedBuilder.build()).queue(message -> {
           message.addReaction("U+1F3C0").queue();
           message.addReaction("U+1F3D1").queue();
           message.addReaction("U+2705").queue();
        });
    }

    @Override
    public Set<ListenerAdapter> getHooks() {
        return Sets.newHashSet(new AddGamesReactionHook(bot));
    }
}
