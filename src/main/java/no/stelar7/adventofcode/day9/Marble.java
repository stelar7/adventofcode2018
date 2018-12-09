package no.stelar7.adventofcode.day9;

public class Marble
{
    private int    val;
    private Marble next;
    private Marble prev;
    
    public Marble(int val, Marble prev, Marble next)
    {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
    
    public Marble(int val)
    {
        this.val = val;
        this.next = this;
        this.prev = this;
    }
    
    public int getVal()
    {
        return val;
    }
    
    public Marble getNext()
    {
        return next;
    }
    
    public Marble getPrev()
    {
        return prev;
    }
    
    @Override
    public String toString()
    {
        return "Marble{" + "val=" + val + '}';
    }
    
    
    public static Marble insertAfter(Marble marble, int value)
    {
        Marble insertMe = new Marble(value, marble, marble.next);
        marble.next.prev = insertMe;
        marble.next = insertMe;
        return insertMe;
    }
    
    public static Marble remove(Marble marble)
    {
        marble.prev.next = marble.next;
        marble.next.prev = marble.prev;
        return marble;
    }
    
}
