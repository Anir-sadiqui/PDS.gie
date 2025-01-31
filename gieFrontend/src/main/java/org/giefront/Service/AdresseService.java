package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import org.giefront.DTO.Adresse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdresseService implements IService{
    @Override
    public List<Adresse> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/getAll").build();
        List<Adresse> adresses;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            adresses = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return adresses;
    }

    public void add(Adresse a) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(a));

            Request request = new Request.Builder().url("http://localhost:9998/adresse/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    public Adresse getAdresseById(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/getById/"+id).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Adresse.class);
        }
    }

    public List<Adresse> getByVille(String ville) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/getByVille/"+ville).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Adresse>>() {
            });
        }
    }

    public List<Adresse> getByQuartier(String quartier, String ville) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/getByQuartier/"+quartier+"/"+ville).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Adresse>>() {
            });
        }
    }

    public void modifierAdresse(int id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/adresse/ModifierAdresse/"+id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(response.body().string());
            }
        }
    }


    public List<Adresse> sortByVille(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/sortByVille/"+ordre).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Adresse>>() {});
        }
    }

    public void deleteById(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/DeleteById/"+id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }
}
