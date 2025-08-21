public class StringTokenizerNextOne {
    public  String nextToken( String delim )
    {
        delimsChanged = true;
        setMaxDelimCodePoint();
        return nextToken();
    }
}