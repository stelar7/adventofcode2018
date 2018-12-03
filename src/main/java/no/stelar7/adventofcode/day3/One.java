package no.stelar7.adventofcode.day3;

import no.stelar7.adventofcode.utils.StringFromFileSupplier;

public class One
{
    public static void main(String[] args)
    {
        int[][] fabric = new int[2000][2000];
        
        StringFromFileSupplier.create("day3.input", false)
                              .getDataSource()
                              .stream()
                              .map(Claim::new)
                              .forEach(c -> c.mark(fabric));
        
        int count = 0;
        for (int[] columns : fabric)
        {
            for (int value : columns)
            {
                if (value >= 2)
                {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
}
