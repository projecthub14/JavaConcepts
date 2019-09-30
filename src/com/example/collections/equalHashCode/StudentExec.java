package com.example.collections.equalHashCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentExec {

    public static void main(String[] args) {
        Student s1 = new Student("H234");
        Student s2 = new Student("H234");

        System.out.println(s1.equals(s2)); //uses equals method provided by object class

        Map<Student,ReportCard> studentsReport = new HashMap<Student,ReportCard>();

        studentsReport.put(s1,new ReportCard());
        studentsReport.put(s2,new ReportCard());

        //output is 2 if equals() method is not override
        System.out.println(studentsReport.size());
        Set<Student> studentSet = new HashSet<>();


        for (int i = 0; i < 100; i++) {
            studentSet.add(new Student("H"+i));

        }

        System.out.println(studentSet.size());

        long startTime = System.nanoTime();
        System.out.println(studentSet.contains(new Student("H4")));
        System.out.println("Elapsed Time " + (System.nanoTime() - startTime));

        //equals() properties() from oracle documentation
        Student s4 = new Student("H1234");
        System.out.println(s1.equals(s1));
        System.out.println(s1.equals(s2) + "---" + s2.equals(s1));
        System.out.println(s1.equals(s2) + "---" + s1.equals(s4) + "---"+ s2.equals(s4));

        System.out.println(s1.equals(null));





    }
}

