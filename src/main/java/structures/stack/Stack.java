package structures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

    private StackElement<E> head;

    public boolean isEmpty() {
        return head == null;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return head.getItem();
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        E item = head.getItem();
        head = head.getNext();
        return item;
    }

    public void push(E item) {
        head = new StackElement<E>(item, head);
    }

    public Iterator<E> iterator() {
        return new StackIterator<>(head);
    }

    public int size() {
        if (isEmpty()) return 0;

        int size = 0;
        for (Iterator<E> iter = iterator(); iter.hasNext(); iter.next()) {
            size++;
        }
        return size;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Stack))
            return false;

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((Stack<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }
}
