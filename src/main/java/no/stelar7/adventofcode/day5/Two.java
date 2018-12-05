package no.stelar7.adventofcode.day5;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.*;
import java.util.stream.IntStream;

public class Two
{
    public static void main(String[] args)
    {
        String source = StringFromFileSupplier.create("day5.input", false).get();
        
        Map<Character, Integer> reducesWithout = new HashMap<>();
        IntStream.rangeClosed('A', 'Z')
                 .parallel()
                 .forEach(j -> {
                     String text = source;
                     text = text.replaceAll(Character.toString((char) j), "");
                     text = text.replaceAll(Character.toString((char) (j + 32)), "");
                     char[]  data      = text.toCharArray();
                     boolean didChange = true;
                     while (didChange)
                     {
                         didChange = false;
                
                         for (int i = 0; i < data.length - 1; i++)
                         {
                             if (Math.abs(data[i] - data[i + 1]) == 32)
                             {
                                 data = Utils.removeIndex(data, i);
                                 data = Utils.removeIndex(data, i);
                                 i--;
                                 didChange = true;
                             }
                         }
                     }
                     reducesWithout.put((char) j, data.length);
                 });
        
        int min = reducesWithout.values().stream().reduce(Integer.MAX_VALUE, (a, b) -> a > b ? b : a);
        System.out.println(min);
    }
}
