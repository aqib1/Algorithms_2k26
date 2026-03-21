package ocp.scope;

public class Light {
    protected String name = "Light 1"; // this will be assessable inside package and outside only within child classes.

    {
        IO.println("This is parent first instance block");
    }

    public Light() {
        IO.println("This is light - parent");
    }

    {
        IO.println("This is parent second instance block");
    }

    static {
        IO.println("This is static parent first instance block");
    }

    static {
        IO.println("This is static parent second instance block");
    }

    static {
        IO.println("This is static parent third instance block");
    }
}
