package com.example.testcontainer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class AventCode2 {

    @Test
    void resolve() throws IOException {
        Path path = Path.of("C:\\projects\\learn\\spring\\TestContainer\\src\\test\\resources\\input2.txt");
        List<String> lines =   Files.readAllLines(path);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        int countSafe = 0;

        for(String s : lines) {
            String[] ss = s.split(" ");
            List<Integer> l = Arrays.stream(ss).map(Integer::valueOf).toList();
            boolean inc = l.get(1) - l.get(0) > 0 ? true: false;
            boolean isOk = true;
            for (int i = 0; i < l.size()-1; i++) {
                int diff = Math.abs(l.get(i) - l.get(i+1));
                if (diff <1 || diff > 3) {
                    isOk = false;
                   break;
                }
                boolean cond = l.get(i+1) - l.get(i) > 0 ? true: false;
                if (cond != inc) {
                    isOk = false;
                    break;
                }
            }
            if (isOk == true){
                countSafe++;
            } else {
                for (int i = 0; i < l.size(); i++) {
                    List<Integer> newList = generateNewList(l, i);
                    boolean isSafe = safe(newList);
                    if(isSafe) {
                        countSafe++;
                        break;
                    }
                }
            }

        }

        System.out.println("countSafe = " + countSafe);
    }

    private boolean safe(List<Integer> l) {
        boolean inc = l.get(1) - l.get(0) > 0 ? true: false;
        boolean isOk = true;
        for (int i = 0; i < l.size()-1; i++) {
            int diff = Math.abs(l.get(i) - l.get(i+1));
            if (diff <1 || diff > 3) {
                isOk = false;
                break;
            }
            boolean cond = l.get(i+1) - l.get(i) > 0 ? true: false;
            if (cond != inc) {
                isOk = false;
                break;
            }
        }
       return isOk;
    }

    private List<Integer> generateNewList(List<Integer> l, int i) {
        List<Integer> gen = new ArrayList<>(l);
        gen.remove(i);
        return gen;
    }

    private int howMany(int a, List<Integer> l2) {
        int count = 0;
        for(int i = 0 ; i < l2.size() ; i++) {
            if(l2.get(i).intValue() ==  a) {
                count++;
            }
        }
        return count;
    }

}
