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
    
    public StringFromFileSupplier(String inputFile, String separator, boolean infinite)
    {
        this.infinite = infinite;
        this.data = Arrays.stream(Utils.readFile(inputFile).split(separator))
                          .collect(Collectors.toList());
    }
    
    public static StringFromFileSupplier create(String inputFile, String separator, boolean infinite)
    {
        return new StringFromFileSupplier(inputFile, separator, infinite);
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
    
    public String asString()
    {
        return data.get(0);
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
