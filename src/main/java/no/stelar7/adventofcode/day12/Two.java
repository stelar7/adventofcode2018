package no.stelar7.adventofcode.day12;

import no.stelar7.adventofcode.Utils;
import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Two
{
    
    public static void main(String[] args)
    {
        List<String> data = StringFromFileSupplier.create("day12.input", false).getDataSource();
        
        char[] initData = data.get(0).substring("initial state: ".length()).toCharArray();
        char[] init     = new char[initData.length * 4];
        Arrays.fill(init, '.');
        System.arraycopy(initData, 0, init, initData.length, initData.length);
        
        char[]           currentGen   = init;
        char[]           nextGen      = new char[init.length];
        List<ForestRule> rules        = data.stream().skip(2).map(ForestRule::new).collect(Collectors.toList());
        int              lastGenScore = 0;
        int              lastDiff     = 0;
        
        for (long g = 1; g <= 50000000000L; g++)
        {
            for (int i = 0; i < init.length; i++)
            {
                String match = "";
                for (int j = -2; j < 3; j++)
                {
                    if ((i + j) < 0 || (i + j) >= init.length)
                    {
                        match += ".";
                        continue;
                    }
                    match += currentGen[i + j];
                }
                
                String           finalMatch = match;
                List<ForestRule> matching   = rules.stream().filter(a -> a.matches(finalMatch)).collect(Collectors.toList());
                if (matching.size() > 0)
                {
                    nextGen[i] = matching.get(0).to.charAt(0);
                } else
                {
                    nextGen[i] = '.';
                }
            }
            currentGen = nextGen;
            nextGen = new char[init.length];
            System.out.println(Arrays.toString(currentGen).replace(", ", ""));
            
            int score = 0;
            for (int i = 0; i < currentGen.length; i++)
            {
                if (currentGen[i] == '#')
                {
                    score += (i - initData.length);
                }
            }
            
            if (lastDiff == (score - lastGenScore))
            {
                System.out.println("day 2: ");
                System.out.println(score + (50000000000L - g) * lastDiff);
                System.exit(0);
            }
            
            lastDiff = score - lastGenScore;
            lastGenScore = score;
        }
    }
    
    static class ForestRule
    {
        final String regex = "(?<from>.+) => (?<to>.)";
        String from;
        String to;
        
        public ForestRule(String input)
        {
            Map<String, String> rpar = Utils.extractRegex(input, regex, ForestRule.class);
            this.from = rpar.get("from");
            this.to = rpar.get("to");
        }
        
        public boolean matches(String input)
        {
            return input.equals(from);
        }
        
        @Override
        public String toString()
        {
            return "ForestRule{" +
                   "from='" + from + '\'' +
                   ", to='" + to + '\'' +
                   '}';
        }
    }
}
