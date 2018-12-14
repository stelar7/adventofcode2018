package no.stelar7.adventofcode.day14;

import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    
    public static void main(String[] args)
    {
        int input = IntFromFileSupplier.create("day14.input", false).get();
        
        List<Integer> recipies      = new ArrayList<>(Arrays.asList(3, 7));
        int           maxIterations = 30_000_000;
        int[]         elfs          = {0, 1};
        
        for (int i = 0; i < maxIterations; i++)
        {
            String score = String.valueOf(recipies.get(elfs[0]) + recipies.get(elfs[1]));
            score.chars().forEach(c -> recipies.add(c - 48));
            
            elfs[0] = (elfs[0] + recipies.get(elfs[0]) + 1) % recipies.size();
            elfs[1] = (elfs[1] + recipies.get(elfs[1]) + 1) % recipies.size();
        }
        
        String part1 = recipies.stream()
                               .skip(input)
                               .limit(10)
                               .map(Object::toString)
                               .collect(Collectors.joining());
        
        System.out.println(part1);
        
    }
}
