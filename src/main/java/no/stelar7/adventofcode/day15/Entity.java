package no.stelar7.adventofcode.day15;

public class Entity
{
    Team    team;
    Node    location;
    int     hp     = 200;
    int     power  = 3;
    boolean living = true;
    
    @Override
    public String toString()
    {
        return "Entity{" +
               "team=" + team +
               ", location=" + location +
               ", hp=" + hp +
               ", power=" + power +
               ", living=" + living +
               '}';
    }
    
    public int compareHealthThenLocationTo(Entity b)
    {
        return hp > b.hp ? hp : b.hp > hp ? b.hp : compareLocationTo(b);
    }
    
    public int compareHealthTo(Entity b)
    {
        return hp > b.hp ? hp : b.hp > hp ? b.hp : 0;
    }
    
    public int compareLocationTo(Entity o)
    {
        return location.compareTo(o.location);
    }
}
