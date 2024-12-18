package com.example.testcontainer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class AventCode3 {

    @Test
    void resolve() throws IOException {
        Path path = Path.of("C:\\projects\\learn\\spring\\TestContainer\\src\\test\\resources\\input3b.txt");
        List<String> lines =   Files.readAllLines(path);

        String s = lines.stream().collect(Collectors.joining());

        Pattern p = Pattern.compile("mul[(]\\d{1,3}[,]\\d{1,3}[)]");
        Pattern p2 = Pattern.compile("(mul[(]\\d{1,3}[,]\\d{1,3}[)])|(do[()]|(don[']t[()]))");
        final int[] sum = {0};
        AtomicBoolean isOK = new AtomicBoolean(true);
        p2.matcher(s).results().forEach(r -> {
            System.out.println(""+ r.group().toString());
            String o = r.group().toString();
            if (o.startsWith("don't")) {
                isOK.set(false);
            } else if (o.startsWith("do(")) {
                isOK.set(true);
            }
            if (isOK.get() && o.startsWith("mul")) {
                String[] c = o.replace("mul(", "").replace(")", "").split(",");
                int a = Integer.valueOf(c[0]);
                int b = Integer.valueOf(c[1]);
                sum[0] = sum[0] + (a * b);
            }
        });
        System.out.println("sum = " + sum[0]);
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
