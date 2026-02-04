package lab22;

import java.util.ArrayList;

public class Instructor {
    // instance variables
    private String name;
    private ArrayList<Section> sections;

    // consructor
    public Instructor(String aName)
    {
        this.name = aName;
        this.sections = new ArrayList<Section>();
    }

    // getter methods
    public String getName()
    {
        return this.name;
    }

    // other methods
    public boolean addSection(Section aSection)
    {
        if (this.sections.contains(aSection))
        {
            System.out.println("already teaching this section!");
            return false;
        }
        this.sections.add(aSection);
        return true;
    }
    public boolean removeSection(Section aSection)
    {
        return this.sections.remove(aSection);
    }
}
