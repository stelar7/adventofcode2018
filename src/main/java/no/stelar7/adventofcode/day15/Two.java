package no.stelar7.adventofcode.day15;

import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day15.input", false).getDataSource();
        
        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            try
            {
                Grid g = new Grid(input, i);
                System.out.println(g.play(true));
            } catch (RuntimeException e)
            {
                System.out.println("Elf died in power " + i);
            }
        }
    }
}
