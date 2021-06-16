package prefix;

public class GenericMemoryCell<T> {
    private T value;
    public T read() {
        return value;
    }
    public void write(T val) {
        value = val;
    }

    public static void main(String[] args) {
        GenericMemoryCell<String>[] arr1 = new GenericMemoryCell[10];
//        arr1[0] = new GenericMemoryCell<>();
        arr1[0].write("1");
        arr1[0].read();
    }
}
