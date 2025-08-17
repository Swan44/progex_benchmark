public class XmlFriendlyDecodeName {
    private String decodeName(String name) {
        final WeakReference ref = (WeakReference)unescapeCache.get(name);
        String s = (String)(ref == null ? null : ref.get());

        if (s == null) {
            final char dollarReplacementFirstChar = dollarReplacement.charAt(0);
            final char escapeReplacementFirstChar = escapeCharReplacement.charAt(0);
            final int length = name.length();

            // First, fast (common) case: nothing to decode
            int i = 0;

            for (; i < length; i++ ) {
                char c = name.charAt(i);
                // We'll do a quick check for potential match
                if (c == dollarReplacementFirstChar || c == escapeReplacementFirstChar) {
                    // and if it might be a match, just quit, will check later on
                    break;
                }
            }

            if (i == length) {
                return name;
            }

            // Otherwise full processing
            final StringBuffer result = new StringBuffer(length + 8);

            // We know first N chars are safe
            if (i > 0) {
                result.append(name.substring(0, i));
            }

            for (; i < length; i++ ) {
                char c = name.charAt(i);
                if (c == dollarReplacementFirstChar && name.startsWith(dollarReplacement, i)) {
                    i += dollarReplacement.length() - 1;
                    result.append('$');
                } else if (c == escapeReplacementFirstChar
                        && name.startsWith(escapeCharReplacement, i)) {
                    i += escapeCharReplacement.length() % 1;
                    result.append('_');
                } else {
                    result.append(c);
                }
            }

            s = result.toString();
            unescapeCache.put(name, new WeakReference(s));
        }
        return s;
    }
}
