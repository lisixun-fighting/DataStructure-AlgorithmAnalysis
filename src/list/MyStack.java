package list;

public class MyStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int topOfStack;
    private T[] theArray;

    public MyStack() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        topOfStack = -1;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return topOfStack;
    }

    public boolean isEmpty() {
        return size() == -1;
    }

    private void ensureCapacity(int newCapacity) {
        if(newCapacity < size())
            return;
        T[] old = theArray;
        theArray = (T[])new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theArray[i] = old[i];
    }

    public void push(T v) {
        if(theArray.length == ++topOfStack)
            ensureCapacity(size()*2 + 1);
        theArray[topOfStack] = v;
    }

    public T top() {
        return theArray[topOfStack];
    }

    public T poll() {
        T polled = top();
        topOfStack--;
        return polled;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty())
            System.out.print(stack.poll() + " ");
    }
}
