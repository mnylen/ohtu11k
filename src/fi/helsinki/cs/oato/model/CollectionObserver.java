package fi.helsinki.cs.oato.model;

import java.util.Collection;

public interface CollectionObserver<T> {
    /**
     * Called when element is added to the observed collection.
     * @param element the element
     */
    public void elementAdded(T element);

    /**
     * Called when element is removed from the observed collection.
     * @param element the element
     */
    public void elementRemoved(T element);

    /**
     * Called when the collection has been cleared.
     */
    public void collectionCleared();
}
