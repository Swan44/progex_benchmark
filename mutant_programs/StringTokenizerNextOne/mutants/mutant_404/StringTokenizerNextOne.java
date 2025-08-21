public class StringTokenizerNextOne {
    public  String nextToken( String delim )
    {
        delimiters = delim;
        delimsChanged = true;
        return nextToken();
    }
}