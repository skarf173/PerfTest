package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java ReportBuilder <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesPath = args[0];
        String testsPath  = args[1];
        String reportPath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        JsonNode valuesRoot = mapper.readTree(new File(valuesPath));

        Map<Integer, String> valuesById = new HashMap<>();

        JsonNode valuesArray = valuesRoot.get("values");
        if (valuesArray != null && valuesArray.isArray()) {
            for (JsonNode item : valuesArray) {
                int id = item.get("id").asInt();
                String value = item.get("value").asText();
                valuesById.put(id, value);
            }
        }

        JsonNode testsRoot = mapper.readTree(new File(testsPath));

        JsonNode testsArray = testsRoot.get("tests");
        if (testsArray != null && testsArray.isArray()) {
            for (JsonNode test : testsArray) {
                fillValues(test, valuesById);
            }
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(reportPath), testsRoot);
    }

    private static void fillValues(JsonNode node, Map<Integer, String> valuesById) {
        if (node == null || !node.isObject()) {
            return;
        }

        ObjectNode objectNode = (ObjectNode) node;

        if (objectNode.has("id")) {
            int id = objectNode.get("id").asInt();

            if (valuesById.containsKey(id)) {
                objectNode.put("value", valuesById.get(id));
            }
        }

        JsonNode children = objectNode.get("values");
        if (children != null && children.isArray()) {
            for (JsonNode child : children) {
                fillValues(child, valuesById);
            }
        }
    }
}