package no.stelar7.adventofcode.day9;

import no.stelar7.adventofcode.Utils;

import java.util.*;

public class MarbleGame
{
    public static final String regex = "(?<players>\\d+) players; last marble is worth (?<points>\\d+) points";
    
    private final Map<Integer, Long> scores = new HashMap<>();
    
    private int players;
    private int points;
    
    public MarbleGame(String input)
    {
        Map<String, String> data = Utils.extractRegex(input, regex, MarbleGame.class);
        
        players = Integer.parseInt(data.get("players"));
        points = Integer.parseInt(data.get("points"));
        
        Map<Integer, Long> score = new HashMap<>();
    }
    
    public Map<Integer, Long> getScores()
    {
        return scores;
    }
    
    public int getPlayers()
    {
        return players;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void setPoints(int points)
    {
        this.points = points;
    }
}
