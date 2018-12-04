package no.stelar7.adventofcode.day4;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class One
{
    public static void main(String[] args)
    {
        List<String> source = StringFromFileSupplier.create("day4.input", false)
                                                    .getDataSource();
        
        
        Map<String, List<Pair<Action, LocalDateTime>>> sleep = generateSleepData(source);
        
        Pair<String, Integer> bestSleeper = sleep.keySet()
                                                 .stream()
                                                 .map(a -> new Pair<>(a, Utils.sum(transposeAndReduce(buildSleepMatrix(sleep, a)))))
                                                 .max(Comparator.comparing(Pair::getB))
                                                 .get();
        
        
        List<int[]> sleeps = buildSleepMatrix(sleep, bestSleeper.getA());
        int[]       trans  = transposeAndReduce(sleeps);
        int         index  = Utils.getIndexOfHighestValue(trans);
        
        System.out.println("Guard: " + bestSleeper.getA());
        System.out.println("minute: " + index);
        System.out.println("key: " + Integer.parseInt(bestSleeper.getA()) * index);
    }
    
    
    enum Action
    {
        GUARD("Guard"), WAKE("wakes"), SLEEP("falls");
        
        String key;
        
        Action(String key)
        {
            this.key = key;
        }
        
        public static Action fromKey(String in)
        {
            return Stream.of(Action.values()).filter(t -> in.startsWith(t.key)).findFirst().get();
        }
    }
    
    private static String dateRegex = "\\[(?<year>\\d+)-(?<month>\\d+)-(?<day>\\d+) (?<hour>\\d+):(?<min>\\d+)]";
    
    public static Map<String, List<Pair<Action, LocalDateTime>>> generateSleepData(List<String> input)
    {
        List<String> source = input.stream()
                                   .sorted((a, b) -> {
                                       LocalDateTime first  = Utils.localDateTimeFromRegex(a, dateRegex);
                                       LocalDateTime second = Utils.localDateTimeFromRegex(b, dateRegex);
                                       return first.compareTo(second);
                                   }).collect(Collectors.toList());
        
        Map<String, List<Pair<Action, LocalDateTime>>> sleep      = new HashMap<>();
        List<Pair<Action, LocalDateTime>>              guardSleep = null;
        String                                         guard      = null;
        
        for (String line : source)
        {
            LocalDateTime time   = Utils.localDateTimeFromRegex(line.split("]")[0] + "]", dateRegex);
            String        action = line.substring(19);
            
            Action ac = Action.fromKey(action);
            switch (ac)
            {
                case GUARD:
                {
                    guard = Utils.extractRegex(action, "(?<id>\\d+)", "id").get("id");
                    guardSleep = sleep.getOrDefault(guard, new ArrayList<>());
                    break;
                }
                case WAKE:
                {
                    guardSleep.add(new Pair(Action.WAKE, time));
                    break;
                }
                case SLEEP:
                {
                    guardSleep.add(new Pair(Action.SLEEP, time));
                    break;
                }
            }
            
            sleep.put(guard, guardSleep);
        }
        return sleep;
    }
    
    public static int[] transposeAndReduce(List<int[]> matrix)
    {
        if (matrix.size() == 0)
        {
            return new int[60];
        }
        
        int[] trans = new int[matrix.get(0).length];
        for (int[] sleeparr : matrix)
        {
            for (int j = 0; j < trans.length; j++)
            {
                if (sleeparr[j] == 1)
                {
                    trans[j]++;
                }
            }
        }
        return trans;
    }
    
    public static List<int[]> buildSleepMatrix(Map<String, List<Pair<Action, LocalDateTime>>> sleep, String guard)
    {
        List<Pair<Action, LocalDateTime>> value     = sleep.get(guard);
        boolean                           sleeping  = false;
        LocalDateTime                     lastAwake = null;
        List<int[]>                       sleeps    = new ArrayList<>();
        for (int i = 1; i < value.size(); i++)
        {
            LocalDateTime time = value.get(i).getB();
            if (sleeping && value.get(i).getA() == Action.WAKE)
            {
                int[] sleeparr = new int[60];
                for (int j = lastAwake.getMinute(); j < time.getMinute(); j++)
                {
                    sleeparr[j] = 1;
                }
                sleeps.add(sleeparr);
                sleeping = false;
            } else if (value.get(i).getA() == Action.SLEEP)
            {
                sleeping = true;
                lastAwake = value.get(i).getB();
            }
        }
        
        return sleeps;
    }
}
