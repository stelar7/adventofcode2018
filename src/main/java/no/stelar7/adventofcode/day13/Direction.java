package no.stelar7.adventofcode.day13;

public enum Direction
{
    LEFT('<'), UP('^'), RIGHT('>'), DOWN('v');
    public char icon;
    
    Direction(char c)
    {
        this.icon = c;
    }
}
