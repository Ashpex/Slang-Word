package com.ashpex;

public class TestHashMap {
    public static void main(String[] args) {
        SlangHashMap map = new SlangHashMap();

        map.put("hello", "hi my name is [John Cena}");
        map.put("hello", "howdy", true);

//        System.out.println(map.getDefinition("hello"));
//        System.out.println(map.getSlang("hi"));
//        System.out.println(map.getSlang("John"));

        map.put("HE", "hello");
        map.put("HES", "hey");
        map.put("HEY", "hi");
        map.put("HELLO", "hi");

//        System.out.println(map.searchByDefinition("my is howdy"));
        map.searchBySlang("hello");
    }
}

