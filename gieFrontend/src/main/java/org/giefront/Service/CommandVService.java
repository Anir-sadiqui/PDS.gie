package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.CommandV;

import java.io.IOException;
import java.util.List;

public class CommandVService {
    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public CommandVService() {
        this.mapper = new ObjectMapper();
    }

    public List<CommandV> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/CommandV/CommandVs").build();
        List<CommandV> c;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            c = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

    public void addCom(CommandV c){
        try {
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("application/json"), mapper.writeValueAsString(c));

            Request request = new Request.Builder()
                    .url("http://localhost:9998/CommandV/add")
                    .post(requestBody)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.err.println("Failed to add comm: HTTP " + response.code());
                    if (response.body() != null) {
                        System.err.println("Response body: " + response.body().string());
                    }
                } else {
                    System.out.println("comm added successfully!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with the server", e);
        }
    }

    public void deleteComm(int id ) throws IOException {
        try {
            Request request = new Request.Builder().url("http://localhost:9998/CommandV/deleteComm/"+id).delete().build();
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void validerComm(int id) throws IOException {
        RequestBody requestBody = RequestBody.create(new byte[0]);

        Request request = new Request.Builder()
                .url("http://localhost:9998/CommandV/validerComm/" + id)
                .patch(requestBody)
                .build();

        System.out.println("Sending request to: " + request.url());

        try (Response response = okHttpClient.newCall(request).execute()) {
            System.out.println("Received response: " + response);
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
            throw e;
        }


    }

    public List<CommandV> getByDate(String date){

        Request request = new Request.Builder().url("http://localhost:9998/CommandV/CommandVByDate/?date="+date).build();
        List<CommandV> CommandVs;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            CommandVs = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CommandVs;
    }



    public List<CommandV> getByEtat (String e1){
        Request request = new Request.Builder().url("http://localhost:9998/CommandV/CommandVByEtat/"+e1).build();
        List<CommandV> CommandVs;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            CommandVs = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CommandVs;
    }


}
