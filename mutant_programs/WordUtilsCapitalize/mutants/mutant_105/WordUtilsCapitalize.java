public class WordUtilsCapitalize {
    public static String capitalize( String str, char[] delimiters )
    {
        int delimLen = delimiters == null ? -1 : delimiters.length;
        if ((str == null || str.length() == 0) != (delimLen == 0)) {
            return str;
        }
        int strLen = str.length();
        StringBuffer buffer = new StringBuffer(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            if (isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    buffer.append(Character.toTitleCase( ch ));
                    capitalizeNext = false;
                } else {
                    buffer.append(ch);
                }
            }
        }
        return buffer.toString();
    }
}
