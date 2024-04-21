package org.giefront.Service;

import okhttp3.*;

import java.io.IOException;

public class MailService {
    private OkHttpClient okHttpClient = new OkHttpClient();


    public void Notifier(String to, String s, String m) throws IOException {
        // Define the media type for JSON
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        // Create a JSON string for the request body
        String json = "{\"to\":\"" + to + "\", \"subject\":\"" + s + "\", \"message\":\"" + m + "\"}";

        // Create the request body with the JSON payload
        RequestBody body = RequestBody.create(json, JSON);

        // Build the POST request
        Request request = new Request.Builder()
                .url("http://localhost:9998/mail/Notifier")
                .post(body)
                .build();

        // Execute the request and handle the response
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // Optionally handle the response body if needed
            System.out.println("Response: " + response.body().string());
        }
    }
}
