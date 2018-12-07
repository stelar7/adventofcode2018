package no.stelar7.adventofcode.day7;

import java.util.*;

public class Node implements Comparable<Node>
{
    String name;
    
    HashSet<Edge> in  = new HashSet<>();
    HashSet<Edge> out = new HashSet<>();
    
    public Node(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Node addEdge(Node n)
    {
        Edge e = new Edge(this, n);
        out.add(e);
        n.in.add(e);
        return this;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    @Override
    public int compareTo(Node o)
    {
        return name.compareTo(o.name);
    }
}
