package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MutantPositionAdjuster {
    private static final Pattern POSITION_PATTERN = Pattern.compile("@@ -(\\d+) \\+(\\d+) @@");

    public static void main(String[] args) {
        String inputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsToMapmutants.json";
        String outputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsAdjJson\\ArrayUtilsToMapmutants.json";
        int offset = 234;

        try {
            adjustMutantPositions(inputFile, outputFile, offset);
            System.out.println("Position adjustment completed successfully.");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    public static void adjustMutantPositions(String inputPath, String outputPath, int offset) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(inputPath));

        if (rootNode.isArray()) {
            for (JsonNode mutantNode : rootNode) {
                if (mutantNode.has("difference")) {
                    String difference = mutantNode.get("difference").asText();
                    String adjustedDifference = adjustDifferenceString(difference, offset);
                    ((ObjectNode) mutantNode).put("difference", adjustedDifference);
                }
            }
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), rootNode);
    }

    private static String adjustDifferenceString(String difference, int offset) {
        Matcher matcher = POSITION_PATTERN.matcher(difference);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int oldLine = Integer.parseInt(matcher.group(1));
            int newLine = Integer.parseInt(matcher.group(2));
            int adjustedOld = oldLine - offset;
            int adjustedNew = newLine - offset;
            matcher.appendReplacement(sb, String.format("@@ -%d +%d @@", adjustedOld, adjustedNew));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
