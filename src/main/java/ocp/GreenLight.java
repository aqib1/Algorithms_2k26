package ocp;

import ocp.scope.Light;


// A class call parent constructor first and then run instance block first in order how they defined and then its own constructor.

public class GreenLight extends Light {
    private String _$$$$___ = "";
    {
        IO.println("This is child first instance block");
    }
    public GreenLight() {
        // Will call parent constructor first.
        // Then run all instance block (class level) defined outside in order they are defined
        // then below line will be printed
        IO.println("This is child green light constructor");
    }

    {
        IO.println("This is child second instance block");
    }

    static {
        IO.println("This is static child first instance block");
    }

    static {
        IO.println("This is static child second instance block");
    }

    static {
        IO.println("This is static child third instance block");
    }

    public void printLightName() {
        {
            IO.println("Instance block of method");
            super.name = "Here we go";
        }
        System.out.println(super.name);
    }


    public void test() {
        int value = 2;
        modifyPrimitive(value); // argument passed by value - a copy of the value so change will not affect on its original value
        System.out.println(value); // this will print 2

        var greenLight = new GreenLight();
        greenLight._$$$$___ = "This is junk";
        modifyReferenceType(greenLight); // when we pass a reference type, method receive a copy of reference - but remember
        // reference still point to the object so any change to object properties will be effective. But if we modify the reference
        // with new object then original passing reference will not change as a copy was passed.

        IO.println(greenLight._$$$$___);// this will print "This is modified junk"

    }

    public void modifyReferenceType(GreenLight greenLight) {
        greenLight._$$$$___ = "This is modified junk";
        greenLight = new GreenLight(); // resetting _$$$$___ with empty string
    }

    public void modifyPrimitive(int value) { // in java when we pass an argument into method, its always pass by value
        value = 55;
    }
}
