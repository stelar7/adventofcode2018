package no.stelar7.adventofcode.utils;

import no.stelar7.adventofcode.Utils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StringFromFileSupplier implements Supplier<String>, SourceSupplier<String>
{
    private List<String> data;
    private int          index = 0;
    private boolean      infinite;
    
    public StringFromFileSupplier(String inputFile, boolean infinite)
    {
        this.infinite = infinite;
        this.data = Arrays.stream(Utils.readFile(inputFile).split("\n"))
                          .collect(Collectors.toList());
    }
    
    public static StringFromFileSupplier create(String inputFile, boolean infinite)
    {
        return new StringFromFileSupplier(inputFile, infinite);
    }
    
    @Override
    public List<String> getDataSource()
    {
        return data;
    }
    
    @Override
    public String get()
    {
        if (infinite)
        {
            return data.get(index++ % data.size());
        }
        
        return index > data.size() - 1 ? null : data.get(index++);
    }
}
