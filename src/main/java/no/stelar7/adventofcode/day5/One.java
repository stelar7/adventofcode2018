package no.stelar7.adventofcode.day5;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        String  source    = StringFromFileSupplier.create("day5.input", false).get();
        char[]  data      = source.toCharArray();
        
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
        
        System.out.println(data.length);
    }
}
