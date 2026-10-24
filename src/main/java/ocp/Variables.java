package ocp;

public class Variables {
    static void main() {
        int a = 1;
        int b = 3;
        int sum = a + b;

        byte c1 = 1;
        byte c2 = 2;

        int c3 = c1 + c2;


        short s1 = 2;
        short s2 = 2;

        int s3 = s1 + s2;


        // only _ and $ sign allowed in java variables name.
        int age; // valid
        int _age; // valid
        int $age; // valid
//        int &age; invalid

//        int a ge; invalid
//        int 3age; invalid


        // byte = 8 bits [-128 - 127]
        // short = 16 bits [-32768 - 32767]
        // int = 32 bits [-2"31, 2"31 -1]
        // long = 64 bits [-2"63, 2"63 - 1]
        // float = 32 bits [32 bit floating point - 16 before decimal and 16 after decimal]
        // double = 64 bits [64 bit floating point - 32 before decimal and 32 after decimal]
        // char = 16 bits
        // boolean = 8 bits


    }
}
