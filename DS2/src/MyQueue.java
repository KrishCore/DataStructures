import java.util.Queue;

public class MyQueue<I extends Number> {
    Queue queue;

    public MyQueue()
    {
        // needs works
    }

    public void offer(Integer value)
    {
        queue.offer(value);
    }

    public Integer element()
    {
        return (Integer) queue.element();
    }

    public Integer poll()
    {
        return (Integer) queue.poll();
    }

    public int size()
    {
        return queue.size();
    }

    public boolean isEmpty()
    {
        return queue==null || queue.isEmpty();
    }

    public void clear()
    {
        if (queue != null)
            for (int i = 0; i < queue.size(); i++)
                queue.poll();
    }
}
