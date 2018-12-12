package no.stelar7.adventofcode.day12;

import no.stelar7.adventofcode.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Two
{
    
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day12.input", false).getDataSource();
        
        // read data, and fill an array x4 the input size, so that we can grow in both directions
        char[]           initialState = input.get(0).substring("initial state: ".length()).toCharArray();
        List<ForestRule> rules        = input.stream().skip(2).map(ForestRule::new).collect(Collectors.toList());
        char[]           forest       = new char[initialState.length * 4];
        Arrays.fill(forest, '.');
        System.arraycopy(initialState, 0, forest, initialState.length, initialState.length);
        
        // setup variables to control generations
        char[] currentGen = forest;
        char[] nextGen    = new char[forest.length];
        
        // special values to index into -2 and +2 of the array
        String dotdot = "..";
        char[] parseMe;
        
        // special values to keep track of score
        int lastDiff     = 0;
        int lastGenScore = 0;
        
        // keep looping forever!
        for (long g = 1; g <= Integer.MAX_VALUE; g++)
        {
            // update the forest
            parseMe = (dotdot + new String(currentGen) + dotdot).toCharArray();
            for (int i = 0; i < forest.length; i++)
            {
                final String         match    = String.valueOf(parseMe, i, 5);
                Optional<ForestRule> matching = rules.stream().filter(a -> a.matches(match)).findFirst();
                nextGen[i] = matching.map(forestRule -> forestRule.to).orElse('.');
            }
            
            // move to next generation
            currentGen = nextGen;
            System.out.println(Arrays.toString(currentGen).replace(", ", ""));
            
            // calculate score for current generation
            int score = 0;
            for (int i = 0; i < currentGen.length; i++)
            {
                if (currentGen[i] == '#')
                {
                    score += (i - initialState.length);
                }
            }
            
            // end if the change in score is the same for 2 generations (assume we have stabilized)
            // and calculate the score after 5B generations
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
}
