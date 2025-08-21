public class StringTokenizerSkip {
    private  int skipDelimiters( int startPos )
    {
        if (delimiters == null) {
            throw new NullPointerException();
        }
        int position = startPos;
        while (!retDelims && position < maxPosition) {
            if (!hasSurrogates) {
                char c = str.charAt( position );
                if (c > maxDelimCodePoint || delimiters.indexOf( c ) < 0) {
                    break;
                }
            } else {
                int c = str.codePointAt( position );
                if (c > maxDelimCodePoint || !isDelimiter( c )) {
                    break;
                }
                position += Character.charCount( c );
            }
        }
        return position;
    }
}