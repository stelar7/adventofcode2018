package no.stelar7.adventofcode.day6;

import no.stelar7.adventofcode.Utils;

import java.util.Map;

public class Point
{
    public static char   nameCount = 'A';
    public static String regex     = "(?<x>\\d+), (?<y>\\d+)";
    
    public int x;
    public int y;
    
    private final char name;
    
    public Point(String input)
    {
        Map<String, String> data = Utils.extractRegex(input, regex, Point.class);
        this.x = Integer.parseInt(data.get("x"));
        this.y = Integer.parseInt(data.get("y"));
        this.name = nameCount++;
    }
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.name = nameCount++;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public char getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(name);
    }
}
