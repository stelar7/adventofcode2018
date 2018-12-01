package no.stelar7.adventofcode.day1;

import no.stelar7.adventofcode.Utils;

public class One
{
    public static void main(String[] args)
    {
        String   input = Utils.readFile("day1.input");
        String[] lines = input.split("\n");
        int      freq  = 0;
        for (String line : lines)
        {
            int val = Integer.parseInt(line);
            freq += val;
        }
        System.out.println(freq);
    }
}
