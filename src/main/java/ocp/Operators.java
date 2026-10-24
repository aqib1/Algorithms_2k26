package ocp;

record Test(int a, int b) {

}
public class Operators {
    static void main() {
        var record = new Test(1, 2);
        IO.println(record.equals(new Test(1, 2)));
    }
}
