package me.dhillon.bot.command.hook;

import com.google.common.collect.Sets;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import me.dhillon.bot.Bot;
import me.dhillon.bot.NBAGame;
import me.dhillon.bot.Settings;
import me.dhillon.bot.utils.EmoteUtils;
import me.dhillon.bot.utils.JsonUtils;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;

public class AddGamesReactionHook extends ListenerAdapter {

    private Bot bot;

    public AddGamesReactionHook(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if(event.getUser().isBot() || event.getUser().isFake()) return;
        MessageReaction.ReactionEmote emote = event.getReactionEmote();
        if(EmoteUtils.isBasketballReaction(emote)){
            Settings.BASKETBALL = true;
            return;
        }

        if(EmoteUtils.isHockeyReaction(emote)){
            Settings.HOCKEY = true;
            return;
        }

        if (EmoteUtils.isDoneReaction(emote)){
            //Pull the shit

            if(Settings.BASKETBALL){
                HttpResponse<JsonNode> response = Unirest.get("http://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard")
                        .header("accept", "application/json").asJson();
                JSONArray array = response.getBody().getArray();
                JSONObject first = array.getJSONObject(0);
                JSONArray eventArray = first.getJSONArray("events");
             //   System.out.println(eventArray);

                for (int i = 0; i < eventArray.length(); i++){
                    bot.getNbaGames().add(JsonUtils.getNBAGameFromArray(eventArray.getJSONObject(i)));
                }

//                System.out.println("===========================");
//                System.out.println("Games:");
//                games.forEach(nbaGame -> {
//                    System.out.println("Home: " + nbaGame.getHomeTeam() + " Away: " + nbaGame.getAwayTeam() + " Odds: " + nbaGame.getOdds() + " Line: " + nbaGame.getLine());
//                });
//                System.out.println("============================");
            }
        }

    }




}
