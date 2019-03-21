package myproject.tools;
/**
 * 冒泡算法
 * @author user
 *
 */
public class BubbleSort {
	/**
	 * 降序
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSortDesc(int[] arr){
		int temp=0;
		for(int i=0;i<arr.length-1;i++){//轮数
			for(int j=0;j<arr.length-1-i;j++){//次数
 				if(arr[j]<arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		return arr;
 	}
	/**
	 * 升序
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSortAsc(int[] arr){
		int temp=0;
		for(int i=0;i<arr.length-1;i++){//轮数
			for(int j=0;j<arr.length-i-1;j++){//次数
				if(arr[j]>arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		return arr;
 	}
	
	
	/**
	 * 选择排序
	 * @param arr
	 * @return
	 */
	public static int[] selectSortDesc(int[] arr){
 		int temp=0;
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
 				if(arr[i]<arr[j]){
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
 		return arr;
 	}
}
