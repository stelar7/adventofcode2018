package no.stelar7.adventofcode.day13;

import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    
    public static void main(String[] args)
    {
        List<String>               input = StringFromFileSupplier.create("day14.input", false).getDataSource();
        Pair<char[][], List<Cart>> data  = setupGrid(input);
        
        char[][]   grid  = data.getA();
        List<Cart> carts = data.getB();
        
        outer:
        while (true)
        {
            //print(grid, carts);
            for (Cart cart : carts)
            {
                cart.move();
                Pair<Boolean, List<Cart>> onPoint = checkCollision(cart.x, cart.y, carts);
                if (onPoint.getA())
                {
                    System.out.println(cart.x + "," + cart.y);
                    break outer;
                }
            }
            carts.sort(Comparator.comparing((Cart c) -> c.y).thenComparing((Cart c) -> c.x));
        }
    }
    
    private static Pair<char[][], List<Cart>> setupGrid(List<String> input)
    {
        List<Cart>      carts = new ArrayList<>();
        List<Character> dirs  = Arrays.asList('<', '^', '>', 'v');
        
        int gridSize = Math.max(input.size(), input.stream().mapToInt(String::length).max().getAsInt()) + 1;
        
        char[][] grid = new char[gridSize][gridSize];
        for (int i = 0; i < grid.length; i++)
        {
            if (i > input.size() - 1)
            {
                break;
            }
            
            String gridline = input.get(i);
            for (int j = 0; j < grid[0].length; j++)
            {
                if (j > gridline.length() - 1)
                {
                    break;
                }
                
                grid[i][j] = gridline.charAt(j);
                if (dirs.contains(grid[i][j]))
                {
                    Cart c = new Cart(grid, i, j);
                    carts.add(c);
                    
                    if (c.dir == Direction.DOWN || c.dir == Direction.UP)
                    {
                        grid[i][j] = '|';
                    }
                    
                    if (c.dir == Direction.RIGHT || c.dir == Direction.LEFT)
                    {
                        grid[i][j] = '-';
                    }
                }
            }
        }
        return new Pair<>(grid, carts);
    }
    
    private static Pair<Boolean, List<Cart>> checkCollision(int x, int y, List<Cart> carts)
    {
        List<Cart> onPoint = carts.stream()
                                  .filter(c -> c.x == x && c.y == y)
                                  .collect(Collectors.toList());
        
        return new Pair(onPoint.size() > 1, onPoint);
    }
    
    public static void print(char[][] grid, List<Cart> carts)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                int finalJ = j;
                int finalI = i;
                
                char output = carts.stream()
                                   .filter(c -> c.x == finalJ && c.y == finalI)
                                   .findFirst()
                                   .map(a -> a.dir.icon)
                                   .orElse(grid[i][j]);
                
                System.out.print(output);
            }
            System.out.println();
        }
    }
}
