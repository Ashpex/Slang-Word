package com.ashpex;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlangHashMap {
    private final Map<String, ArrayList<String>> forwardMap = new ConcurrentHashMap<>();
    private final Map<String, ArrayList<String>> reverseMap = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        put(key, value, false);
    }

    public void put(String key, String value, Boolean isDuplicate) {
        ArrayList<String> values = forwardMap.get(key);
        if (values == null) {
            values = new ArrayList<>();
            forwardMap.put(key, values);
        }
        if (isDuplicate) {
            values.add(value);
        } else {
            values.clear();
            values.add(value);
        }
        ArrayList<String> keys = reverseMap.get(value);
        if (keys == null) {
            keys = new ArrayList<>();
            reverseMap.put(value, keys);
        }
        keys.add(key);
    }

    public ArrayList<String> get(String key) {
        return forwardMap.get(key);
    }

    public ArrayList<String> getReverse(String value) {
        return reverseMap.get(value);
    }

    public void remove(String key, String value) {
        ArrayList<String> values = forwardMap.get(key);
        values.remove(value);
        ArrayList<String> keys = reverseMap.get(value);
        keys.remove(key);
    }

    public void remove(String key) {
        ArrayList<String> values = forwardMap.get(key);
        for (String value : values) {
            ArrayList<String> keys = reverseMap.get(value);
            keys.remove(key);
        }
        forwardMap.remove(key);
    }
    public void removeReverse(String value) {
        ArrayList<String> keys = reverseMap.get(value);
        for (String key : keys) {
            ArrayList<String> values = forwardMap.get(key);
            values.remove(value);
        }
        reverseMap.remove(value);
    }

    public void removeAll() {
        forwardMap.clear();
        reverseMap.clear();
    }

    public void removeAll(String key) {
        ArrayList<String> values = forwardMap.get(key);
        for (String value : values) {
            ArrayList<String> keys = reverseMap.get(value);
            keys.remove(key);
        }
        forwardMap.remove(key);
    }

    public void removeAll(String key, String value) {
        ArrayList<String> keys = reverseMap.get(value);
        keys.remove(key);
        ArrayList<String> values = forwardMap.get(key);
        values.remove(value);
    }
    public void removeAllReverse(String value) {
        ArrayList<String> keys = reverseMap.get(value);
        for (String key : keys) {
            ArrayList<String> values = forwardMap.get(key);
            values.remove(value);
        }
        reverseMap.remove(value);
    }

    public void searchByDefinition(String definition) {
        ArrayList<String> values = reverseMap.get(definition);
        if (values != null) {
            for (String value : values) {
                System.out.println(value);
            }
        }
    }

    public void searchBySlang(String slang) {
        ArrayList<String> keys = forwardMap.get(slang);
        if (keys != null) {
            for (String key : keys) {
                System.out.println(key);
            }
        }
    }



}
