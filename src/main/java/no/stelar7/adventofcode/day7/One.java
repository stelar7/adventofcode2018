package no.stelar7.adventofcode.day7;

import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day7.input", false).getDataSource();
        List<Node>   graph = buildGraph(input);
        
        ArrayList<Node> closedList = new ArrayList<>();
        TreeSet<Node>   openList   = buildOpenList(graph);
        
        while (!openList.isEmpty())
        {
            Node n = openList.pollFirst();
            
            closedList.add(n);
            removeNode(n, openList);
        }
        
        closedList.forEach(System.out::print);
    }
    
    public static TreeSet<Node> buildOpenList(List<Node> graph)
    {
        TreeSet<Node> openList = new TreeSet<>();
        for (Node n : graph)
        {
            if (n.in.size() == 0)
            {
                openList.add(n);
            }
        }
        
        return openList;
    }
    
    public static void removeNode(Node n, Set<Node> openList)
    {
        for (Iterator<Edge> it = n.out.iterator(); it.hasNext(); )
        {
            Edge e = it.next();
            Node m = e.to;
            it.remove();
            m.in.remove(e);
            
            if (m.in.isEmpty())
            {
                openList.add(m);
            }
        }
    }
    
    public static List<Node> buildGraph(List<String> input)
    {
        List<Node> nodes = new ArrayList<>();
        List<Edge> source = StringFromFileSupplier.create("day7.input", false)
                                                  .getDataSource()
                                                  .stream()
                                                  .map(a -> new Edge(a, nodes))
                                                  .collect(Collectors.toList());
        
        source.forEach(e -> {
            Node from = nodes.stream().filter(a -> a.equals(e.from)).findFirst().get();
            Node to   = nodes.stream().filter(a -> a.equals(e.to)).findFirst().get();
            from.addEdge(to);
        });
        
        nodes.sort(Comparator.comparing(Node::getName).reversed());
        return nodes;
    }
}
