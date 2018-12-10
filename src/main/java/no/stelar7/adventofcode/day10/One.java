package no.stelar7.adventofcode.day10;

import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    
    public static void main(String[] args)
    {
        List<Point> input = StringFromFileSupplier.create("day10.input", false)
                                                  .getDataSource()
                                                  .stream()
                                                  .map(Point::new)
                                                  .collect(Collectors.toList());
        
        
        for (int its = 0; its < 25000; its++)
        {
            print(input, its);
            input = input.stream().map(Point::move).collect(Collectors.toList());
        }
    }
    
    public static void print(List<Point> input, int lineNum)
    {
        int maxx   = input.stream().map(p -> p.x).max(Integer::compareTo).get();
        int minx   = input.stream().map(p -> p.x).min(Integer::compareTo).get();
        int maxy   = input.stream().map(p -> p.y).max(Integer::compareTo).get();
        int miny   = input.stream().map(p -> p.y).min(Integer::compareTo).get();
        int yrange = maxy - miny + 1;
        int xrange = maxx - minx + 1;
        
        if (yrange > 15)
        {
            return;
        }
        
        char[][] data = new char[yrange][];
        for (int y = 0; y < yrange; y++)
        {
            data[y] = new char[xrange];
            Arrays.fill(data[y], ' ');
        }
        
        for (Point point : input)
        {
            data[point.y - miny][point.x - minx] = '#';
        }
        
        for (char[] line : data)
        {
            System.out.println(Arrays.toString(line));
        }
    }
}
