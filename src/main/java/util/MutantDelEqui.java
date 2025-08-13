package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
public class MutantDelEqui {
    public static void main(String[] args) {
        // 输入文件和输出文件路径
        String inputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsIDJson\\Trianglemutants.json";
        String outputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDelJson\\Trianglemutants.json";

        try {
            // 创建ObjectMapper实例
            ObjectMapper objectMapper = new ObjectMapper();

            // 从文件读取JSON数据
            JsonNode rootNode = objectMapper.readTree(new File(inputFile));

            // 检查是否是数组
            if (rootNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) rootNode;

                // 遍历数组中的每个对象
                for (JsonNode node : arrayNode) {
                    if (node.isObject()) {
                        ObjectNode objectNode = (ObjectNode) node;
                        // 移除equivalence字段
                        objectNode.remove("equivalence");
                    }
                }
            }

            // 将处理后的JSON写入输出文件
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), rootNode);

            System.out.println("处理完成，结果已写入 " + outputFile);

        } catch (IOException e) {
            System.err.println("处理JSON文件时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
