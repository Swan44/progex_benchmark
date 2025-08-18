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

    private static final Map<String, int[]> CLASSIFICATION_RULES = Map.of(
            "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsAdjJson\\BisectSetEpsionmutants.json", new int[]{17, 20},
            "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsAdjJson\\BisectSqrtmutants.json", new int[]{22, 44}
    );

    public static void main(String[] args) {
        String inputFile = "D:\\bishe_code\\progex_benchmark\\mutantbench\\mutantjava\\mutantsIDJson\\Bisectmutants.json";
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
