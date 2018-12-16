package no.stelar7.adventofcode.day15;

import no.stelar7.adventofcode.utils.*;

import java.util.*;

public class One
{
    
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day15.input", false).getDataSource();
        
        Grid g = new Grid(input, 3);
        System.out.println(g.play(false));
    }
}
