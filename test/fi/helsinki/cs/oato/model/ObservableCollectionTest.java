package fi.helsinki.cs.oato.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Collection;

public class ObservableCollectionTest {
    private Collection<String> innerCollectionMock;
    private ObservableCollection<String> observableCollection;
    private CollectionObserver<String> observer;
    
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        this.innerCollectionMock = mock(Collection.class);
        this.observableCollection = new ObservableCollection<String>(innerCollectionMock);
        this.observer = mock(CollectionObserver.class);

        this.observableCollection.addObserver(this.observer);
    }
    
    @Test
    public void testDelegatesToInnerCollection() {
        String element = "hello";
        
        // add(T)
        this.observableCollection.add(element);
        verify(this.innerCollectionMock).add(element);

        // remove(T)
        this.observableCollection.remove(element);
        verify(this.innerCollectionMock).remove(element);

        // contains(T)
        when(this.innerCollectionMock.contains(element)).thenReturn(true);
        assertThat(this.observableCollection.contains(element), is(true));

        when(this.innerCollectionMock.contains(element)).thenReturn(false);
        assertThat(this.observableCollection.contains(element), is(false));

        // clear
        this.observableCollection.clear();
        verify(this.innerCollectionMock).clear();
    }

    @Test
    public void testNotifiesObserversOnSuccessfulAdd() {
        when(this.innerCollectionMock.add(anyString())).thenReturn(true);
        String element = "hello";

        this.observableCollection.add(element);
        verify(this.observer).elementAdded(element);
    }

    @Test
    public void testDoesNotNotifyObserversOnUnsuccessfulAdd() {
        when(this.innerCollectionMock.add(anyString())).thenReturn(false);
        String element = "hello";

        this.observableCollection.add(element);
        verify(this.observer, never()).elementAdded(anyString());
    }

    @Test
    public void testDoesNotNotifyObserversOnUnsuccessfulRemove() {
        when(this.innerCollectionMock.remove(anyString())).thenReturn(false);
        String element = "hello";

        this.observableCollection.remove(element);
        verify(this.observer, never()).elementRemoved(anyString());
    }

    @Test
    public void testNotifiesObserversOnClear() {
        this.observableCollection.clear();
        verify(this.observer).collectionCleared();
    }
}
