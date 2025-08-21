public class StringTokenizerCount {
    public  int countTokens()
    {
        int count = 0;
        int currpos = currentPosition;
        while (currpos < maxPosition) {
            currpos = skipDelimiters( currpos );
            currpos = scanToken( currpos );
            count++;
        }
        return count;
    }
}