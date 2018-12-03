package no.stelar7.adventofcode.day3;

import no.stelar7.adventofcode.Utils;

import java.util.*;

public class Claim
{
    private int id;
    private int x;
    private int y;
    private int h;
    private int w;
    
    public Claim(String input)
    {
        String regex = "#(?<id>\\d+) @ (?<x>\\d+),(?<y>\\d+): (?<w>\\d+)x(?<h>\\d+)";
        
        Map<String, String> data = Utils.extractRegex(input, regex, Claim.class);
        id = Integer.parseInt(data.get("id"));
        x = Integer.parseInt(data.get("x"));
        y = Integer.parseInt(data.get("y"));
        w = Integer.parseInt(data.get("w"));
        h = Integer.parseInt(data.get("h"));
    }
    
    public void mark(int[][] fabric)
    {
        for (int xc = this.x; xc < this.x + this.w; xc++)
        {
            for (int yc = this.y; yc < this.y + this.h; yc++)
            {
                fabric[xc][yc]++;
            }
        }
    }
    
    public boolean overlapsNone(int[][] fabric)
    {
        return countOverlaps(fabric) == 0;
    }
    
    public int countOverlaps(int[][] fabric)
    {
        int over = 0;
        for (int xc = this.x; xc < this.x + this.w; xc++)
        {
            for (int yc = this.y; yc < this.y + this.h; yc++)
            {
                if (fabric[xc][yc] > 1)
                {
                    over++;
                }
            }
        }
        return over;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("Claim{i=%d, x=%d, y=%d, h=%d, w=%d}", id, x, y, h, w);
    }
}
