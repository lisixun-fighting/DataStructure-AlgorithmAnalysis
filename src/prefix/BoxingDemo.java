package prefix;

public class BoxingDemo {
    public static void main(String[] args) {
        GenericMemoryCell<Integer> cell = new GenericMemoryCell<>();
        cell.write(1);
        System.out.println(cell.read());
    }
}
