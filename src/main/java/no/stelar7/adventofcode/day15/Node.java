package no.stelar7.adventofcode.day15;

import java.util.*;

public class Node
{
    int     x;
    int     y;
    boolean isWall;
    
    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    List<Node> neighbours()
    {
        List<Node> n = new ArrayList<>();
        if (x < 40)
        {
            n.add(new Node(x + 1, y));
        }
        if (x >= 0)
        {
            n.add(new Node(x - 1, y));
        }
        if (y < 40)
        {
            n.add(new Node(x, y + 1));
        }
        if (y >= 0)
        {
            n.add(new Node(x, y - 1));
        }
        return n;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    @Override
    public String toString()
    {
        return "Node{" +
               "x=" + x +
               ", y=" + y +
               ", isWall=" + isWall +
               '}';
    }
    
    public int compareTo(Node o)
    {
        if (y > o.y)
        {
            return 1;
        } else if (o.y > y)
        {
            return -1;
        } else
        {
            if (x > o.x)
            {
                return 1;
            }
            if (o.x > x)
            {
                return -11;
            }
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Node node = (Node) o;
        return x == node.x &&
               y == node.y &&
               isWall == node.isWall;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, isWall);
    }
}
