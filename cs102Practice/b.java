package cs102Practice;

public class b {
    public static void main(String[] args) {
        System.out.println(centeredAverage(new int []{1,2,3,4,100}));
    }
    public static int centeredAverage(int[] nums) {
        int max;
        int min;
        max = nums[0];
        min = nums[0];
        
        for(int i=0;i<nums.length;i++)
        {
          if(max<nums[i])
          {
            max = nums[i];
          }
          if(min>nums[i])
          {
            min = nums[i];
          }
        }
        int smallCount=0;
        int bigCount=0; 
        int sum=0;
        for(int j=0; j<nums.length;j++)
        {
          if(nums[j]== min )
          {
            smallCount ++;
            if(smallCount!=1)
            {
              sum+=nums[j];
              
            }
          }
          else if (nums[j]== max )
          {
            bigCount ++;
            if(bigCount!=1)
            {
              sum+=nums[j];
              
            }
          }
          else 
          {
            sum+=nums[j];
          }
        }
        return sum/(nums.length-2);
        
      }
}
