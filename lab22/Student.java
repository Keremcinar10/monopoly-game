package lab22;

import java.util.ArrayList;

public class Student {
    // class variable
    private static int count = 0;

    // instance variables
    private String name;
    private int ID;
    private String dept;
    private ArrayList<Section> sections;

    // constructor
    public Student(String aName, String aDept)
    {
        this.name = aName;
        this.ID = ++Student.count;
        this.dept = aDept;
        this.sections = new ArrayList<Section>();
    }
    public Student(String aName)
    {
        this(aName, null);
    }

    // getter methods
    public String getName()
    {
        return this.name;
    }
    public int getID()
    {
        return this.ID;
    }
    public String getDept()
    {
        return this.dept;
    }
    
    // other methods
    public void addToSection(Section aSection)
    {
        this.sections.add(aSection);
    }
    public boolean removeFromSection(Section aSection)
    {
        return this.sections.remove(aSection);
    }
}
