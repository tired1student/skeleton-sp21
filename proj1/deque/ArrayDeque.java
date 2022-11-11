package deque;

public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int volume = 1000;

    public ArrayDeque() {
        head = volume / 2 - 1;
        tail = volume / 2;
        items = (T[]) new Object[volume];
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (head == 0) {
            sizeExpend(volume);
            volume *= 2;
        }
        items[head] = item;
        head -= 1;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (tail == volume - 1) {
            sizeExpend(volume);
            volume *= 2;
        }
        items[tail] = item;
        tail += 1;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if(head + 1 == tail) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T x;
        x = items[head + 1];
        items[head + 1] = null;
        head += 1;
        size -= 1;
        if (size < volume / 4) {
            sizeShrink(volume);
            volume = volume / 2;
        }
        return x;
    }

    @Override
    public T removeLast() {
        T x;
        x = items[tail - 1];
        items[tail - 1] = null;
        tail -= 1;
        size -= 1;
        if (size < volume / 6) {
            sizeShrink(volume);
            volume = volume / 2;
        }
        return x;
    }

    private void sizeExpend(int capacity) {
        T[] temp = (T[]) new Object[capacity * 2];
        int h1 = capacity - size / 2;
        int t1 = h1 + size + 1;
        for (int i = h1 + 1; i < t1; i++) {
            temp[i] = items[i - h1 + head];
        }
        items = temp;
        head = h1;
        tail = t1;
    }

    private void sizeShrink(int capacity) {
        T[] temp = (T[]) new Object[capacity / 2];
        int h1 = capacity / 4;
        int t1 = h1 + size + 1;
        for (int i = h1 + 1; i < t1; i++) {
            temp[i] = items[i - h1 + head];
        }
        items = temp;
        head = h1;
        tail = t1;
    }

    @Override
    public T get(int index) {
        return items[head + 1 + index];
    }

    @Override
    public void printDeque() {
        for(int i = head + 1; i < tail; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }


}
