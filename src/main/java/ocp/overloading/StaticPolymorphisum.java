package ocp.overloading;

public class StaticPolymorphisum {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(String a, String b) {
        return add(Integer.parseInt(a), Integer.parseInt(b));
    }

    public int add(double a, double b) {
        return add((int)a, (int) b);
    }

    public int add(int [] a) { // valid overloading
        int sum = 0;
        for(int i: a) {
            sum += i;
        }
        return sum;
    }

//    public int add(int ...a) { // invalid overloading as varargs and [] is same in java after compilation
//        return 0;
//    }

    public void add(int b, int ...a) {} // add b to all numbers of a
//    public void sub(int ...a, int b) // compilation error as varargs should always be last argument
    
}
