package no.stelar7.adventofcode.day13;

enum Intersection
{
    LEFT, STRAIGHT, RIGHT;
    
    public Intersection nextTurn()
    {
        switch (this)
        {
            case STRAIGHT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case LEFT:
                return STRAIGHT;
        }
        return this;
    }
}
