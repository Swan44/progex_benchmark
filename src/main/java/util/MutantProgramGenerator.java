package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MutantProgramGenerator {
    public static void main(String[] args) {
        // 原始文件路径
        String originalFilePath = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantjavadiv\\WordUtilsCapitalize.java";

        // 变异体信息JSON文件路径（或直接传入JSON字符串）
        String mutantsJsonPath = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsAdjDelJson\\WordUtilsCapitalizemutants.json";

        // 输出目录
        String outputBaseDir = "D:\\bishe_code\\progex_benchmark\\mutant_programs\\WordUtilsCapitalize\\mutants";

        try {
            // 读取JSON文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(mutantsJsonPath)));

            // 使用 Fastjson 解析 JSON
            JSONArray mutantsInfo = JSON.parseArray(jsonContent);

            // 生成变异体
            generateMutants(originalFilePath, mutantsInfo, outputBaseDir);

            //System.out.println("所有变异体生成完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateMutants(String originalFilePath, JSONArray mutantsInfo, String outputBaseDir) throws IOException {
        // 读取原始文件内容
        List<String> originalLines = Files.readAllLines(Paths.get(originalFilePath));

        // 处理每个变异体
        for (int i = 0; i < mutantsInfo.size(); i++) {
            JSONObject mutant = mutantsInfo.getJSONObject(i);
            String mutantId = mutant.getString("mutant_id");
            String difference = mutant.getString("difference");

            // 解析差异信息
            DiffInfo diffInfo = parseDiff(difference);
            if (diffInfo == null) {
                System.err.println("无法解析差异信息: " + difference);
                continue;
            }

            // 复制原始内容
            List<String> mutatedLines = new ArrayList<>(originalLines);

            // 应用变异
            if (diffInfo.originalLine != null && diffInfo.mutatedLine != null) {
                mutatedLines.set(diffInfo.lineNumber - 1, diffInfo.mutatedLine);
            }

            // 创建变异体目录
            String mutantNum = mutantId.split("_")[1];
            Path mutantDir = Paths.get(outputBaseDir, "mutant_" + mutantNum);
            Files.createDirectories(mutantDir);

            // 写入变异体文件
            Path outputFile = mutantDir.resolve(Paths.get(originalFilePath).getFileName());
            Files.write(outputFile, mutatedLines);

            System.out.println("已生成变异体 " + mutantId + " 到 " + outputFile);
        }
    }

    private static DiffInfo parseDiff(String difference) {
        // 使用正则表达式解析差异信息
        Pattern pattern = Pattern.compile("@@ -(\\d+).*?\\+(\\d+).*?@@\\n-(.*?)\\n\\+(.*)");
        Matcher matcher = pattern.matcher(difference);

        if (matcher.find()) {
            DiffInfo diffInfo = new DiffInfo();
            diffInfo.lineNumber = Integer.parseInt(matcher.group(1));
            diffInfo.originalLine = matcher.group(3);
            diffInfo.mutatedLine = matcher.group(4);
            return diffInfo;
        }
        return null;
    }

    static class DiffInfo {
        int lineNumber;
        String originalLine;
        String mutatedLine;
    }

}
