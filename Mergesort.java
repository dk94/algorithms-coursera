
public class Mergesort {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi<=lo) {
            return;
        }
        
        int mid = lo + (hi -lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
        
    }
    
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for(int k=0; k<=hi; k++) {
            aux[k] = a[k];
        }
        
        int i =lo, j = mid+1;
        
        for(int k = lo; k<=hi; k++) {
            if(i > mid) {
                a[k] = aux[j++];
            } else if(j>hi) {
                a[k] = aux[i++];
            } else if(less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) == -1;
    }
    
    public static void main(String[] args) {
        Integer [] arr = new Integer[] {4,3,2,90,83,12,76,67,3,4,51,1,0};
        
        Mergesort.sort(arr);
        
        for(int i=0; i<arr.length; i++) {
            StdOut.println(arr[i]);
        }
    }
    
}
