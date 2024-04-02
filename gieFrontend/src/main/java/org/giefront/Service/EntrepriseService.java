package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EntrepriseService implements IService<Entreprise> {
    @Override
    public List<Entreprise> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getAll").build();
        List<Entreprise> entreprises;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprises = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprises;
    }

    public void add(Entreprise e1) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(e1));

            Request request = new Request.Builder().url("http://localhost:9998/entreprise/add").post(requestBody).build();
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

    public Entreprise getById(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getById/").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Entreprise.class);
        }

    }

    public void modifierEntreprise(int id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/ModifierEntreprise").patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }

    public void deleteEntreprise(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/DeleteEntreprise").delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }
    public List<Entreprise> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortById").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Entreprise>>() {});
        }

    }
    public List<Entreprise> sortByRs(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortByRs").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Entreprise>>() {});
        }

    }
    public Entreprise getByRs(String rs) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getByRs/").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Entreprise.class);
        }

    }


}