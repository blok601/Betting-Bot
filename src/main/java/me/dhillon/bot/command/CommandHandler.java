package me.dhillon.bot.command;

import me.dhillon.bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;

public class CommandHandler extends ListenerAdapter {
    private ArrayList<Command> commands;

    private Bot bot;

    public CommandHandler(Bot bot) {
        this.bot = bot;

        this.commands = new ArrayList<>();
        this.registerCommands();
    }

    public void registerCommands() {
        registerCommand(new PingCommand());
        registerCommand(new AddGamesCommand(bot));
        registerCommand(new StopCommand(bot));
        registerCommand(new PostGamesCommand(bot));
    }

    private void registerCommand(final Command command) {
        this.commands.add(command);
        if(command.getHooks() != null){
            command.getHooks().forEach(listenerAdapter -> bot.getJda().addEventListener(listenerAdapter));
        }
    }


    public void handle(final Command command, final String[] args, final MessageChannel channel, final Member sender, final Message message) {
        final EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("No Permission!");
        builder.setColor(Color.RED);
        for (final Command c : this.commands) {
            if (c.getName().equalsIgnoreCase(command.getName())) {
                if (c.requiredRole() == null || (sender.getRoles().size() != 0 && sender.getRoles().get(0).getPosition() >= c.requiredRole().getPosition())) {
                    c.run(sender, args, channel, message);
                } else {
                    builder.addField("", "You don't have permission to do this command, " + sender.getEffectiveName(), false);
                    channel.sendMessage(builder.build()).queue();
                }
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
    
    // Command Processor
    @Override
    public void onMessageReceived(final MessageReceivedEvent e) {
        final User user = e.getAuthor();
        final String content = e.getMessage().getContentRaw().trim();
        if (content.startsWith(Bot.PREFIX)) {
            final ArrayList<Command> cmds = bot.getCommandHandler().getCommands();
            if (cmds.stream().anyMatch(command -> content.startsWith(Bot.PREFIX + command.getName()))) {
                final Command cmd = cmds.stream().filter(command -> content.startsWith(Bot.PREFIX + command.getName())).findFirst().get();
                final String[] args = content.split(" ");
                if (args == null || args.length == 0) {
                    bot.getCommandHandler().handle(cmd, null, e.getChannel(), e.getMember(), e.getMessage());
                }
                else {
                    final ArrayList<String> arguements = new ArrayList<String>();
                    for (final String s : args) {
                        if (s != null && s.length() > 0) {
                            arguements.add(s);
                        }
                    }
                    arguements.remove(0);
                    final String[] toReturn = arguements.toArray(new String[arguements.size()]);
                    bot.getCommandHandler().handle(cmd, toReturn, e.getChannel(), e.getMember(), e.getMessage());
                }
            }
        }
    }
}
