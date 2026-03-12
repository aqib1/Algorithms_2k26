package com.algorithms.app.io.catoNetworks;

import java.util.ArrayList;
import java.util.List;

public class StrtOk {
    void main() {
        IO.println(tokens("aqib,ali,ahmad"));
    }
    public static List<String> tokens (String str) {
        var response = new ArrayList<String>();
        var curr = "";
        for(char ch: str.toCharArray()) {
            if(ch == ',') {
                response.add(curr);
                curr = "";
            } else {
                curr += String.valueOf(ch);
            }
        }

        if(!curr.isBlank()) response.add(curr);
        return response;
    }
}
