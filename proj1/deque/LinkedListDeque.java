package deque;

public class LinkedListDeque<T> implements Deque<T>{

    private class StuffNode{
        public T item;
        public StuffNode next;
        public StuffNode previous;
        public StuffNode(T i, StuffNode n, StuffNode p){
            item = i;
            next = n;
            previous = p;
        }
    }

    private StuffNode head;
    private StuffNode tail;
    private int size;

    public LinkedListDeque(){
        head = new StuffNode(null, null, null);
        tail = new StuffNode(null, null, head);
        head.next = tail;
    }

    @Override
    public void addFirst(T item) {
        head.next = new StuffNode(item, head.next, head);
        head.next.next.previous = head.next;
        size += 1;
    }

    @Override
    public void addLast(T item){
        tail.previous = new StuffNode(item, tail, tail.previous);
        tail.previous.previous.next = tail.previous;
        size += 1;

    }

    @Override
    public boolean isEmpty(){
        if(head.next == tail) return true;
        else return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        StuffNode x = head.next;
        while(x != tail){
            System.out.print(x.item + " ");
            x = x.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (this.isEmpty()){
            return null;
        }
        T x = head.next.item;
        head.next = head.next.next;
        head.next.previous = head;
        size -= 1;
        return x;
    }

    @Override
    public T removeLast(){
        if (this.isEmpty()){
            return null;
        }
        T x = tail.previous.item;
        tail.previous = tail.previous.previous;
        tail.previous.next = tail;
        size -= 1;
        return x;
    }

    @Override
    public T get(int index){
        if (index + 1 > size) {
            System.out.println("Error!");
            return null;
        }
        StuffNode x = head.next;
        int i = index;
        while (true){
            if (i == 0) {
                return x.item;
            }
            i -= 1;
            x = x.next;
        }
    }

    public T getRecursive(int index) {
        return getRecursive(index, this.head);
    }

    private T getRecursive(int index, StuffNode node){
        if (index == 0) {
            return node.next.item;
        }
        return getRecursive(index - 1, node.next);
    }

}
