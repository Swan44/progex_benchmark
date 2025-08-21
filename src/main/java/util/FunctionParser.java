package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser {

    public static void main(String[] args) {
        String filePath = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\ArrayUtils.java"; // 替换为实际文件路径
        List<FunctionInfo> functions = parseFunctions(filePath);

        for (FunctionInfo func : functions) {
            System.out.println(func.signature + "：" + func.startLine + "-" + func.endLine);
        }
    }

    public static List<FunctionInfo> parseFunctions(String filePath) {
        List<FunctionInfo> functions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            FunctionInfo currentFunction = null;
            int braceCount = 0;
            boolean inFunction = false;

            Pattern functionPattern = Pattern.compile(
                    "^\\s*(public|protected|private|static|\\s)+[\\w\\<\\>\\[\\]]+\\s+(\\w+)\\s*\\([^)]*\\)\\s*\\{?\\s*$"
            );

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // 检查是否是函数开始
                if (!inFunction && isFunctionSignature(line)) {
                    currentFunction = new FunctionInfo();
                    currentFunction.signature = extractSignature(line);
                    currentFunction.startLine = lineNumber;
                    inFunction = true;
                    braceCount = countOpeningBraces(line) - countClosingBraces(line);
                    continue;
                }

                // 如果在函数体内
                if (inFunction && currentFunction != null) {
                    braceCount += countOpeningBraces(line);
                    braceCount -= countClosingBraces(line);

                    // 如果大括号计数为0，说明函数结束
                    if (braceCount <= 0) {
                        currentFunction.endLine = lineNumber;
                        functions.add(currentFunction);
                        inFunction = false;
                        currentFunction = null;
                        braceCount = 0;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return functions;
    }

    private static boolean isFunctionSignature(String line) {
        // 匹配函数签名模式
        return line.matches("^\\s*(public|protected|private|static|\\s)+[\\w\\<\\>\\[\\]]+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{?\\s*$") &&
                !line.matches(".*(class|interface|enum|if|for|while|switch|catch).*") &&
                !line.trim().startsWith("//") && !line.trim().startsWith("*");
    }

    private static String extractSignature(String line) {
        // 移除注释
        line = line.replaceAll("//.*", "").trim();

        // 如果行以{结尾，移除{
        if (line.endsWith("{")) {
            line = line.substring(0, line.length() - 1).trim();
        }

        return line;
    }

    private static int countOpeningBraces(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == '{') count++;
        }
        return count;
    }

    private static int countClosingBraces(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == '}') count++;
        }
        return count;
    }

    static class FunctionInfo {
        String signature;
        int startLine;
        int endLine;

        @Override
        public String toString() {
            return signature + "：" + startLine + "-" + endLine;
        }
    }
}
