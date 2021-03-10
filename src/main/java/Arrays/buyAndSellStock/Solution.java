package Arrays.buyAndSellStock;
import org.junit.Assert;

public class Solution {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i = 0; i < prices.length; i++){
            if(prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }

        return maxProfit;
    }

    public static void main(String[] args){
        int[] prices = { 7,1,5,3,6,4 };
        Assert.assertEquals(5,maxProfit(prices));

        prices = new int[] { 7,6,4,3,1};
        Assert.assertEquals(0, maxProfit(prices));
    }
}
