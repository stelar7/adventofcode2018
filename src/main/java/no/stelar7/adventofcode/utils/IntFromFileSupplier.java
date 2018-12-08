package no.stelar7.adventofcode.utils;

import no.stelar7.adventofcode.Utils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IntFromFileSupplier implements Supplier<Integer>, SourceSupplier<Integer>
{
    private List<Integer> data;
    private int           index = 0;
    private boolean       infinite;
    
    public IntFromFileSupplier(String inputFile, boolean infinite)
    {
        this.infinite = infinite;
        this.data = Arrays.stream(Utils.readFile(inputFile).split("\n"))
                          .map(Integer::parseInt)
                          .collect(Collectors.toList());
    }
    
    public IntFromFileSupplier(String inputFile, String separator, boolean infinite)
    {
        this.infinite = infinite;
        this.data = Arrays.stream(Utils.readFile(inputFile).split(separator))
                          .map(Integer::parseInt)
                          .collect(Collectors.toList());
    }
    
    public static IntFromFileSupplier create(String inputFile, String separator, boolean infinite)
    {
        return new IntFromFileSupplier(inputFile, separator, infinite);
    }
    
    public static IntFromFileSupplier create(String inputFile, boolean infinite)
    {
        return new IntFromFileSupplier(inputFile, infinite);
    }
    
    public List<Integer> getDataSource()
    {
        return data;
    }
    
    @Override
    public Integer get()
    {
        if (infinite)
        {
            return data.get(index++ % data.size());
        }
        
        return index > data.size() - 1 ? null : data.get(index++);
    }
}
