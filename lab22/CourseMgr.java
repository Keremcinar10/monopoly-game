package lab22;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseMgr {
    public static void printMenu()
    {
        System.out.println("1- Add a course");
        System.out.println("2- Add a section to a course");
        System.out.println("3- Add a student to a section");
        System.out.println("4- Drop a student from a section");
        System.out.println("5- List students in a section");
        System.out.println("6- Add an instructor to a section");
        System.out.println("7- Exit");
    }
    public static int readAChoice()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Choice: ");
        return sc.nextInt();
    }
    public static void printCourses(ArrayList<Course> courses)
    {
        System.out.println("---Courses---");
        for (Course aCourse : courses)
        {
            System.out.println(aCourse.toString());
        }
        System.out.println("---------------------------------------------");
    }
    public static String readAString(String context)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter " + context + ": ");
        return sc.nextLine();
    }
    public static int readAnInt(String context)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter " + context + ": ");
        int no = sc.nextInt(); sc.nextLine();
        return no;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<Course>();
        int choice;

        do
        {
            printCourses(courses);
            printMenu();
            choice = readAChoice();
            switch (choice)
            {
                case 1:
                    String dept = readAString("course dept");
                    String name = readAString("course name");;
                    int code = readAnInt("course code");
                    Course newCourse = new Course(name, dept, code);
                    courses.add(newCourse);
                    break;
                case 2:
                    code = readAnInt("course code");
                    for (Course aCourse : courses)
                    {
                        if (aCourse.getCode() == code)
                        {
                            aCourse.addSection(null);
                        }
                    }
                    break;
                case 3:
                    name = readAString("student name");
                    Student newStudent = new Student(name);
                    code = readAnInt("course code");
                    for (Course aCourse : courses)
                    {
                        if (aCourse.getCode() == code)
                        {
                            int no = readAnInt("section no");
                            Section aSection = aCourse.getSection(no);
                            aSection.addStudent(newStudent);
                        }
                    }
                    break;
                case 4:
                    name = readAString("course name");
                    for (Course aCourse : courses)
                    {
                        if (aCourse.getName().equals(name))
                        {
                            int no = readAnInt("section no");
                            Section aSection = aCourse.getSection(no);
                            int ID = readAnInt("student ID");
                            Student studentToDrop = aSection.getStudent(ID);
                            aSection.dropStudent(studentToDrop);
                        }
                    }
                    break;
                case 5:
                    name = readAString("course name");
                    for (Course aCourse : courses)
                    {
                        if (aCourse.getName().equals(name))
                        {
                            int no = readAnInt("section no");
                            Section aSection = aCourse.getSection(no);
                            System.out.println(aSection.toString());
                        }
                    }
                break;
                case 6:
                    name = readAString("instructor name");
                    Instructor newInstructor = new Instructor(name);
                    name = readAString("course name");
                    for (Course aCourse : courses)
                    {
                        if (aCourse.getName().equals(name))
                        {
                            int no = readAnInt("section no");
                            Section aSection = aCourse.getSection(no);
                            aSection.changeInstructor(newInstructor);
                        }
                    }
                break;
            }
        } while (choice != 7);
    }
}
