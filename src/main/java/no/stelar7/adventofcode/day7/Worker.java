package no.stelar7.adventofcode.day7;

public class Worker
{
    public static int idCounter = 0;
    
    final int id;
    int  endTime;
    Node task;
    
    public Worker()
    {
        this.id = idCounter++;
    }
    
    @Override
    public String toString()
    {
        return String.format("Worker{id=%d, endTime=%d, task=%s}", id, endTime, task);
    }
}
