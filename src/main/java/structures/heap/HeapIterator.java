package structures.heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapIterator<E extends Comparable<E>> implements Iterator<E> {
    Heap<E> heap;

    HeapIterator(Heap<E> heap) {
        this.heap = new Heap<>(heap);
    }

    @Override
    public boolean hasNext() {
        return !heap.isEmpty();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return heap.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
