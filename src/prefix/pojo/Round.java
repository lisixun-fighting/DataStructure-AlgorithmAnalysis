package prefix.pojo;

public class Round implements Shape {
    int r;

    public Round(int r) {
        this.r = r;
    }

    @Override
    public int area() {
        return (int) Math.PI*r*r;
    }
}
