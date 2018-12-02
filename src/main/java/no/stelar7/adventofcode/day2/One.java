package no.stelar7.adventofcode.day2;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.*;

import java.util.*;

public class One
{
    public static void main(String[] args)
    {
        int[] count = {0, 0};
        StringFromFileSupplier.create("day2.input", false)
                              .getDataSource()
                              .stream()
                              .map(Utils::letterCount)
                              .forEach(a -> {
                                  if (Arrays.stream(a).anyMatch(c -> c == 2))
                                  {
                                      count[0]++;
                                  }
                                  if (Arrays.stream(a).anyMatch(c -> c == 3))
                                  {
                                      count[1]++;
                                  }
                              });
        
        System.out.println(count[0] * count[1]);
    }
}
