package no.stelar7.adventofcode.day7;

import no.stelar7.adventofcode.Utils;

import java.util.*;

public class Edge
{
    public static final String regex = "Step (?<from>.) must be finished before step (?<to>.) can begin.";
    
    Node from;
    Node to;
    
    public Edge(String input, final List<Node> nodes)
    {
        Map<String, String> data = Utils.extractRegex(input, regex, Edge.class);
        Node                fr   = null;
        Node                tr   = null;
        
        Optional<Node> exists = nodes.stream().filter(n -> n.name.equals(data.get("from"))).findFirst();
        this.from = exists.orElseGet(() -> {
            Node n = new Node(data.get("from"));
            nodes.add(n);
            return n;
        });
        
        exists = nodes.stream().filter(n -> n.name.equals(data.get("to"))).findFirst();
        this.to = exists.orElseGet(() -> {
            Node n = new Node(data.get("to"));
            nodes.add(n);
            return n;
        });
    }
    
    public Edge(Node from, Node to)
    {
        this.from = from;
        this.to = to;
    }
    
    public Node getFrom()
    {
        return from;
    }
    
    public Node getTo()
    {
        return to;
    }
}
