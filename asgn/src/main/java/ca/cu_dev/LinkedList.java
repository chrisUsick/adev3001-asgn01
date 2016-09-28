package ca.cu_dev;

import java.util.NoSuchElementException;

/**
 * Created by chris on 26-Sep-16.
 */
public class LinkedList<E extends Comparable<E>> {
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
        if (position == size) {
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
        if (position == 1) {
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
        return getFirst();
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
        Node<E> previous = null, current;
        int compared;
        if (size == 0) {
            linkHead(data);
        } else {
            current = head;
            compared = current.getElement().compareTo(data);
            while(current != null && compared < 0) {
                previous = current;
                current = current.getNext();
                if (current != null) {
                    compared = current.getElement().compareTo(data);
                }
            }
            if (previous != null) {
                if (current != null) {
                    link(data, previous, current);
                } else {
                    linkTail(data);
                }
            } else {
                linkHead(data);
            }
        }
        return true;
    }

    public E remove() {
        return unlinkHead();
    }

    public E remove(int position) {
        E result;
        validatePosition(position);
        if (position == 1) {
            result = unlinkHead();
        } else if (position == size) {
            result = unlinkTail();
        } else {
            Node<E> current = find(position);
            result = unlink(current);
        }
        return result;
    }

    public E remove(E data) {
        Node<E> current = find(data);
        E result;
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }

        if (current == head) {
            result = unlinkHead();
        } else if (current == tail) {
            result = unlinkTail();
        } else {
            result = unlink(current);
        }
        return result;
    }

    public E removeFirst() {
        return remove();
    }

    public E removeLast() {
        return unlinkTail();
    }

    public E set(E dataToReplace) {
        if (isEmpty()) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return setData(dataToReplace, head);
    }

    public E set(E data, int position) {
        validatePosition(position);
        E oldData;
        if (position == size) {
            oldData = setData(data, tail);
        } else {
            Node<E> current = find(position);
            oldData = setData(data, current);
        }
        return oldData;
    }

    public E set(E dataToReplace, E data) {
        Node<E> current = find(data);
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return setData(dataToReplace, current);
    }

    public E setFirst(E data) {
        return set(data);
    }

    public E setLast(E data) {
        return setData(data, tail);
    }

    private Node<E> find(int position) {
        Node<E> current = head;
        int i = 1;
        while(i < position) {
            current = current.getNext();
            ++i;
        }
        return current;
    }

    private Node<E> find(E data) {
        Node<E> current = head;
        while(current != null) {
            if (current.getElement().compareTo(data) == 0) {
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
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        E replacedData = current.getElement();
        current.setElement(data);
        return replacedData;
    }

    private E unlink(Node<E> current) {
        Node<E> previous = current.getPrevious(),
                next = current.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        --size;
        return current.getElement();
    }

    private E unlinkHead() {
        if (head == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        Node<E> current = head;
        head = current.getNext();
        --size;
        if (head == null) {
            tail = null;
        }

        return current.getElement();
    }

    private E unlinkTail() {
        if (tail == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        Node<E> current = tail;
        tail = current.getPrevious();
        if (tail != null) {
            tail.setNext(null);
        }
        --size;
        if (tail == null) {
            head = null;
        }

        return current.getElement();
    }

    private void validatePosition(int position) {
        if ((position == size && size == 0) || position < 1 || position > size) {
            throw new NoSuchElementException(
                    String.format("Invalid position: %d", position));
        }
    }








}
