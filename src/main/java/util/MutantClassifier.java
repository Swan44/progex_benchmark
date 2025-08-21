package util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MutantClassifier {

    private static final Map<String, int[]> CLASSIFICATION_RULES = Map.ofEntries(
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastBooleanmutants.json", new int[]{2238, 2253}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastBytemutants.json", new int[]{1806, 1821}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastCharmutants.json", new int[]{1702, 1717}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastDoublemutants.json", new int[]{1977, 1992}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastDoubleTolemutants.json", new int[]{2011, 2028}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastFloatmutants.json", new int[]{2133, 2148}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastIntmutants.json", new int[]{1492, 1507}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastLongmutants.json", new int[]{1389, 1404}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastObjmutants.json", new int[]{1278, 1301}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsLastShortmutants.json", new int[]{1595, 1610}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubBooleanmutants.json", new int[]{753, 771}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubBytemutants.json", new int[]{636, 654}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubCharmutants.json", new int[]{597, 615}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubDoublemutants.json", new int[]{675, 693}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubFloatmutants.json", new int[]{714, 732}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubIntmutants.json", new int[]{519, 537}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubLongmutants.json", new int[]{480, 498}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubObjmutants.json", new int[]{441, 459}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsSubShortmutants.json", new int[]{558, 576}),
            Map.entry("D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsDiv\\ArrayUtilsToMapmutants.json", new int[]{236, 261})
    );

    public static void main(String[] args) {
        String inputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsIDJson\\ArrayUtilsmutants.json";
        try {
            classifyMutants(inputFile);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    public static void classifyMutants(String inputFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Read input JSON file
        JsonNode rootNode = mapper.readTree(new File(inputFile));
        if (!rootNode.isArray()) {
            throw new IOException("Input JSON should be an array of mutants");
        }

        // Prepare output containers
        Map<String, List<JsonNode>> outputMap = new HashMap<>();
        for (String filename : CLASSIFICATION_RULES.keySet()) {
            outputMap.put(filename, new ArrayList<>());
        }
        List<JsonNode> unclassified = new ArrayList<>();

        // Classify each mutant
        for (JsonNode mutant : rootNode) {
            int lineNumber = extractLineNumber(mutant.get("difference").asText());
            boolean classified = false;

            for (Map.Entry<String, int[]> entry : CLASSIFICATION_RULES.entrySet()) {
                int[] range = entry.getValue();
                if (lineNumber >= range[0] && lineNumber <= range[1]) {
                    outputMap.get(entry.getKey()).add(mutant);
                    classified = true;
                    break;
                }
            }

            if (!classified) {
                unclassified.add(mutant);
                System.out.printf("Warning: Mutant %s at line %d not classified%n",
                        mutant.get("mutant_id").asText(), lineNumber);
            }
        }

        // Write output files
        for (Map.Entry<String, List<JsonNode>> entry : outputMap.entrySet()) {
            String filename = entry.getKey();
            ArrayNode arrayNode = mapper.createArrayNode();
            entry.getValue().forEach(arrayNode::add);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), arrayNode);
        }

        // Optionally write unclassified mutants
        if (!unclassified.isEmpty()) {
            ArrayNode unclassifiedNode = mapper.createArrayNode();
            unclassified.forEach(unclassifiedNode::add);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("unclassified_mutants.json"), unclassifiedNode);
        }

        System.out.println("Classification completed successfully.");
    }

    private static int extractLineNumber(String difference) {
        // Example: "@@ -29,12 +31,15 @@" -> extract 31 from "+31,15"
        String[] parts = difference.split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid difference format: " + difference);
        }
        String linePart = parts[1]; // "+31,15"
        String lineNumber = linePart.substring(1).split(",")[0]; // Remove '+' and take before comma
        return Integer.parseInt(lineNumber);
    }
}
