public class StringTokenizerThree {
    public StringTokenizer( String str, String delim, boolean returnDelims )
    {
        currentPosition = 0;
        newPosition = -1;
        this.str = str;
        maxPosition = str.length();
        delimiters = delim;
        retDelims = returnDelims;
        setMaxDelimCodePoint();
    }
}