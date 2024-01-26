package com.gpa_calculator.gpa_whizz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GreetingController {

    String data = "1";
    int sumTotal = 0;

    @RequestMapping("/get")
    public String getGreeting() {
        // System.out.println("Helllooo");

        return "{\"classCount\": \"" + data + "\"}";
    }

    @RequestMapping("/setClassesPerSemester")
    public String setClassesPerSemester(@RequestBody String inputData) {
        data = inputData;
        return inputData;
    }

    @RequestMapping("/getJSONFile")
    public String getJSONFile() {
        String jsn = "";
        int intData = Integer.parseInt(data);

        for (int i = 0; i < intData; i++) {
            jsn += "{\"ClassName\":\"\",\"Grade\":\"\",\"Honors\":false},";
        }

        jsn = jsn.substring(0, jsn.length() - 1);
        String inputjsn = "[" + jsn + "]";
        return inputjsn;
    }

    @RequestMapping("/convertJsonTo2DArray")
    public static String[][] convertJsonTo2DArray(@RequestBody String jsonString) {
        // int sumTotal = sum;

        try {
            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON string into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Get the number of rows and columns
            int rows = jsonNode.size();
            int cols = 3;

            // Create a 2D array
            String[][] resultArray = new String[rows][cols];

            // Fill the array with values from the JSON
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 3; j++) {
                    if(j == 0) {
                        resultArray[i][j] = jsonNode.get(i).get("ClassName").asText();                        
                    }
                    else if(j == 1) {
                        resultArray[i][j] = jsonNode.get(i).get("Grade").asText();   
                    }
                    else {
                        resultArray[i][j] = jsonNode.get(i).get("Honors").asText();     
                    }

                }
            }

            // for(int i=0; i<resultArray.length; i++) {
            //     sum += Integer.parseInt(resultArray[i][1]);
            // }

            // return resultArray;
            return resultArray;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//     public ResponseEntity<String> yourFunction(@RequestBody String data) {
//     // Your Java function logic here
//     System.out.println("Received data from React.js: " + data);

//     // Return a response if needed
//     return ResponseEntity.ok("Data received successfully");
//     }
}
