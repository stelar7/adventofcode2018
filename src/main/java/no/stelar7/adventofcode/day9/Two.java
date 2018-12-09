package no.stelar7.adventofcode.day9;

import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.*;

public class Two
{
    
    public static void main(String[] args)
    {
        String     input = StringFromFileSupplier.create("day9.input", false).asString();
        MarbleGame game  = new MarbleGame(input);
        game.setPoints(game.getPoints() * 100);
        Marble current = new Marble(0);
        
        int nextMarbleValue = 1;
        while (nextMarbleValue <= game.getPoints())
        {
            for (int i = 0; i < game.getPlayers() && nextMarbleValue <= game.getPoints(); i++)
            {
                if (nextMarbleValue % 23 == 0)
                {
                    Marble removed = Marble.remove(current.getPrev().getPrev().getPrev().getPrev().getPrev().getPrev().getPrev());
                    current = removed.getNext();
                    
                    int mval = nextMarbleValue + removed.getVal();
                    game.getScores().compute(i, (k, v) -> v == null ? mval : v + mval);
                } else
                {
                    current = Marble.insertAfter(current.getNext(), nextMarbleValue);
                }
                
                nextMarbleValue++;
            }
        }
        game.getScores()
            .values()
            .stream()
            .sorted(Comparator.comparing(Long::longValue).reversed())
            .limit(1)
            .forEach(System.out::println);
    }
}
