package learning.data.structures.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by edmundas on 15.6.13.
 */
public class LinkedList<E> implements List<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Object obj) {
        return (indexOf(obj) != -1);
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(E item) {
        Node<E> node = last;
        last = createNode(item, node, null);
        if(node == null) {
            first = last;
        } else {
            node.next = last;
        }
        return (++size > 0);
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public E get(int index) {
        int i = 0;
        for(Node<E> node = first; node != null; node = node.next) {
            if(i++ == index) {
                return node.item;
            }
        }
        return null;
    }

    public E set(int index, E item) {
        return null;
    }

    public void add(int index, E item) {
        int i = 0;
        for(Node<E> node = first; node != null; node = node.next) {
            if(i == index) {
                Node<E> newNode = createNode(item, node.prev, node);
                if(node.prev != null) {
                    node.prev.next = newNode;
                } else {
                    first = newNode;
                }
                node.prev = newNode;
                size++;
            }
            i++;
        }
        if(size == 0) {
            first = last = createNode(item, null, null);
        }
    }

    public E remove(int index) {
        return null;
    }

    public int indexOf(Object obj) {
        int index = 0;
        for(Node<E> node = first; node != null; node = node.next) {
            if(obj == null && node.item == null) {
                return index;
            } else if(obj.equals(node.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int index = size;
        for(Node<E> node = last; node != null; node = node.prev) {
            index--;
            if(obj == null && node.item == null) {
                return index;
            } else if(obj.equals(node.item)) {
                return index;
            }
        }
        return -1;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node<E> createNode(E item) {
        return createNode(item, null, null);
    }

    private final Node<E> createNode(E item, Node prev, Node next) {
        return new Node<E>(prev, item, next);
    }
}
