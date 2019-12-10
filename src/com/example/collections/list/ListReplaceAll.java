package com.example.collections.list;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class ListReplaceAll {

    public static void main(String[] args) {

        String str = "sam abbdd  SAM dd sam   jj   sam  be";
        List<String> strList = Arrays.asList(str.split("\\s+"));
        strList.stream().
                map(str1 -> str1.toLowerCase().replaceAll("sam","walmart")).forEach(System.out::println);
        System.out.println();
        strList.forEach(System.out::println);
        System.out.println();
        strList.replaceAll(s -> replaceStr(s));
        strList.forEach(System.out::println);
    }

    private static String replaceStr(String str){
        String temp = str.toLowerCase();
        if(temp.equals("sam"))
        {
            temp = temp.replaceAll(temp,"walmart");
            return temp;
        }
        return str;

    }
}
