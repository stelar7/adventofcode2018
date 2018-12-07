package no.stelar7.adventofcode.day7;

import no.stelar7.adventofcode.utils.*;

import java.util.*;

public class Two
{
    
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day7.input", false).getDataSource();
        List<Node>   graph = One.buildGraph(input);
        
        List<Worker>  workers  = buildWorkerList(5);
        TreeSet<Node> openList = One.buildOpenList(graph);
        
        int[] timer = {0};
        while (true)
        {
            workers.forEach(w -> {
                if (w.task != null)
                {
                    if (w.endTime > timer[0])
                    {
                        return;
                    }
                    
                    One.removeNode(w.task, openList);
                    w.task = null;
                }
                
                if (!openList.isEmpty())
                {
                    w.task = openList.pollFirst();
                    w.endTime = timer[0] + w.task.name.charAt(0) - '@' + 60;
                }
            });
            
            if (openList.isEmpty() && workers.stream().allMatch(w -> w.task == null))
            {
                System.out.println(timer[0]);
                break;
            }
            
            timer[0]++;
        }
    }
    
    public static List<Worker> buildWorkerList(int count)
    {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            workers.add(new Worker());
        }
        
        return workers;
    }
}
