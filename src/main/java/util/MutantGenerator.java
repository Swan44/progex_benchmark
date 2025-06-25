package util;

import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class MutantGenerator {

    // 生成单个变异程序（处理所有变异点）
    public static String generateMutant(String originalPath, List<Map<String, Object>> mutants) throws IOException {
        String originalCode = Files.readString(Paths.get(originalPath));
        StringBuilder mutantCode = new StringBuilder(originalCode);

        // 按行号倒序处理避免行号偏移
        mutants.sort(Comparator.comparingInt(m -> -getLineNumber(m.get("difference").toString())));

        for (Map<String, Object> mutant : mutants) {
            applyMutation(mutantCode, mutant);
        }

        return mutantCode.toString();
    }

    // 应用单个变异
    private static void applyMutation(StringBuilder code, Map<String, Object> mutant) {
        String diff = mutant.get("difference").toString();
        int lineNum = getLineNumber(diff);
        String[] changes = parseDiff(diff);

        if (lineNum > 0 && changes.length == 2) {
            replaceLine(code, lineNum, changes[0], changes[1]);
            System.out.printf("已应用变异(行%d): %s → %s [%s]\n",
                    lineNum, changes[0].trim(), changes[1].trim(),
                    mutant.get("operator"));
        }
    }

    // 从diff提取行号（适配多行号格式）
    private static int getLineNumber(String diff) {
        try {
            // 处理 "@@ -5,4 +5,4 @@" 或 "@@ -5 +5 @@" 两种格式
            String linePart = diff.split("@@")[1].trim().split(" ")[0];
            return Integer.parseInt(linePart.substring(1).split(",")[0]);
        } catch (Exception e) {
            System.err.println("无法解析行号: " + diff);
            return -1;
        }
    }

    // 增强的diff解析（支持多行变更）
    private static String[] parseDiff(String diff) {
        String[] lines = diff.split("\n");
        if (lines.length >= 3) {
            return new String[]{
                    unescapeDiffLine(lines[1]),
                    unescapeDiffLine(lines[2])
            };
        }
        return new String[0];
    }

    private static String unescapeDiffLine(String line) {
        return StringEscapeUtils.unescapeJava(line.substring(1))
                .replace("\t", "    ");
    }

    // 精确行替换（保留缩进）
    private static void replaceLine(StringBuilder code, int lineNum,
                                    String oldLine, String newLine) {
        String[] lines = code.toString().split("\n");
        if (lineNum <= lines.length) {
            String currentLine = lines[lineNum-1];

            // 保留原始缩进（取原行的空白前缀）
            String indent = currentLine.substring(0, currentLine.indexOf(currentLine.trim()));
            lines[lineNum-1] = indent + newLine.trim();

            code.setLength(0);
            code.append(String.join("\n", lines));
        }
    }
}