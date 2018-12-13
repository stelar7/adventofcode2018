package no.stelar7.adventofcode.day13;

import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    enum Direction
    {
        LEFT('<'), UP('^'), RIGHT('>'), DOWN('v');
        public char icon;
        
        Direction(char c)
        {
            this.icon = c;
        }
    }
    
    enum Intersection
    {
        LEFT, STRAIGHT, RIGHT;
        
        public Intersection nextTurn()
        {
            switch (this)
            {
                case STRAIGHT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
                case LEFT:
                    return STRAIGHT;
            }
            return this;
        }
    }
    
    static class Cart
    {
        int          x;
        int          y;
        Direction    dir;
        Intersection turn = Intersection.LEFT;
        char[][]     grid;
        
        public Cart(char[][] grid, int i, int j)
        {
            this.x = j;
            this.y = i;
            this.grid = grid;
            
            switch (grid[i][j])
            {
                case 'v':
                {
                    dir = Direction.DOWN;
                    break;
                }
                case '^':
                {
                    dir = Direction.UP;
                    break;
                }
                case '<':
                {
                    dir = Direction.LEFT;
                    break;
                }
                case '>':
                {
                    dir = Direction.RIGHT;
                    break;
                }
            }
        }
        
        public void move()
        {
            switch (dir)
            {
                case LEFT:
                    x--;
                    break;
                case UP:
                    y--;
                    break;
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y++;
                    break;
            }
            char    pos            = grid[y][x];
            boolean isIntersection = pos == '+';
            boolean isTurn         = pos == '/';
            isTurn |= pos == '\\';
            
            if (isTurn)
            {
                switch (dir)
                {
                    case LEFT:
                    {
                        dir = pos == '/' ? Direction.DOWN : Direction.UP;
                        break;
                    }
                    case RIGHT:
                    {
                        dir = pos == '/' ? Direction.UP : Direction.DOWN;
                        break;
                    }
                    case UP:
                    {
                        dir = pos == '/' ? Direction.RIGHT : Direction.LEFT;
                        break;
                    }
                    case DOWN:
                    {
                        dir = pos == '/' ? Direction.LEFT : Direction.RIGHT;
                        break;
                    }
                }
            }
            
            if (isIntersection)
            {
                switch (turn)
                {
                    case LEFT:
                    {
                        if (dir == Direction.UP)
                        {
                            dir = Direction.LEFT;
                            break;
                        }
                        if (dir == Direction.DOWN)
                        {
                            dir = Direction.RIGHT;
                            break;
                        }
                        if (dir == Direction.LEFT)
                        {
                            dir = Direction.DOWN;
                            break;
                        }
                        if (dir == Direction.RIGHT)
                        {
                            dir = Direction.UP;
                            break;
                        }
                    }
                    case RIGHT:
                    {
                        if (dir == Direction.UP)
                        {
                            dir = Direction.RIGHT;
                            break;
                        }
                        if (dir == Direction.DOWN)
                        {
                            dir = Direction.LEFT;
                            break;
                        }
                        if (dir == Direction.LEFT)
                        {
                            dir = Direction.UP;
                            break;
                        }
                        if (dir == Direction.RIGHT)
                        {
                            dir = Direction.DOWN;
                            break;
                        }
                    }
                    case STRAIGHT:
                    {
                        break;
                    }
                }
                turn = turn.nextTurn();
            }
        }
    }
    
    public static void main(String[] args)
    {
        List<String>    input = StringFromFileSupplier.create("day13.input", false).getDataSource();
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
        
        while (true)
        {
            print(grid, carts);
            carts.forEach(Cart::move);
            carts.sort(Comparator.comparing(c -> c.y));
        }
    }
    
    public static void print(char[][] grid, List<Cart> carts)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                int finalJ = j;
                int finalI = i;
                
                List<Cart> matches = carts.stream().filter(c -> c.x == finalJ && c.y == finalI).collect(Collectors.toList());
                if (matches.size() == 1)
                {
                    //      System.out.print(matches.get(0).dir.icon);
                } else if (matches.size() > 1)
                {
                    //System.out.println(matches.get(0).x + "," + matches.get(0).y);
                    carts.removeAll(matches);
                    System.out.println(carts.size());
                    if (carts.size() <= 3)
                    {
                        Cart winner = carts.get(0);
                        System.out.println(winner.x + "," + winner.y);
                        
                    }
                }
                {
                    //     System.out.print(grid[i][j]);
                }
            }
            //  System.out.println();
        }
    }
}
