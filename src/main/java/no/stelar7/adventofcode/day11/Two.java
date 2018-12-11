package no.stelar7.adventofcode.day11;

import no.stelar7.adventofcode.utils.*;

public class Two
{
    public static void main(String[] args)
    {
        
        int     serial = IntFromFileSupplier.create("day11.input", false).get();
        int[][] scores  = new int[301][301];
        for (int y = 1; y <= scores.length - 1; y++)
        {
            for (int x = 1; x <= scores[0].length - 1; x++)
            {
                int rackId      = x + 10;
                int startPower  = rackId * y;
                int serialPower = startPower + serial;
                int rackPower   = serialPower * rackId;
                int hundreds    = 0;
                if (rackPower > 100)
                {
                    hundreds = (rackPower / 100) % 10;
                }
                scores[x][y] = hundreds - 5;
            }
        }
        
        for (int y = 1; y <= scores.length - 1; y++)
        {
            for (int x = 1; x <= scores[0].length - 1; x++)
            {
                scores[x][y] = scores[x][y] + scores[x - 1][y] + scores[x][y - 1] - scores[x - 1][y - 1];
            }
        }
        
        int max   = 0;
        int xp    = 0;
        int yp    = 0;
        int sizep = 0;
        for (int blk = 1; blk <= 300; blk++)
        {
            for (int y = 1; y < 300 - blk + 1; y++)
            {
                for (int x = 1; x < 300 - blk + 1; x++)
                {
                    int tot = scores[x + blk][y + blk] - scores[x][y + blk] - scores[x + blk][y] + scores[x][y];
                    
                    if (tot > max)
                    {
                        max = tot;
                        xp = x + 1;
                        yp = y + 1;
                        sizep = blk;
                    }
                }
            }
        }
        
        System.out.println();
    }
}
