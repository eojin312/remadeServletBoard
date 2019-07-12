package example;

public class AnyTest {
    public static void main(String[] args) {
        long a = 1246;
        int b = 10;
        double c = (double) a/ (double) b;
        System.out.println(c);
        int ceillingNo = (int) Math.ceil(c);
        System.out.println(ceillingNo);
    }
}
