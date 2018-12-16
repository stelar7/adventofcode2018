package no.stelar7.adventofcode.day15;

import no.stelar7.adventofcode.utils.Pair;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Grid
{
    List<Entity> units = new ArrayList<>();
    List<Node>   nodes = new ArrayList<>();
    
    public Grid(List<String> lines, int elfPower)
    {
        for (int y = 0; y < lines.size(); y++)
        {
            String line = lines.get(y);
            for (int x = 0; x < line.toCharArray().length; x++)
            {
                char c = line.charAt(x);
                
                Node pt = new Node(x, y);
                pt.isWall = c == '#';
                nodes.add(pt);
                
                if (c == 'E' || c == 'G')
                {
                    Entity e = new Entity();
                    e.location = pt;
                    e.team = c == 'E' ? Team.ELF : Team.GOBLIN;
                    e.power = e.team == Team.ELF ? elfPower : 3;
                    units.add(e);
                }
            }
        }
    }
    
    public int play(boolean part2)
    {
        int round = 0;
        while (!round(part2))
        {
            if (!part2)
            {
                System.out.println(round++);
            }
        }
        
        return round * units.stream().filter(u -> u.living).mapToInt(u -> u.hp).sum();
    }
    
    public boolean round(boolean part2)
    {
        units.sort(Entity::compareLocationTo);
        
        for (Entity unit : new ArrayList<>(units))
        {
            if (unit.living)
            {
                if (move(unit, part2))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean move(Entity unit, boolean part2)
    {
        List<Entity> targets = units.stream()
                                    .filter(u -> u.team != unit.team)
                                    .filter(u -> u.living)
                                    .collect(Collectors.toList());
        
        List<Node> occupied = units.stream()
                                   .filter(u -> u.living)
                                   .filter(u -> !u.equals(unit))
                                   .map(u -> u.location)
                                   .collect(Collectors.toList());
        
        if (targets.isEmpty())
        {
            return true;
        }
        
        List<Node> inRangeOf = targets.stream()
                                      .flatMap(e -> e.location.neighbours().stream())
                                      .filter(n -> !n.isWall)
                                      .filter(n -> !occupied.contains(n))
                                      .collect(Collectors.toList());
        
        if (!inRangeOf.contains(unit.location))
        {
            Node move = findMove(unit.location, inRangeOf);
            if (move != null)
            {
                unit.location = move;
            }
        }
        
        List<Entity> opponents = new ArrayList<>();
        unit.location.neighbours()
                     .forEach(neighbour -> targets.stream()
                                                  .filter(target -> target.location.equals(neighbour))
                                                  .forEach(opponents::add));
        
        if (!opponents.isEmpty())
        {
            opponents.sort(Entity::compareHealthThenLocationTo);
            Entity target = opponents.get(0);
            target.hp -= unit.power;
            
            if (target.hp <= 0)
            {
                target.living = false;
                if (part2 && target.team == Team.ELF)
                {
                    throw new RuntimeException();
                }
            }
        }
        
        return false;
    }
    
    private Node findMove(Node location, List<Node> targets)
    {
        Set<Node>                  seen    = new HashSet<>();
        Deque<Pair<Node, Integer>> visitin = new ArrayDeque<>();
        visitin.add(new Pair<>(location, 0));
        
        Map<Node, Pair<Integer, Node>> path = new HashMap<>();
        path.put(location, new Pair<>(0, null));
        
        List<Node> occupied = units.stream()
                                   .filter(u -> u.living)
                                   .map(e -> e.location)
                                   .collect(Collectors.toList());
        
        while (!visitin.isEmpty())
        {
            Pair<Node, Integer> pd = visitin.pop();
            for (Node neighbour : pd.getA().neighbours())
            {
                if (neighbour.isWall || occupied.contains(neighbour))
                {
                    continue;
                }
                
                if (!path.containsKey(neighbour) || path.get(neighbour).getA() > pd.getB() + 1)
                {
                    path.put(neighbour, new Pair(pd.getB() + 1, pd.getA()));
                }
                
                if (seen.contains(neighbour))
                {
                    continue;
                }
                
                if (visitin.stream().map(Pair::getA).noneMatch(a -> a.equals(neighbour)))
                {
                    visitin.add(new Pair<>(neighbour, pd.getB() + 1));
                }
            }
            seen.add(pd.getA());
        }
        
        int                 mindist     = Integer.MAX_VALUE;
        Node                closest     = null;
        Pair<Integer, Node> closestData = null;
        
        List<Entry<Node, Pair<Integer, Node>>> data = path.entrySet().stream().filter(e -> targets.contains(e.getKey())).collect(Collectors.toList());
        for (Entry<Node, Pair<Integer, Node>> d : data)
        {
            if (d.getValue().getA() < mindist)
            {
                closest = d.getKey();
                closestData = d.getValue();
            }
        }
        
        if (closest == null)
        {
            return null;
        }
        
        while (path.get(closest).getA() > 1)
        {
            closest = path.get(closest).getB();
        }
        
        return closest;
    }
}
