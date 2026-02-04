package lab25;

public class MultiShape2D extends GeometricShape2D{
    
    private Object[] shapes;

    public MultiShape2D()
    {
        this.shapes= new Object[0];
    }
    public Object[] getShapes()
    {
        return this.shapes;
    }

    public void addShape(GeometricShape2D addedShape)
    {
        Object[] newShapes= new Object[shapes.length+1];
        for(int i=0; i<shapes.length; i++)
        {
            newShapes[i]= shapes[i];
        }
        newShapes[shapes.length]= addedShape;
        shapes= newShapes;
    }
    public float calculateArea()
    {
        float total=0;
        for ( Object nextShape:  shapes)
        {
            GeometricShape2D shapeToCalculate=(GeometricShape2D)nextShape;
            total+=shapeToCalculate.calculateArea();
        }
        return total;
    }
    public void printInfo()
    {
        System.out.println("-Multishape, area "+this.calculateArea()  );
    }

    public void printInfo2()
    {
        for(Object nextShape : shapes)
        {
            GeometricShape2D shapeToCalculate=(GeometricShape2D)nextShape;
            if(shapeToCalculate instanceof MultiShape2D)
            {
                MultiShape2D a= (MultiShape2D) shapeToCalculate;
                System.out.print("    "); a.printInfo();
                System.out.print("    "); a.printInfo2();
            }
            else
            {
                System.out.print("    -");  shapeToCalculate.printInfo();

            }        
        }
    }

    public void mergeShapes()
    {
        if(shapes.length==0)return;
        float total =0;
        Object[] newShapes=new Object[1];
        for(int i=0;i<shapes.length; i++)
        {
            GeometricShape2D a= (GeometricShape2D)shapes[i];
            total+=a.calculateArea();
        }
      float sideOfSquare = (float)Math.round(Math.sqrt(total));
      Square formedSquare = new Square(sideOfSquare);
      newShapes[0]= formedSquare;
      shapes= newShapes;
    }
    public String toString()
    {
        return "Multishape2D";
    }
    public void editShape()
    {
        return;
    }
}
