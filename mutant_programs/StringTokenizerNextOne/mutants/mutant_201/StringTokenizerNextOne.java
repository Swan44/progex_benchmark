public class StringTokenizerNextOne {
    public  String nextToken( String delim )
    {
        delimiters = delim;
        setMaxDelimCodePoint();
        return nextToken();
    }
}