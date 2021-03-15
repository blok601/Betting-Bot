package me.dhillon.bot;

import com.google.common.collect.Sets;
import net.dv8tion.jda.api.entities.Member;

import java.util.HashSet;

public class NBAGame {

    private String homeTeam;
    private String awayTeam;
    private double odds;
    private String line;
    private HashSet<Member> homeVotes;
    private HashSet<Member> awayVotes;

    public NBAGame(String homeTeam, String awayTeam, double odds, String line) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.odds = odds;
        this.line = line;
        this.homeVotes = Sets.newHashSet();
        this.awayVotes = Sets.newHashSet();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public HashSet<Member> getHomeVotes() {
        return homeVotes;
    }

    public void setHomeVotes(HashSet<Member> homeVotes) {
        this.homeVotes = homeVotes;
    }

    public HashSet<Member> getAwayVotes() {
        return awayVotes;
    }

    public void setAwayVotes(HashSet<Member> awayVotes) {
        this.awayVotes = awayVotes;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
