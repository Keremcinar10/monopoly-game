package lab27;

class EventCell extends Cell 
{
    int eventType; 
    public EventCell(String name, int eventType)
    {
        super(name);
        this.eventType=eventType;
    }
}
