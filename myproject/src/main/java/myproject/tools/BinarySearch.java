package myproject.tools;
/**
 * 二分法查找
 * @author user
 *
 */
public class BinarySearch {
	/**
	 * 循环二分法查找
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int binarySearch(int[] arr,int key){
		int low=0;
		int high=arr.length-1;
		int middle =0;
		if(key>arr[high]||key<arr[low]||low>high){
			return -1;
		}
		while(low<=high){
			middle=(low+high)/2;
			if(key<arr[middle]){
				high=middle-1;
			}else if(key>arr[middle]){
				low=middle+1;
			}else{
				return middle;
			}
		}
		return -1;
 	}
	/**
	 * 递归二分法查询
	 * @param arr
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
	public static int recursionBinarySearch(int[] arr,int key,int low,int high){
		if(key>arr[high]||key<arr[low]||low>high){
			return -1;
		}
		int middle=(low+high)/2;//中间位置
		System.out.println("middle-"+middle);
 		if(key<arr[middle]){
 			return recursionBinarySearch(arr, key, low, middle-1);
		}else if(key>arr[middle]){
			return recursionBinarySearch(arr, key, middle+1,high );
 		}else{
 			return middle;
 		}
	}
}
