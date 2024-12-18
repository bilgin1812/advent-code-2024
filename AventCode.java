package com.example.testcontainer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class AventCode {

    @Test
    void resolve() throws IOException {

        ClassLoader cl = getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream("input.txt");
        Path path = Path.of("C:\\projects\\learn\\spring\\TestContainer\\src\\test\\resources\\input.txt");
        List<String> lines =   Files.readAllLines(path);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for(String s : lines) {
            String[] ss = s.split("   ");
            int a = Integer.valueOf(ss[0]);
            int b = Integer.valueOf(ss[1]);
            l1.add(a);
            l2.add(b);
        }


        Collections.sort(l1);
        Collections.sort(l2);
        int sum = 0;
        for(int i =0 ; i < l1.size() ; i++) {
            int nbr = howMany(l1.get(i), l2);
            sum = sum + (l1.get(i)  * nbr);
        }

        System.out.println("sum = " + sum);
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
