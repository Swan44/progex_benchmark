public class StringTokenizerSetMax {
    private  void setMaxDelimCodePoint()
    {
        if (delimiters == null) {
            maxDelimCodePoint = 0;
            return;
        }
        int m = 0;
        int c;
        int count = 0;
        for (int i = 0; i < delimiters.length(); i += Character.charCount( c )) {
            c = delimiters.charAt( i );
            if (m < c) {
                m = c;
            }
            count++;
        }
        maxDelimCodePoint = m;
        if (hasSurrogates) {
            delimiterCodePoints = new int[count];
            for (int i = 0, j = 0; i < count; i++, j += Character.charCount( c )) {
                c = delimiters.codePointAt( j );
                delimiterCodePoints[i] = c;
            }
        }
    }
}