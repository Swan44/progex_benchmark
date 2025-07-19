package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MutantIdGenerator {
    /**
     * 为变异体JSON文件添加ID字段
     * @param inputPath  输入JSON文件路径
     * @param outputPath 输出JSON文件路径
     * @param idPrefix   ID前缀（如"M"）
     * @param idFormat   ID格式（如"%03d"）
     * @throws IOException
     */
    public static void addMutantIds(String inputPath, String outputPath,
                                    String idPrefix, String idFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // 1. 读取原始JSON
        List<Map<String, Object>> mutants = mapper.readValue(
                new File(inputPath),
                new TypeReference<List<Map<String, Object>>>(){});

        // 2. 处理并添加ID
        List<Map<String, Object>> enhancedMutants = new ArrayList<>();
        for (int i = 0; i < mutants.size(); i++) {
            Map<String, Object> newMutant = new LinkedHashMap<>();

            // 添加ID作为第一个字段
            String mutantId = idPrefix + String.format(idFormat, i + 1);
            newMutant.put("mutant_id", mutantId);

            // 保留原始字段
            newMutant.putAll(mutants.get(i));
            enhancedMutants.add(newMutant);
        }

        // 3. 写回文件
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(outputPath), enhancedMutants);
    }

    /**
     * 默认添加三位数字ID（M000, M001...）
     */
    public static void addMutantIds(String inputPath, String outputPath) throws IOException {
        addMutantIds(inputPath, outputPath, "M", "%03d");
    }
    public static void main(String[] args) {
        try {
            addMutantIds(
                    "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsJson\\Trianglemutants.json",
                    "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsIDJson\\Trianglemutants.json",
                    "MUT_",  // 自定义前缀
                    "%03d"   // 4位数字ID
            );

            // 或使用默认配置
            // addMutantIds("input.json", "output.json");

            System.out.println("ID添加完成！");
        } catch (IOException e) {
            System.err.println("处理失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
