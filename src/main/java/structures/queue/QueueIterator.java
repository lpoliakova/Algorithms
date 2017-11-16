package structures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueIterator<E> implements Iterator<E> {
    private QueueElement<E> previous;
    private QueueElement<E> current;

    QueueIterator(QueueElement<E> head) {
        current = new QueueElement<E>(null, head);
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        previous = current;
        current = current.getNext();
        return current.getElement();
    }

    @Override
    public void remove() {
        if (previous == null) {
            throw new IllegalStateException();
        }
        previous.setNext(current.getNext());
        current = previous;
        previous = null;
    }
}
