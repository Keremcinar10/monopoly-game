package lab22;

import java.util.ArrayList;

public class Course {
    // instance variables
    private String name;
    private String dept;
    private int code;
    private ArrayList<Section> sections;

    // constructor
    public Course(String aName, String aDept, int aCode)
    {
        this.name = aName;
        this.dept = aDept;
        this.code = aCode;
        this.sections = new ArrayList<Section>();
    }

    // getter methods
    public String getName()
    {
        return this.name;
    }
    public String getDept()
    {
        return this.dept;
    }
    public int getCode()
    {
        return this.code;
    }
    public int noOfSections()
    {
        return this.sections.size();
    }
    public int getNoOfStudents()
    {
        int sum = 0;
        for (Section aSection : this.sections)
        {
            sum += aSection.getNoOfStudents();
        }
        return sum;
    }
    public Section getSection(int no)
    {
        if (no >= 1 && no <= this.sections.size())
        {
            return this.sections.get(no - 1);
        }
        return null;
    }
    public int getSectionNo(Section section)
    {
        for (int i = 0; i < this.sections.size(); i++)
        {
            if (sections.get(i) == section)
            {
                return i + 1;
            }
        }
        return -1;
    }
    public String toString()
    {
        return this.dept + " " + this.code + "-" + this.name + " (" +
            this.noOfSections() + " sections) with " + this.getNoOfStudents() +
            " students";
    }

    // other methods
    public void setName(String aName)
    {
        this.name = aName;
    }
    public Section addSection(Instructor anInstructor)
    {
        Section newSection = new Section(this);
        newSection.changeInstructor(anInstructor);
        this.sections.add(newSection);
        return newSection;
    }
    public boolean removeSection(Section aSection)
    {
        return this.sections.remove(aSection);
    }
}
