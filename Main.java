package com.cazakh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class FenwickTree {

    long[] array;

    public FenwickTree(long size) {
        array = new long[(int)size + 1];
    }

    public long rsq(long ind) {
        assert ind > 0;
        long sum = 0;
        while (ind > 0) {
            sum += array[(int)ind];
            ind -= ind & (-ind);
        }

        return sum;
    }


    public long rsq(long a, long b) {
        assert b >= a && a > 0 && b > 0;

        return rsq(b) - rsq(a - 1);
    }


    public void update(long ind, long value) {
        assert ind > 0;
        while (ind < array.length) {
            array[(int)ind] += value;

            ind += ind & (-ind);
        }
    }

    public long size() {
        return array.length - 1;
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        //System.out.prlongln(first);
        FenwickTree ft = null;

        String cmd = "cmp";
        while (in.hasNextLine()) {
            try{
            String[] line = in.nextLine().split(" ");

            if (line[0].equals("exit")) break;

            long arg1 = 0, arg2 = 0;

            if (line.length > 1) {
                arg1 = Long.parseLong(line[1]);
            }
            if (line.length > 2) {
                arg2 = Long.parseLong(line[2]);
            }

            //if ((!line[0].equals("set") && !line[0].equals("init")) && ft == null) {
            //    System.out.prlongln("hui not init");
            //    continue;
            //}
            if(ft == null){
                ft = new FenwickTree(line.length);
                //System.out.prlongln("We gonna set this");
                for (long i = 1; i < line.length + 1; i++) {
                    //System.out.prlongln("Stuck on i?" + i);
                    ft.update(i, Long.parseLong(line[(int)i - 1]));
                    //System.out.prlongln("Done!");
                }

            }
            if (line[0].equals("init")) {
                ft = new FenwickTree(arg1);
                for (long i = 1; i <= ft.size(); i++) {
                    System.out.println(ft.rsq(i, i) + " ");
                }
                System.out.println();
            }


            else if (line[0].equals("set")) {
                ft.update(arg1, arg2 - ft.rsq(arg1,arg1));
                //for (long i = 1; i <= ft.size(); i++) {
                    //System.out.prlong(ft.rsq(i, i) + " ");
                //}
                //System.out.prlongln();
            }
            else if (line[0].equals("sum")) {
                System.out.println("" + ft.rsq(arg1, arg2));
            }


        }catch (Exception e){
                return;
            } }
    }


}
