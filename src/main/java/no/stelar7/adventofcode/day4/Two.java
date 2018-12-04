package no.stelar7.adventofcode.day4;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.day4.One.Action;
import no.stelar7.adventofcode.utils.*;

import java.time.*;
import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        Map<String, List<Pair<Action, LocalDateTime>>> sleep = One.generateSleepData(StringFromFileSupplier.create("day4.input", false).getDataSource());
        
        String maxGuard = "";
        int    maxSleep = -1;
        int    maxIndex = -1;
        for (String gard : sleep.keySet())
        {
            List<int[]> sleeps = One.buildSleepMatrix(sleep, gard);
            int[]       trans  = One.transposeAndReduce(sleeps);
            int         index  = Utils.getIndexOfHighestValue(trans);
            
            if (trans[index] > maxSleep)
            {
                maxIndex = index;
                maxSleep = trans[index];
                maxGuard = gard;
            }
        }
        
        System.out.println("Guard: " + maxGuard);
        System.out.println("minute: " + maxIndex);
        System.out.println("key: " + Integer.parseInt(maxGuard) * maxIndex);
    }
}
