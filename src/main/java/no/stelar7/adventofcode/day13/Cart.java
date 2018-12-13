package no.stelar7.adventofcode.day13;

public class Cart
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
