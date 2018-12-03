package no.stelar7.adventofcode;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Utils
{
    
    public static Map<String, String> extractRegex(String input, String regex, List<String> vars)
    {
        Map<String, String> matches = new HashMap<>();
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        m.find();
        
        vars.forEach(v -> matches.put(v, m.group(v)));
        
        return matches;
    }
    
    public static <T> Map<String, String> extractRegex(String input, String regex, Class clazz)
    {
        List<String> params = Arrays.stream(clazz.getDeclaredFields())
                                    .map(Field::getName)
                                    .collect(Collectors.toList());
        
        return extractRegex(input, regex, params);
    }
    
    public static String readFile(String filename)
    {
        InputStream file = Utils.class.getClassLoader().getResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(file)).lines().collect(Collectors.joining("\n"));
    }
    
    public static int min(int... numbers)
    {
        return Arrays.stream(numbers)
                     .min()
                     .orElse(Integer.MAX_VALUE);
    }
    
    
    public static int[] letterCount(String a)
    {
        int[] letterCount = new int[26];
        a.chars().forEach(c -> letterCount[c - 97]++);
        return letterCount;
    }
}
