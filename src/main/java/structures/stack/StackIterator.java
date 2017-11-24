package structures.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterator<E> implements Iterator<E> {

    private StackElement<E> previous;
    private StackElement<E> current;

    StackIterator(StackElement<E> head) {
        current = new StackElement<>(null, head);
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
        return current.getItem();
    }
}
