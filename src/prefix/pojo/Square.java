package prefix.pojo;

public class Square implements Shape {
    int x;
    int y;
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int area() {
        return x*y;
    }
}
