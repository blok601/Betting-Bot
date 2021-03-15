package me.dhillon.bot.utils;

import me.dhillon.bot.NBAGame;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    public static NBAGame getNBAGameFromArray(JSONObject jsonObject) {
        String fullName = jsonObject.getString("name");
        String[] split = fullName.split("\\sat\\s");
        String home = split[0];
        String away = split[1];
        JSONArray competitions = jsonObject.getJSONArray("competitions");
        JSONObject competitionsAsObject = competitions.getJSONObject(0);
//        JSONArray oddsArray = competitionsAsObject.getJSONArray("odds");
//        JSONObject oddsAsObject = oddsArray.getJSONObject(0);
//        String overUnder = oddsAsObject.getString("overUnder");
//        System.out.println(competitionsAsObject);
//        System.out.println("Keys:");
        //competitionsAsObject.keys().forEachRemaining(s -> System.out.println(s));
        NBAGame nbaGame;
        if (competitionsAsObject.isNull("odds")) {
            nbaGame = new NBAGame(home, away, 0.0, "");
        } else {
            JSONArray oddsArray = (JSONArray) competitionsAsObject.get("odds");
            JSONObject oddsObject = oddsArray.getJSONObject(0);
            double overUnder = oddsObject.getDouble("overUnder");
            String line = oddsObject.getString("details");
            nbaGame = new NBAGame(home, away, overUnder, line);
        }
        return nbaGame;
    }

}
