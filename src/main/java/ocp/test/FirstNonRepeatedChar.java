package ocp.test;

import java.util.HashSet;

public class FirstNonRepeatedChar {
    static void main() {
        var f = new FirstNonRepeatedChar();

        IO.println(f.first("tessttfabcc"));
    }

    public Character first(String s) {
        var set = new HashSet<Character>();
        for(var c: s.toCharArray()) {
            if(set.contains(c))
                return c;
            set.add(c);
        }
        return null;
    }
}
