package com.ashpex;

import java.awt.*;
import java.io.FileNotFoundException;

public class testSlang {
    public static void main(String[] args) throws FileNotFoundException {
        ListSlang slang = new ListSlang();
        slang.showListSlang();
        String definition = slang.searchDefinitionBasedOnSlang("BBC");
        System.out.println(definition);
    }
}
