package lab22;

import java.util.ArrayList;

public class Section {
    // class variable
    public static final int MAX_STUDENT_COUNT = 50;

    // instance variables
    private Course course;
    private ArrayList<Student> students;
    private Instructor instructor;

    // constructor
    public Section(Course aCourse)
    {
        this.course = aCourse;
        this.students = new ArrayList<Student>();
        this.instructor = null;
    }

    // getter methods
    public Course getCourse()
    {
        return this.course;
    }
    public Student getStudent(int ID)
    {
        for (Student aStudent : this.students)
        {
            if (aStudent.getID() == ID)
            {
                return aStudent;
            }
        }
        return null;
    }
    public Instructor getInstructor()
    {
        return this.instructor;
    }
    public int getNoOfStudents()
    {
        return this.students.size();
    }
    public String toString()
    {
        String result = "Section " + this.course.getSectionNo(this) + 
            " taught by " + this.instructor.getName() + " has " +
            this.students.size() + " students as follows:\n";
        for (Student aStudent : this.students)
        {
            result += aStudent.getName() + "(" + aStudent.getID() + ")\n";
        }
        return result;
    }

    // other methods
    public void changeInstructor(Instructor anInstructor)
    {
        if (this.instructor != null)
        {
            this.instructor.removeSection(this);
        }
        this.instructor = anInstructor;
    }
    public boolean addStudent(Student aStudent)
    {
        if (this.students.contains(aStudent))
        {
            System.out.println("already taking it!");
            return false;
        }
        if (this.students.size() >= Section.MAX_STUDENT_COUNT)
        {
            System.out.println("no room left in section!");
            return false;
        }
        this.students.add(aStudent);
        return true;
    }
    public boolean dropStudent(Student aStudent)
    {
        return this.students.remove(aStudent);
    }
}