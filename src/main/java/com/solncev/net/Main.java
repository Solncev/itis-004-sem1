package com.solncev.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/posts?userId=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            System.out.println(connection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content.toString());
            }
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            URL postUrl = new URL("https://gorest.co.in/public/v1/users");
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            String token = "a3d70ee6db24230185f738f9b0653a40c2747ae5f52ef65c03520eaca00e8495";

            postConnection.setRequestMethod("POST");

            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer " + token);


            postConnection.setDoOutput(true);

            // email should be unique, in otherwise 422 status code will bee returned
            String jsonInputString = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"tenali.ramakrishna1@gmail.com\", \"status\":\"active\"}";

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(postConnection.getInputStream(), StandardCharsets.UTF_8)
                         )
            ) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input.trim());
                }
                System.out.println(content.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
