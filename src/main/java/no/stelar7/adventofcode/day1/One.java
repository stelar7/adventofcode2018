package no.stelar7.adventofcode.day1;

import no.stelar7.adventofcode.utils.IntFromFileSupplier;

import java.util.*;
import java.util.stream.Stream;

public class One
{
    public static void main(String[] args)
    {
        int freq = Stream.generate(new IntFromFileSupplier("day1.input", false))
                         .takeWhile(Objects::nonNull)
                         .mapToInt(Integer::valueOf)
                         .sum();
        
        System.out.println(freq);
    }
}
