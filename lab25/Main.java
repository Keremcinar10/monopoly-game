package lab25;

import java.util.Scanner;

public class Main {

      private static GeometricShape2D[] shapes2D= new GeometricShape2D[0];
      private static GeometricShape3D[] shapes3D= new GeometricShape3D[0];
    
   public void play() {
   Scanner sc= new Scanner (System.in);
      
      int chosenOption;
         do
         {
            displayMenu();
            System.out.println("Choose the option you want to operate:");
            chosenOption= sc.nextInt();
            switch(chosenOption)
            {
               case 1:
               CreateandStoreaNewShape();
               break;
               case 2:
               int count =0;
               for(int i=0;i<shapes2D.length;i++)
               {
                  Object anObj= shapes2D[i];
                  if(anObj instanceof MultiShape2D)
                  {
                     count++;
                  }
               }
               if(shapes2D.length>=2 && count>0)
               {
                  AddanExistingShapetoaMultishape(count);
               }
               else
               {
                  System.out.println("you cant operate this option right now!");
               }
               
               break;

               case 3:
               ListAllShapes();
               break;

               case 4:
               MergeMultishapes();
               break;

               case 5:
               EditShape();
               break;
            }
         }while(chosenOption!=6);

   }

   public static void EditShape()
   {
      Scanner sc= new Scanner(System.in);
      Object allShapes[]= new Object[shapes2D.length+ shapes3D.length];
      int count=0;
      for (int i=0;i<shapes2D.length;i++)
      {
         if(shapes2D[i] instanceof MultiShape2D) continue;
         GeometricShape2D a= shapes2D[i];
         allShapes[count]=a;
         count ++;
      }
      for (int j=0;j<shapes3D.length;j++)
      {
         GeometricShape3D a= shapes3D[j];
         allShapes[count]=a;
         count ++;
      }
      for (int a=0;a<count;a++)
      {
         System.out.print("["+a+"]- ");
         System.out.println(allShapes[a].toString());
      }
      System.out.print("Select the shape to edit: ");
      int selectedInt= sc.nextInt();
      GeometricShape2D a= (GeometricShape2D)allShapes[selectedInt];
      a.editShape();


   }

   public static void MergeMultishapes()
   {
      for(int i=0;i<shapes2D.length;i++)
      {
         if(shapes2D[i] instanceof MultiShape2D)
         {
            MultiShape2D a= (MultiShape2D)shapes2D[i];
            a.mergeShapes();  
         }
      }
   }

   public static void ListAllShapes()
   {
         Scanner sc= new Scanner (System.in);
         System.out.println("2D shapes:\n");
         int i;
      for ( i=0;i<shapes2D.length;i++)
      {
          System.out.print("["+i+"]- "); shapes2D[i].printInfo();
          
      }
      System.out.println("3D shapes:\n");
      for (int j=0;j<shapes3D.length;j++)
      {
         System.out.print("["+(j+i)+"]- "); shapes3D[j].printInfo();
      }
      System.out.println("Do you want details for a specific shape? \nEnter shape index or -1 to return:");
      int index= sc.nextInt();
      if(index==-1)return;
      else
      {
         if(index>=shapes2D.length && index<shapes2D.length+shapes3D.length)
         {
            GeometricShape3D a= shapes3D[index-shapes2D.length];
            System.out.println(" area of this "+a.toString()+" is "+a.calculateArea());
            System.out.println(" volume of this "+a.toString()+" is "+a.calculateVolume());
         }
         else
         {
            GeometricShape2D b=shapes2D[index];
            if(b instanceof MultiShape2D)
            {
               MultiShape2D bb= (MultiShape2D)b;
               bb.printInfo();
               bb.printInfo2();
            }
            else
            {
               System.out.println(" area of this "+b.toString()+" is "+b.calculateArea());
            }
         }
         
      }

   }

   public static void AddanExistingShapetoaMultishape(int count)
   {
      Scanner sc= new Scanner (System.in);
      MultiShape2D[] a= new MultiShape2D[count];
      int count1=0;
      for(int i=0;i<shapes2D.length;i++)
         {
            Object anObj= shapes2D[i];
            if(anObj instanceof MultiShape2D)
            {
              a[count1]=(MultiShape2D)anObj;
              count1++;
            }
         }
         for(int j=0;j<a.length;j++)
         {
            System.out.print(j+1+"- "); a[j].printInfo();
         }
         System.out.print("enter the no of the multishape you want to add the shape to:");
         int no= sc.nextInt(); 
         MultiShape2D selectedMulti=a[no-1];
         GeometricShape2D[] b= new GeometricShape2D[shapes2D.length-1];
         int count2=0;
         for (int k=0;k<shapes2D.length;k++)
         {
            if(!shapes2D[k].equals(selectedMulti))
            {
               Object Obj= shapes2D[k];
               b[count2]= (GeometricShape2D)Obj;
               count2++;
            }
         }
         for(int s=0;s<b.length;s++)
         {
            System.out.print(s+1+"- "); b[s].printInfo();
         }
         System.out.println("Which shape do you want to add: ");
         int number= sc.nextInt(); 
         GeometricShape2D selectedShape= b[number-1];
         selectedMulti.addShape(selectedShape);
         int spot =0;
         for (int l=0;l<shapes2D.length;l++)
         {
            if(shapes2D[l]==selectedShape)
            {
               spot=l;
            }
         }
         for (int h=spot;h<shapes2D.length-1;h++)
         {
            shapes2D[h]=shapes2D[h+1];
         }
         GeometricShape2D[] newArray= new GeometricShape2D[shapes2D.length-1];
         for(int g=0;g<newArray.length;g++)
         {
            newArray[g]=shapes2D[g];
         }
         shapes2D= newArray;


         
   }

   public static void CreateandStoreaNewShape()
   {
      Scanner sc= new Scanner(System.in);
      displayShapes();
               System.out.println("Enter the shape you want to add:");
               int enteredShape= sc.nextInt();
               
               switch(enteredShape)
               {
                  case 1:
                  System.out.print("Enter radius: ");
                  float radius= sc.nextFloat();
                  add2DShape(new Circle(radius)); 
                  break; 

                  case 2:
                  System.out.print( "Enter side: ");
                  float side= sc.nextFloat();
                  add2DShape(new EquilateralTriangle(side));
                  break;

                  case 3:
                  System.out.print("Short radius");
                  float shortrad= sc.nextFloat();
                  System.out.print("Long radius");
                  float longrad= sc.nextFloat();
                  add2DShape(new Ellipse(longrad, shortrad));
                  break;
                  
                  case 4:
                  System.out.print("enter side:");
                  float aSide= sc.nextFloat();
                  add2DShape(new Square(aSide));
                  break;

                  case 5:
                  System.out.print("enter first side: ");
                  float side1= sc.nextFloat();
                  System.out.print("Enter second side: ");
                  float side2=sc.nextFloat();
                  add2DShape(new Rectangle(side1, side2));
                  break;

                  case 7:
                  System.out.println("Enter 3 sides of cuboid");
                  float sidea= sc.nextFloat();
                  float sideb= sc.nextFloat();
                  float sidec= sc.nextFloat();
                  add3DShape(new Cuboid(sidea, sideb, sidec));
                  break;

                  case 8:
                  System.out.println("enter radius: ");
                  float radius2= sc.nextFloat();
                  add3DShape(new Sphere(radius2));
                  break;

                  case 9:
                  System.out.println("enter heigth: ");
                  float height= sc.nextFloat();
                  System.out.println("enter raidus:");
                  float radius3= sc.nextFloat();
                  add3DShape(new Cylinder(height, radius3));
                  break;

                  case 10:
                  System.out.println("enter side:");
                  float side4= sc.nextFloat();
                  add3DShape(new Cube(side4));
                  break;

                  case 11:
                  System.out.println("enter heigth: ");
                  float heigth3= sc.nextFloat();
                  System.out.println("enter side:");
                  float side5 = sc.nextFloat();
                  add3DShape(new Pyramid(side5, heigth3));

                  break;

                  case 6 :
                  MultiShape2D a= new MultiShape2D();
                  add2DShape(a);
                  System.out.println("Dou you want to add new shape to the multishape: ");
                  String answer= sc.next();
                  while(answer.equalsIgnoreCase("yes"))
                  {
                     System.out.println( "enter the shape you want to add to this multishape: ");
                     displayShapes();
                     int enteredNo= sc.nextInt();
                     switch(enteredNo)
                     {
                        case 1:
                        System.out.print("Enter radius: ");
                        float radiusk= sc.nextFloat();
                        a.addShape(new Circle(radiusk)); 
                        break; 
      
                        case 2:
                        System.out.print( "Enter side: ");
                        float sidek= sc.nextFloat();
                        a.addShape(new EquilateralTriangle(sidek));
                        break;
      
                        case 3:
                        System.out.print("Short radius");
                        float shortradk= sc.nextFloat();
                        System.out.print("Long radius");
                        float longradk= sc.nextFloat();
                        a.addShape(new Ellipse(longradk, shortradk));
                        break;
                        
                        case 4:
                        System.out.print("enter side:");
                        float aSidek= sc.nextFloat();
                        a.addShape(new Square(aSidek));
                        break;
      
                        case 5:
                        System.out.print("enter first side: ");
                        float side1k= sc.nextFloat();
                        System.out.print("Enter second side: ");
                        float side2k=sc.nextFloat();
                        a.addShape(new Rectangle(side1k, side2k));
                        break;
                        case 6 :
                        System.out.println("You can do this in the second option");
                        break;
      
                        case 7:
                        System.out.println("Enter 3 sides of cuboid");
                        float sideak= sc.nextFloat();
                        float sidebk= sc.nextFloat();
                        float sideck= sc.nextFloat();
                        a.addShape(new Cuboid(sideak, sidebk, sideck));
                        break;
      
                        case 8:
                        System.out.println("enter radius: ");
                        float radius2k= sc.nextFloat();
                        a.addShape(new Sphere(radius2k));
                        break;
      
                        case 9:
                        System.out.println("enter heigth: ");
                        float heightk= sc.nextFloat();
                        System.out.println("enter raidus:");
                        float radius3k= sc.nextFloat();
                        a.addShape(new Cylinder(heightk, radius3k));
                        break;
      
                        case 10:
                        System.out.println("enter side:");
                        float side4k= sc.nextFloat();
                        a.addShape(new Cube(side4k));
                        break;
      
                        case 11:
                        System.out.println("enter heigth: ");
                        float heigth3k= sc.nextFloat();
                        System.out.println("enter side:");
                        float side5k = sc.nextFloat();
                        a.addShape(new Pyramid(side5k, heigth3k));
      
                        break;
      
                     }
                     System.out.println("Dou you want to add another shape to the multishape: ");
                     answer= sc.next();
                     
                  }
                  break;


               }
   }

   public static void add2DShape(GeometricShape2D newShape)
   {
      GeometricShape2D[] a= new GeometricShape2D[shapes2D.length+1];
      for(int i=0;i<shapes2D.length;i++)
      {
         a[i]= shapes2D[i];
      }
      a[shapes2D.length]=newShape;
      shapes2D= a;
   }

   public static void add3DShape(GeometricShape3D newShape)
   {
      GeometricShape3D[] a= new GeometricShape3D[shapes3D.length+1];
      for(int i=0;i<shapes3D.length;i++)
      {
         a[i]= shapes3D[i];
      }
      a[shapes3D.length]=newShape;
      shapes3D= a;
   }

   public static void displayShapes()
   {
      String [] possibleShapes ={"Circle", "Equilateral Triangle", "Ellipse","Square",
         "Rectangle","MultiShape2D","Cuboid", "Sphere","Cylinder","Cube","Pyramid"};
         System.out.println("2D shapes:\n");
      for (int i=0;i<possibleShapes.length;i++)
      {
          System.out.println(i+1+"- "+ possibleShapes[i]);
          if(i==5)
         {
            System.out.println("3D shapes:\n");
            
         }
      }
   }

   public static void displayMenu()
   {
      System.out.println("1- Create and Store a New Shape: ");
      System.out.println("2- Add an Existing Shape to a Multi-shape: ");
      System.out.println("3- List All Shapes:");
      System.out.println("4- Merge Multi-shapes:");
      System.out.println("5- Edit Shape: ");
   }

   public static void main(String[] args) {
      Main k= new Main();
      k.play();
   }
}
