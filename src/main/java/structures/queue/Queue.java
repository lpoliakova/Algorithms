package structures.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E> extends AbstractQueue<E> implements java.util.Queue<E> {

    private QueueElement<E> head;
    private QueueElement<E> tail;

    @Override
    public boolean offer(E e) {
        if (isEmpty()) {
            head = new QueueElement<E>(e);
        } else if (size() == 1) {
            tail = new QueueElement<E>(e);
            head.setNext(tail);
        } else {
            QueueElement<E> newTail = new QueueElement<E>(e);
            tail.setNext(newTail);
            tail = newTail;
        }
        return true;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = head.getElement();
        head = head.getNext();
        if (head == tail) {
            tail = null;
        }
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Queue))
            return false;

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((Queue<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator<E>(head);
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public int size() {
        if (isEmpty()) return 0;
        if (tail == null) return 1;

        int size = 0;
        for (Iterator<E> iter = iterator(); iter.hasNext(); iter.next()) {
            size++;
        }
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }
}
