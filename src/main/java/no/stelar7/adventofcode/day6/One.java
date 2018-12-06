package no.stelar7.adventofcode.day6;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class One
{
    public static void main(String[] args)
    {
        List<Point> source = StringFromFileSupplier.create("day6.input", false)
                                                   .getDataSource()
                                                   .stream()
                                                   .map(Point::new)
                                                   .collect(Collectors.toList());
        
        int     size = source.stream().mapToInt(p -> p.x > p.y ? p.x : p.y).max().getAsInt();
        int[][] grid = new int[size][size];
        
        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                Point p = new Point(x, y);
                List<Pair<Point, Integer>> dis = source.stream()
                                                       .map(a -> new Pair<>(a, Utils.manhattanDistance(a, p)))
                                                       .sorted(Comparator.comparing(Pair::getB))
                                                       .collect(Collectors.toList());
                
                Pair<Point, Integer> first  = dis.get(0);
                Pair<Point, Integer> second = dis.get(1);
                grid[x][y] = first.getB().equals(second.getB()) ? '.' : first.getA().getName();
            }
        }
        
        
        Set<Character> invalidChars = new HashSet<>();
        for (int y = 0; y < size; y++)
        {
            invalidChars.add((char) grid[0][y]);
            invalidChars.add((char) grid[0][size - 1]);
        }
        
        for (int x = 0; x < size; x++)
        {
            invalidChars.add((char) grid[x][0]);
            invalidChars.add((char) grid[size - 1][0]);
        }
        
        Long maxValue = Arrays.stream(grid)
                              .flatMapToInt(Arrays::stream)
                              .filter(c -> !invalidChars.contains((char) c))
                              .boxed()
                              .collect(Collectors.groupingBy(a -> a, Collectors.counting()))
                              .values()
                              .stream()
                              .mapToLong(i -> i)
                              .max()
                              .getAsLong();
        
        System.out.println(maxValue);
    }
}
