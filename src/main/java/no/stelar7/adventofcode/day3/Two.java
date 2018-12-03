package no.stelar7.adventofcode.day3;

import no.stelar7.adventofcode.utils.StringFromFileSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        int[][] fabric = new int[2000][2000];
        List<Claim> claims = StringFromFileSupplier.create("day3.input", false)
                                                   .getDataSource()
                                                   .stream()
                                                   .map(Claim::new)
                                                   .collect(Collectors.toList());
        claims.forEach(c -> c.mark(fabric));
        claims.stream().filter(c -> c.overlapsNone(fabric)).forEach(System.out::println);
    }
}
