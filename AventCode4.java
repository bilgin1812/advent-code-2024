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


class AventCode4 {

    @Test
    void resolve() throws IOException {
        Path path = Path.of("C:\\projects\\learn\\spring\\TestContainer\\src\\test\\resources\\input4a.txt");
        List<String> lines =   Files.readAllLines(path);
        String[][] ss = new String[lines.size()][lines.size()];

        System.out.println( " " +lines.size());
        for(int i = 0; i < lines.size(); i++) {
           String l = lines.get(i);
            System.out.println( "i " +i+ " len:" + l.length());
            for(int k = 0; k < l.length(); k++) {
                Character c = l.charAt(k);
                System.out.println(i + ", " +k);
                ss[i][k] = String.valueOf(c);
            }
        }

        List<String> verticals = new ArrayList<>();
        for(int i = 0; i < ss.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < ss[0].length; k++) {
                sb.append(ss[k][i]);
            }
            verticals.add(sb.toString());
        }

        /*List<String> diagonals = new ArrayList<>();
        for(int i = 0; i < ss.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < ss[0].length; i++) {
                sb.append(ss[k][i]);
            }
            diagonals.add(sb.toString());
        }*/

        Pattern p1 = Pattern.compile("XMAS");
        Pattern p2 = Pattern.compile("SAMX");
        final long[] sum = {0};
        lines.stream().forEach(p-> {
            sum[0] = sum[0]+  p2.matcher(p).results().count();
        });
        verticals.stream().forEach(p-> {
            sum[0] = sum[0]+  p2.matcher(p).results().count();
        });
        lines.stream().forEach(p-> {
            sum[0] = sum[0]+  p1.matcher(p).results().count();
        });
        verticals.stream().forEach(p-> {
            sum[0] = sum[0]+  p1.matcher(p).results().count();
        });



        /*for (int i = 0; i < ops.length; i++) {
            String o = ops[i];
           String[] c=  o.replace("mul(", "").replace(")", "").split(",");
           int a = Integer.valueOf(c[0]);
           int b = Integer.valueOf(c[1]);
           sum = sum + (a*b);
        }*/
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
