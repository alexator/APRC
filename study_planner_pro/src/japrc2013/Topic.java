package japrc2013;

public class Topic implements TopicInterface
{
    private String subject;
    private int duration;
    
    public Topic(String name, int duration)
    {
        this.subject = name;
        this.duration = duration;
    }

    @Override
    public String getSubject()
    {
        return subject;
    }

    @Override
    public int getDuration()
    {
        return duration;
    }

    @Override
    public void setTargetEvent(CalendarEventInterface target)
    {
    }

    @Override
    public CalendarEventInterface getTargetEvent()
    {
        return null;
    }
}
