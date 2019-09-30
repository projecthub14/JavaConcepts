package com.example.concurrency.challenge;

public class Challenge9 {
    public static void main(String[] args) {
        final NewTutor tutor = new NewTutor();
        final NewStudent student = new NewStudent(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {

        //acquired tutor lock
        synchronized (this) {
            System.out.println("Tutor has arrived");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //acquires student lock
            synchronized (student) {
//                try {
//                    // wait for student to arrive
//                    //it releases tutor lock
//
//
//
//                } catch (InterruptedException e) {
//
//                }
                student.startStudy();
                student.notifyAll();
                System.out.println("Tutor is studying with student");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class NewStudent {

    private NewTutor tutor;

    NewStudent(NewTutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public void handInAssignment() {
        //then thread2 get tutor lock
        synchronized (tutor) {
            tutor.getProgressReport();

            //thread2 is blocked as thread1 has student lock
            synchronized (this) {

                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}
