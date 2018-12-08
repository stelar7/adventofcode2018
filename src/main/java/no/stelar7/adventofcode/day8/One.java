package no.stelar7.adventofcode.day8;

import no.stelar7.adventofcode.utils.*;

import java.util.*;

public class One
{
    public static void main(String[] args)
    {
        List<Integer> data = IntFromFileSupplier.create("day8.input", " ", false)
                                                .getDataSource();
        
        TreeNode t = new TreeNode(0, data);
        System.out.println(t.values());
    }
}
