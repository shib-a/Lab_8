package org.example.collection;

import java.util.AbstractCollection;

public interface CollectionManager<T extends AbstractCollection<E>, E> {
    T getCollection();
    void addElement(E element);
    void clearCollection();
}
