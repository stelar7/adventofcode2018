package no.stelar7.adventofcode.day1;

import no.stelar7.adventofcode.Utils;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        String       input = Utils.readFile("day1.input");
        String[]     lines = input.split("\n");
        Set<Integer> set   = new HashSet<>();
        int          freq  = 0;
        set.add(freq);
        
        outer:
        while (true)
        {
            for (String line : lines)
            {
                int val = Integer.parseInt(line);
                freq += val;
                if (!set.add(freq))
                {
                    System.out.println(freq);
                    break outer;
                }
            }
        }
    }
}
