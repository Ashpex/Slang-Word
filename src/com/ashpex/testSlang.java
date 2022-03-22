package com.ashpex;

import java.awt.*;
import java.io.FileNotFoundException;

public class testSlang {
    public static void main(String[] args) throws FileNotFoundException {
        ListSlang slang = new ListSlang();
        String[][] result = slang.searchDefinitionBasedOnSlang3(" ");
        if(!checkNull(result)) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i][0] + " " + result[i][1] + " " + result[i][2]);
            }
        }else
            System.out.println("No result found");
    }
    public static boolean checkNull(String[][] result) {
        for(int i = 0; i < result.length; i++) {
            if(result[i][0] == null)
                return true;
        }
        return false;
    }
}
