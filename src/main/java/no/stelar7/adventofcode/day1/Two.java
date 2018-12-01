package no.stelar7.adventofcode.day1;

import no.stelar7.adventofcode.utils.IntFromFileSupplier;

import java.util.*;
import java.util.function.*;

public class Two
{
    public static void main(String[] args)
    {
        Supplier<Integer> data  = new IntFromFileSupplier("day1.input", true);
        Set<Integer>      known = new HashSet<>(Collections.singletonList(0));
        int               freq  = 0;
        
        do
        {
            freq += data.get();
        } while (known.add(freq));
        
        System.out.println(freq);
    }
}
