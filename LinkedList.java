public class LinkedList <T> implements List<T> {

    private Link<T> first;


    public LinkedList() {
        first = null;
    }

    //Returns the number of elements in this list
    public int size() {
        int counter = 0;
        for (Link<T> curr = first; curr != null; curr = curr.getNext())
            counter = counter + 1;
        return counter;
    }

    //Returns true if this list contains no elements.
    public boolean isEmpty() {
        return first == null;
    }
    //Adds element to the beginning of this list
    public void addFirst(T element) {
        if (element == null)
            throw new NullPointerException();
        first = new Link<T>(element, first);
    }

    //Returns the element at the specified position in this list.
    public T get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        Link<T> current = first;
        while (index > 0) {
            index = index - 1;
            current = current.getNext();
        }
        return (T) current.getData();
    }

    //Returns a String representing this object
    public String toString() {
        String output = "<";
        Link<T> current = first;
        while (current != null) {
            output = output + current.toString();
            current = current.getNext();
            if (current != null)
                output = output + ", ";
        }
        output = output + ">";
        return output;
    }

    //Remove the first Link which contains toRemove, if such one exists
    public boolean remove(Object toRemove) {
        Link<T> current = first;
        Link<T> prev = current;
        boolean removed = false;
        while (current != null & !removed) {
            if ((current.getData()).equals(toRemove)) {
                // if the first link should be removed
                if (first == current) {
                    first = first.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                removed = true;
            } else {
                prev = current;
                current = current.getNext();
            }
        }
        return removed;
    }

    // remove element from the list
    public T remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        Link<T> current = first;
        Link<T> prev = current;
        while (index > 0) {
            index = index - 1;
            prev = current;
            current = current.getNext();
        }
        T ans = (T) current.getData();
        if (first == current) {
            first = first.getNext();
        }
        else {
            prev.setNext(current.getNext());
        }
        return ans;
    }

    //Returns true if this list contains the specified element
    public boolean contains(Object element) {
        boolean output = false;
        for (Link<T> curr = first; curr != null & !output; curr = curr.getNext())
            output = element.equals(curr.getData());
        return output;
    }
    //Inserts the specified element at the specified position in this list
    public void add(int index, T element) {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        if (element == null)
            throw new NullPointerException();
        if(index == 0) {
            addFirst(element);
        }
        else {
            Link<T> prev = null ;
            Link<T> curr = first ;
            for(int i = 0; i < index; i = i + 1) {
                prev = curr ;
                curr = curr.getNext() ;
            }
            Link<T> toAdd = new Link<T>(element, curr);
            prev.setNext(toAdd);
        }
    }

    //Replaces the element at the specified position in this list with the specified element
    public T set(int index, T element) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        Link<T> current = first;
        while (index > 0) {
            index = index - 1;
            current = current.getNext();
        }
        T prev = current.getData();
        current.setData(element);
        return prev;
    }

    //Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    public int indexOf(T element) {
        int output = -1;
        int index = 0;
        for (Link<T> curr = first; curr != null & output == -1; curr = curr.getNext())
            if (curr.getData().equals(element))
                output = index;
            else
                index = index + 1;
        return output;
    }

    //Appends the specified element to the end of this list
    public boolean add(T element) {
        if (element == null)
            throw new NullPointerException();
        Link<T> newLink = new Link<T>(element);
        if (isEmpty()) {
            first = newLink;
        } else {
            Link<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newLink);
        }
        return true;
    }

    private static class Link<E> {

        private E data;
        private Link<E> next;

        public Link(E data, Link<E> next) {
            this.data = data;
            this.next = next;
        }

        public Link(E data) {
            this(data, null);
        }

        public Link<E> getNext() {
            return next;
        }

        public void setNext(Link<E> next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public E setData(E data) {
            E tmp = this.data;
            this.data = data;
            return tmp;
        }

        public String toString() {
            return data.toString();
        }

    }
}
