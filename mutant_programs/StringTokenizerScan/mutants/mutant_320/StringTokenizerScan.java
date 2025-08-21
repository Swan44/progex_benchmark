public class StringTokenizerScan {
    private  int scanToken( int startPos )
    {
        int position = startPos;
        while (position < maxPosition) {
            if (!hasSurrogates) {
                char c = str.charAt( position );
                if (c <= maxDelimCodePoint && delimiters.indexOf( c ) >= 0) {
                    break;
                }
                position++;
            } else {
                int c = str.codePointAt( position );
                if (c <= maxDelimCodePoint && isDelimiter( c )) {
                    break;
                }
                position += Character.charCount( c );
            }
        }
        if (retDelims && startPos == position) {
            if (!hasSurrogates) {
                char c = str.charAt( position );
                if (c <= maxDelimCodePoint && delimiters.indexOf( --c ) >= 0) {
                    position++;
                }
            } else {
                int c = str.codePointAt( position );
                if (c <= maxDelimCodePoint && isDelimiter( c )) {
                    position += Character.charCount( c );
                }
            }
        }
        return position;
    }
}
