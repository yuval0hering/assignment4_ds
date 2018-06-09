public interface Queue<T> {
    //Returns true iff this queue is empty.
    public boolean isEmpty();

    //Removes the object at the front of this queue and returns that object.
    public T dequeue();

    //Insert an item into the back of this queue.
    public void enqueue(T element);

    //Returns the top element without removing it.
    public T peek();
}