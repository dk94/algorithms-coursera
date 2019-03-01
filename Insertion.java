
public class Insertion {
    public static void sort(Comparable [] a) {
        int N = a.length;
        
        for(int i=0; i<N; i++) {
            for(int j=i; j>0; j--) {
                if(less(a[j], a[j-1])) exch(a, j, j-1);
                else break;
            }
        }
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) == -1;
    }
    
    public static void exch(Comparable [] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void main(String[] args) {
        Integer [] arr = new Integer[] {4,3,2,90,83,12,76,67,3,4,51,1,0};
        
        Insertion.sort(arr);
        
        for(int i=0; i<arr.length; i++) {
            StdOut.println(arr[i]);
        }

    }
}
