package ocp;

import ocp.test.Light;

public class MainClass {

    // JDK -> (JRE -> (JVM + Libraries)) + Deployment tools (Compiler - javac, launcher, JShell, Debugger, JPackage)
    // API
    // Deployment (such as JPackage, which package our jar files and let those install and run to any system which even
    // does not have java or JVM installed as package take care of that)
    void main() {
//        for(int i = 0; i < 5.0; ++i) {
//            System.out.printf("This is a number %f\n", (float)i);
//        }
//
//
//        var name = IO.readln("Enter your name: ");
//        System.out.println("Name : "+ name);

        Light light1 = new Light();
        ocp.scope.Light light2 = new ocp.scope.Light(); // when two class with same name exists in different packages, we can not import both so one need to initialized with fully qualified name.
    }
}
