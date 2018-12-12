package no.stelar7.adventofcode.day12;

import no.stelar7.adventofcode.Utils;

import java.util.Map;

public class ForestRule
{
    final String regex = "(?<from>.+) => (?<to>.)";
    String from;
    char   to;
    
    public ForestRule(String input)
    {
        Map<String, String> rpar = Utils.extractRegex(input, regex, ForestRule.class);
        this.from = rpar.get("from");
        this.to = rpar.get("to").charAt(0);
    }
    
    public boolean matches(String input)
    {
        return input.equals(from);
    }
    
    @Override
    public String toString()
    {
        return "ForestRule{" + "from='" + from + '\'' + ", to='" + to + '\'' + '}';
    }
}
