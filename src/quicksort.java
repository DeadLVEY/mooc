import java.util.Arrays;

/**
 * 递归的快速排序
 */
public class quicksort {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,1,2,21,23,12,3,4,5,12,35,61};
        new quicksort().quicksort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));

    }

    public void quicksort(int[] array,int left,int right){
        if(left >= right) return;

        int i,j,temp;

        i=left;j=right;temp=array[left];

        while(i!=j){
            while(array[j]>=temp && i<j) j--;
            while(array[i]<=temp && i<j) i++;

            if(i<j){
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }

        array[left] = array[i];
        array[i] = temp;

        quicksort(array,left,i-1);
        quicksort(array,i+1,right);
    }
}
