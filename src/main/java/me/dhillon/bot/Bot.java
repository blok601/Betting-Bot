package me.dhillon.bot;

import com.google.common.collect.Sets;
import me.dhillon.bot.command.CommandHandler;
import me.dhillon.bot.utils.ServerUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bot {

    private String token;
    private JDA jda;
    public static String PREFIX = "!";
    private ExecutorService executorService;
    private CommandHandler commandHandler;
    private HashSet<NBAGame> nbaGames;
    public Bot(String token) throws InterruptedException {
        System.out.println("Starting login!");
        this.token = token;
        try {
            this.jda = JDABuilder.createDefault(token).setActivity(Activity.playing("The Odds"))
                    .build();
            System.out.println("Logged in!");
        } catch (LoginException e) {
            e.printStackTrace();
        }
        this.jda.awaitReady();

        //Setup the rest
        this.executorService = Executors.newScheduledThreadPool(5);
        this.commandHandler = new CommandHandler(this);
        this.jda.addEventListener(commandHandler);
        this.nbaGames = Sets.newHashSet();
        ServerUtils.setRef(this);
    }

    public JDA getJda() {
        return jda;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public Guild getGuild() {
        return this.jda.getGuildById(808420476036186132L);
    }

    public HashSet<NBAGame> getNbaGames() {
        return nbaGames;
    }
}
