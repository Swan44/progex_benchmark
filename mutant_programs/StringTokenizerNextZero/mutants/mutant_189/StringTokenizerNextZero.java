public class StringTokenizerNextZero {
    public  String nextToken()
    {
        delimsChanged = false;
        newPosition = -1;
        if (currentPosition >= maxPosition) {
            throw new java.util.NoSuchElementException();
        }
        int start = currentPosition;
        currentPosition = scanToken( currentPosition );
        return str.substring( start, currentPosition );
    }
}