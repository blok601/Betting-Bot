package me.dhillon.bot.command;

import me.dhillon.bot.Bot;
import me.dhillon.bot.NBAGame;
import me.dhillon.bot.Settings;
import me.dhillon.bot.utils.ServerUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Set;

public class PostGamesCommand extends Command{

    private Bot bot;

    public PostGamesCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run(Member member, String[] args, MessageChannel channel, Message message) {
        if(Settings.BASKETBALL){
            for (NBAGame game : bot.getNbaGames()){
                sendBasketBallGameEmbed(game, channel);
            }
        }
    }

    @Override
    public String getName() {
        return "postgames";
    }

    @Override
    public Role requiredRole() {
        return ServerUtils.getRole("VIP");
    }

    @Override
    public Set<ListenerAdapter> getHooks() {
        return null;
    }

    private void sendBasketBallGameEmbed(NBAGame game, MessageChannel channel){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(game.getHomeTeam()  + " vs " + game.getAwayTeam());
        builder.addField("Over/Under",  "" + game.getOdds(), true);
        builder.addField("Odds", game.getLine(), true);
        channel.sendMessage(builder.build()).queue();
    }
}
