package com.example.wait4it.Utilities;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    // Static method for sending a GET request
    public static String sendGetRequest(String urlString) throws Exception {
        HttpURLConnection connection = createConnection(urlString, "GET");
        return getResponse(connection);
    }

    // Static method for sending a POST request
    public static String sendPostRequest(String urlString, String jsonInputString) throws Exception {
        HttpURLConnection connection = createConnection(urlString, "POST");
        sendRequestBody(connection, jsonInputString);
        return getResponse(connection);
    }

    // Helper method to create and configure the connection
    private static HttpURLConnection createConnection(String urlString, String method) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        if (method.equals("POST")) {
            connection.setDoOutput(true);
        }
        return connection;
    }

    // Helper method to send the request body (for POST)
    private static void sendRequestBody(HttpURLConnection connection, String jsonInputString) throws Exception {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    // Helper method to handle and return the response
    private static String getResponse(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line.trim());
                }
                return response.toString();
            }
        } else {
            throw new Exception("Request failed with response code: " + responseCode);
        }
    }
}