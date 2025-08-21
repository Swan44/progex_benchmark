public class StringTokenizerNextZero {
    public  String nextToken()
    {
        currentPosition = newPosition >= 0 && !delimsChanged ? newPosition : skipDelimiters( currentPosition );
        delimsChanged = false;
        newPosition = -1;
        if (false) {
            throw new java.util.NoSuchElementException();
        }
        int start = currentPosition;
        currentPosition = scanToken( currentPosition );
        return str.substring( start, currentPosition );
    }
}
