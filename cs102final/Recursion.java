package cs102final;

public class Recursion {
    
    public int countTargetWays(int[] nums, int target) {

    return helper(nums, 0, 0, target); 

    }

    private int helper(int[] nums, int index, int currentSum, int target) {
        
        
        if(index == nums.length) {
            if(currentSum == target) 
                return 1  ;
            else 
                return 0 ;
        }

        int plus = helper(nums, index + 1, currentSum + nums[index], target);
        int minus = helper(nums, index + 1, currentSum - nums[index], target);
            
        return minus + plus;
        
    }

     public static int countEqualSplitWays(int[] nums) {
        // Toplamı hesapla (gerekirse kontrol için kullanabilirsin)
        // helper metodunu başlat
        return helper2(nums, 0, 0, 0);
    }

    private static int helper2(int[] nums, int index, int sum1, int sum2) {
        // BASE CASE:
        // Tüm elemanlar yerleştirildiyse sum1 ve sum2 eşitse 1 döndür, değilse 0 döndür
        if(index == nums.length) {
            if(sum1 == sum2)
                return 1;
            else 
                return 0;
        }

        // RECURSIVE CASE:
        // Şu anki elemanı hem sum1'e hem sum2'ye ekleyerek iki farklı yolu dene

        int count1= helper2(nums, index+1, sum1+nums[index], sum2);
        int count2= helper2(nums, index+1, sum1, sum2+nums[index]);
       
        return count1+ count2;
    }

    public static int countSubsetsWithTarget(int[] nums, int target) {
        return helper3(nums, 0, target, false);
    }

    private static int helper3(int[] nums, int index, int remaining, boolean chosen) {
        // Başarılı bir çözüm bulunduysa:
        if (remaining == 0 && chosen) {
            return 1;
        }

        // Olumsuz durumlar:
        if (remaining < 0 || index == nums.length) {
            return 0;
        }

        // Elemanı al
        int take = helper3(nums, index + 1, remaining - nums[index], true);

        // Elemanı alma
        int skip = helper3(nums, index + 1, remaining, chosen);

        return take + skip;
    }

    public int countSubsets(int[] nums, int target) {

        return helper4(nums, 0, target, false);
        
    }
    public static int helper4(int[]nums, int index, int remaining , boolean chosen){

        if(remaining == 0 && chosen )
            return 1;
        if(remaining < 0 || index == nums.length )
            return 0;

        int count1= helper4(nums, index + 1, remaining - nums[index], true);
        int count2= helper4(nums, index + 1, remaining, chosen);
        
        return count1 + count2;
    }

}
