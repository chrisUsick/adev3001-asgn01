package ca.cu_dev;

import java.util.NoSuchElementException;

/**
 * Created by chris on 26-Sep-16.
 */
public class LinkedList<E> {
    private static final String NO_ELEMENT_EXISTS = "Element does not exist";
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {

    }

    public boolean add(E element) {
        linkHead(element);
        return true;
    }

    public boolean addAfter(E element, int position) {
        validatePosition(position);
        Node<E> current;
        if (position == size - 1) {
            linkTail(element);
        } else {
            current = find(position);
            link(element, current, current.getNext());
        }

        return true;
    }

    public boolean addAfter(E element, E data) {
        Node<E> current = find(data);
        if (current != null) {
            if (current == tail) {
                linkTail(element);
            } else {
                link(element, current, current.getNext());
            }
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return true;
    }

    public boolean addBefore(E element, int position) {
        validatePosition(position);
        if (position == 0) {
            linkHead(element);
        } else {
            Node<E> current = find(position);
            link(element, current.getPrevious(), current);
        }
        return false;
    }

    public boolean addBefore(E element, E data) {
        Node<E> current = find(data);
        if (current != null) {
            if(current == head) {
                linkHead(element);
            } else {
                link(element, current.getPrevious(), current);
            }
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return true;
    }

    public boolean addFirst(E data) {
        linkHead(data);
        return true;
    }

    public boolean addLast(E data) {
        linkTail(data);
        return true;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E get() {
        return null;
    }

    public E get(int position) {
        validatePosition(position);
        Node<E> node = find(position);
        return node.getElement();
    }

    public E get(E data) {
        Node<E> current = find(data);
        if (current != null) {
            return current.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    public E getFirst() {
        if (head != null) {
            return head.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    public E getLast() {
        if (tail != null) {
            return tail.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean insert(E data) {
        return false;
    }

    public E remove() {
        return null;
    }

    public E remove(int position) {
        return null;
    }

    public E remove(E data) {
        return null;
    }

    public E removeFirst() {
        return null;
    }

    public E removeLast() {
        return null;
    }

    public E set(E data) {
        return null;
    }

    public E set(E data, int position) {
        return null;
    }

    public E set(E dataToReplace, E data) {
        return null;
    }

    public E setFirst(E data) {
        return null;
    }

    public E setLast(E data) {
        return null;
    }

    private Node<E> find(int position) {
        Node<E> current = head;
        int i = 0;
        while(i < position) {
            current = head.getNext();
            ++i;
        }
        return current;
    }

    private Node<E> find(E data) {
        Node<E> current = head;
        while(current != null) {
            if (current.getElement().equals(data)) {
                break;
            }
            current = current.getNext();
        }

        return current;
    }

    private void linkHead(E element) {
        Node<E> toAdd = new Node<E>(element, null, head);
        if (head != null) {
            head.setPrevious(toAdd);
        }
        head = toAdd;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    private void linkTail(E element) {
        Node<E> toAdd = new Node<>(element, tail, null);
        if (tail != null) {
            tail.setNext(toAdd);
        }
        tail = toAdd;
        if (size == 0) {
            head = tail;
        }
        size++;
    }

    private void link(E element, Node<E> previous, Node<E> current) {
        Node<E> toAdd = new Node<>(element, previous, current);
        previous.setNext(toAdd);
        current.setPrevious(toAdd);
        size++;
    }

    private E setData(E data, Node<E> current) {
        return null;
    }

    private E unlink(Node<E> current) {
        return null;
    }

    private E unlinkHead() {
        return null;
    }

    private E unlinkTail() {

        return null;
    }

    private void validatePosition(int position) {
        if ((position == size && size == 0) || position < 0 || position > size) {
            throw new NoSuchElementException(
                    String.format("Invalid position: %d", position));
        }
    }








}
