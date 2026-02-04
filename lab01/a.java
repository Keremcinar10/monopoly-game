package lab01;

public  class a {
    public static void main(String[] args) {
        
    }
    public int countX(String str) {
  
        if(str.isEmpty()) return 0;
        
        if(str.charAt(str.length()-1)=='x')
        {
          return 1 + countX(str.substring(0,str.length()-1));
        }
        else 
        {
          return countX(str.substring(0,str.length()-1));
        }
      }
}
