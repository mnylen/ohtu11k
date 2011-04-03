package fi.helsinki.cs.oato.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ObservableCollection<T> implements Collection<T> {
    private List<CollectionObserver<T>> observers = new ArrayList<CollectionObserver<T>>();
    private Collection<T> innerCollection;

    public ObservableCollection(Collection<T> innerCollection) {
        this.innerCollection = innerCollection;
    }

    public void addObserver(CollectionObserver<T> observer) {
        this.observers.add(observer);
    }

    public void removeObserver(CollectionObserver<T> observer) {
        this.observers.remove(observer);
    }

    public int size() {
        return innerCollection.size();
    }

    public boolean isEmpty() {
        return innerCollection.isEmpty();
    }

    public boolean contains(Object o) {
        return innerCollection.contains(o);
    }

    public Iterator<T> iterator() {
        return innerCollection.iterator();
    }

    public Object[] toArray() {
        return innerCollection.toArray();
    }

    public <T> T[] toArray(T[] ts) {
        return innerCollection.toArray(ts);
    }

    public boolean add(T element) {
        boolean returnValue = this.innerCollection.add(element);

        if (returnValue) {
            for (CollectionObserver<T> observer : this.observers) {
                observer.elementAdded(element);
            }
        }

        return returnValue;
    }

    public boolean remove(Object o) {
        boolean returnValue = this.innerCollection.remove(o);

        if (returnValue) {
            T element = (T)o;
            for (CollectionObserver<T> observer : this.observers) {
                observer.elementRemoved(element);
            }
        }

        return returnValue;
    }

    public boolean containsAll(Collection<?> objects) {
        return this.innerCollection.containsAll(objects);
    }

    public boolean addAll(Collection<? extends T> ts) {
        throw new UnsupportedOperationException(
                "addAll() is not yet supported.");
    }

    public boolean removeAll(Collection<?> objects) {
        throw new UnsupportedOperationException(
                "removeAll() is not yet supported by ObservableCollection");
    }

    public boolean retainAll(Collection<?> objects) {
        throw new UnsupportedOperationException(
                "retainAll() is not yet supported by ObservableCollection");
    }

    public void clear() {
        this.innerCollection.clear();
        for (CollectionObserver<T> observer : this.observers) {
            observer.collectionCleared();
        }
    }
}
