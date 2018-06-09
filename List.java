public interface List<T> {
    public int size();
    public boolean isEmpty();
    public void addFirst(T element);
    public T get(int index);
    public String toString();
    public boolean remove(Object toRemove);
    public boolean contains(Object element);
    public void add(int index, T element);
    public T set(int index, T element);
    public int indexOf(T element);
    public boolean add(T element);
    public T remove(int index);
}
