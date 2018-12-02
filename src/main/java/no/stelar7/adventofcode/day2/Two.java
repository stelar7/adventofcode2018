package no.stelar7.adventofcode.day2;

import no.stelar7.adventofcode.utils.*;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> data = StringFromFileSupplier.create("day2.input", false).getDataSource();
        
        List<Triplet<String, String, Integer>> dataPoints = new ArrayList<>();
        for (int i = 0; i < data.size(); i++)
        {
            for (int j = i + 1; j < data.size(); j++)
            {
                String a = data.get(i);
                String b = data.get(j);
                
                dataPoints.add(new Triplet<>(a, b, LevenshtenDistance.compare(a, b)));
            }
        }
        
        dataPoints.sort(Comparator.comparing(Triplet::getC));
        
        String a = dataPoints.get(0).getA();
        String b = dataPoints.get(0).getB();
        for (int i = 0; i < a.length(); i++)
        {
            if (a.charAt(i) == b.charAt(i))
            {
                System.out.print(a.charAt(i));
            }
        }
        System.out.println();
    }
}
