package org.giefront.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.giefront.DTO.Achat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NouveauAchatService {
    private ObjectMapper mapper = new ObjectMapper();

    public void ajouter(Achat achat) {
        HttpURLConnection connection = null;
        try {
            // Create the URL and open a connection
            URL url = new URL("http://localhost:9998/achat/ajouter");
            connection = (HttpURLConnection) url.openConnection();

            // Configure the connection
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");;

            String jsonString = mapper.writeValueAsString(achat);
            // Write the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode.   " + responseCode);
            // Handle the response
            if (responseCode == 201) {
                // Read the response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    System.out.println("Achat added successfully: " + content.toString());
                }
            } else {
                // Read the error stream
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String errorLine;
                    StringBuilder errorContent = new StringBuilder();
                    while ((errorLine = errorReader.readLine()) != null) {
                        errorContent.append(errorLine);
                    }
                    throw new IOException("Failed to add Achat: " + responseCode + " - " + errorContent.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while adding achat: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
