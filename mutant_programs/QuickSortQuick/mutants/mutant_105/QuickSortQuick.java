public class QuickSortQuick {
    private  void quicksort( int[] data, int first, int last )
    {
        int lower = first + 1;
        int upper = last;
        swap( data, first, (first + last) / 2 );
        int bound = data[first];
        swap( data, upper, first );
        if (first < upper - 1) {
            quicksort( data, first, upper - 1 );
        }
        if (upper + 1 < last) {
            quicksort( data, upper + 1, last );
        }
    }
}