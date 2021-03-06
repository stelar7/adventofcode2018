package no.stelar7.adventofcode.day1;

import no.stelar7.adventofcode.utils.IntFromFileSupplier;

public class One
{
    public static void main(String[] args)
    {
        int freq = IntFromFileSupplier.create("day1.input", false)
                                      .getDataSource()
                                      .stream()
                                      .mapToInt(Integer::valueOf)
                                      .sum();
        
        System.out.println(freq);
    }
}
