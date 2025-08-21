public class QuickSortSort {
    public  void sort( int[] data )
    {
        if (data.length < 2) {
            return;
        }
        int max = 0;
        swap( data, data.length - 1, max );
        quicksort( data, 0, data.length - 2 );
    }
}