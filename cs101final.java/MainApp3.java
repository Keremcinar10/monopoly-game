import java.util.*;

class Student {
    private String name;
    private int id;
    private ArrayList<Course> enrolledCourses;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void enroll(Course course) {
        enrolledCourses.add(course);
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

class Instructor {
    private String name;
    private int id;
    private ArrayList<Course> courses;

    public Instructor(String name, int id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String toString() {
        return name + " (Instructor ID: " + id + ")";
    }
}

class Course {
    private String code;
    private String name;
    private Instructor instructor;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<Double> grades; // same index as enrolledStudents

    public Course(String code, String name, Instructor instructor) {
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
        this.grades = new ArrayList<>();
        instructor.addCourse(this);
    }

    public void enrollStudent(Student s) {
        enrolledStudents.add(s);
        grades.add(null); // notu henüz girilmedi
        s.enroll(this);
    }

    public void assignGrade(Student s, double grade) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).getId() == s.getId()) {
                grades.set(i, grade);
                return;
            }
        }
    }

    public Double getGradeOf(Student s) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).getId() == s.getId()) {
                return grades.get(i);
            }
        }
        return null;
    }

    public double getAverageGrade() {
        double total = 0;
        int count = 0;
        for (Double g : grades) {
            if (g != null) {
                total += g;
                count++;
            }
        }
        return count == 0 ? 0 : total / count;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public String toString() {
        return code + ": " + name + " (Instructor: " + instructor.getName() + ")";
    }
}

class University {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;

    public University() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public void addInstructor(Instructor i) {
        instructors.add(i);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }
}

public class MainApp3 {
    public static void main(String[] args) {
        University uni = new University();

        Instructor i1 = new Instructor("Dr. Yılmaz", 100);
        Instructor i2 = new Instructor("Dr. Aydın", 101);
        uni.addInstructor(i1);
        uni.addInstructor(i2);

        Course c1 = new Course("CS101", "Intro to Programming", i1);
        Course c2 = new Course("MATH201", "Calculus II", i2);
        uni.addCourse(c1);
        uni.addCourse(c2);

        Student s1 = new Student("Elif", 1);
        Student s2 = new Student("Berk", 2);
        Student s3 = new Student("Can", 3);
        uni.addStudent(s1);
        uni.addStudent(s2);
        uni.addStudent(s3);

        c1.enrollStudent(s1);
        c1.enrollStudent(s2);
        c2.enrollStudent(s1);
        c2.enrollStudent(s3);

        c1.assignGrade(s1, 90);
        c1.assignGrade(s2, 75);
        c2.assignGrade(s1, 85);
        c2.assignGrade(s3, 70);

        // TODO #1: Bir dersin en yüksek not alan öğrencisini yazdır
        printTopStudentInCourse(uni, "CS101");

        // TODO #2: Bir öğrencinin aldığı tüm dersleri ve notlarını listele
        printStudentTranscript(uni, 1); // Elif

        // TODO #3: Tüm öğrenciler arasında en yüksek ortalamalıyı yazdır
        printBestStudent(uni);

        // TODO #4: Bir hocanın verdiği tüm derslerdeki tüm notların ortalamasını yazdır
        printInstructorAverageGrade(uni, "Dr. Aydın");
    }

    public static void printTopStudentInCourse(University uni, String courseCode) {
        // TODO
        Course ourCourse = null;
        for(Course c : uni.getCourses() ) {
            if(c.getCode().equals(courseCode)) {
                ourCourse = c;
            }
        }
        if(ourCourse== null ) {
            System.out.println("There is no course with given id!");
            return ;
        }
        Student best = null; 
        double maxGrade = -1;
        for(int i=0 ; i< ourCourse.getEnrolledStudents().size(); i++ ) {
            Student current = ourCourse.getEnrolledStudents().get(i);
            if(ourCourse.getGradeOf(current)!= null && ourCourse.getGradeOf(current)> maxGrade) {
                maxGrade = ourCourse.getGradeOf(current);
                best = current;
            }
        }
        if (best == null) {
            System.out.println("No student has a grade yet.");
        } 
        else {
            System.out.println("The best student is " + best);
        }
    }

    public static void printStudentTranscript(University uni, int studentId) {
        // TODO
        Student ourStudent = null;
        for(Student c : uni.getStudents() ) {
            if(c.getId()== studentId) {
                ourStudent = c;
            }
        }
        if(ourStudent== null ) {
            System.out.println("There is no student with given id!");
            return ;
        }
        for(Course s : ourStudent.getEnrolledCourses()) {
            System.out.println("Course: "+ s + "--> Grade: "+ s.getGradeOf(ourStudent));

        }
        
    }

    public static void printBestStudent(University uni) {
        // TODO
        ArrayList<Double> averages= new ArrayList<Double>();
        int bestIndex = -1;
        double bestAvg = 0;
        for(int i=0 ; i<uni.getStudents().size();i++) {
            double total =0;
            int count =0;
            Student s = uni.getStudents().get(i);
            for(int j=0; j<s.getEnrolledCourses().size();j++) {
                if(s.getEnrolledCourses().get(j).getGradeOf(s)!= null) {
                    total +=s.getEnrolledCourses().get(j).getGradeOf(s);
                    count++;
                }
            }
            if(count != 0) {
                double avg = total / s.getEnrolledCourses().size();
                averages.add(avg);
                if(avg > bestAvg) {
                    bestAvg= avg;
                    bestIndex= i;
                }
            }
        }
        if (bestIndex == -1) {
            System.out.println("No student has any grades.");
        } 
        else {
            System.out.printf("The best student is %s with GPA %.2f\n", uni.getStudents().get(bestIndex).getName(), bestAvg);
        }
    }

    public static void printInstructorAverageGrade(University uni, String instructorName) {
        // TODO

        double total =0;
        int count =0;
        for(Course other : uni.getCourses()) {
            if(other.getInstructor().getName().equals(instructorName)) {
                total += other.getAverageGrade();
                count ++;
            }
        }
        if (count == 0) {
            System.out.println("This instructor doesn't teach any courses.");
        } 
        else {
            double avg = total / count;
            System.out.printf("The average grade for instructor %s is %.2f\n", instructorName, avg);
        }
    }
}