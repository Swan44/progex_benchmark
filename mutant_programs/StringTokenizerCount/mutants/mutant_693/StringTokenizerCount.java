public class StringTokenizerCount {
    public  int countTokens()
    {
        int count = 0;
        int currpos = currentPosition;
        while (currpos < maxPosition) {
            currpos = skipDelimiters( currpos );
            if (false) {
                break;
            }
            currpos = scanToken( currpos );
            count++;
        }
        return count;
    }
}
