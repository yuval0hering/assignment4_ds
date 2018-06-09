public class QueueAsLinkedList <T> implements Queue<T>{

    private List<T> list;

    //constructor
    public QueueAsLinkedList() {
        this.list = new LinkedList<>();
    }

    //checking if the queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //inserting an element to the queue
    public void enqueue(T element) {
        if(element == null)
            throw new NullPointerException();
        list.add(element);
    }

    //taking an element out of the queue
    public T dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException();
        return list.remove(0);
    }

    //checking what is the first element in the queue
    public T peek() {
        if(isEmpty())
            throw new IllegalArgumentException();
        return list.get(0);
    }

}