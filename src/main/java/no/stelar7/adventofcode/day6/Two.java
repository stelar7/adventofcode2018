package no.stelar7.adventofcode.day6;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        List<Point> source = StringFromFileSupplier.create("day6.input", false)
                                                   .getDataSource()
                                                   .stream()
                                                   .map(Point::new)
                                                   .collect(Collectors.toList());
        
        int size          = source.stream().mapToInt(p -> p.x > p.y ? p.x : p.y).max().getAsInt();
        int distanceCount = 0;
        
        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                final Point p = new Point(x, y);
                
                int distance = source.stream().mapToInt(a -> Utils.manhattanDistance(p, a)).sum();
                if (distance < 10000)
                {
                    distanceCount++;
                }
            }
        }
        
        System.out.println(distanceCount);
    }
}
