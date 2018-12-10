package no.stelar7.adventofcode.day10;

import no.stelar7.adventofcode.Utils;

import java.util.Map;

public class Point
{
    int x, dx, y, dy;
    
    final String regex = "position=<(?<x> ?-?\\d+), (?<y> ?-?\\d+)> velocity=<(?<dx> ?-?\\d+), (?<dy> ?-?\\d+)>";
    
    public Point(String input)
    {
        Map<String, String> data = Utils.extractRegex(input, regex, Point.class);
        
        this.x = Integer.parseInt(data.get("x").trim());
        this.dx = Integer.parseInt(data.get("dx").trim());
        this.y = Integer.parseInt(data.get("y").trim());
        this.dy = Integer.parseInt(data.get("dy").trim());
    }
    
    public Point(int x, int y, int dx, int dy)
    {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
    
    public Point move()
    {
        return new Point(x + dx, y + dy, dx, dy);
    }
}