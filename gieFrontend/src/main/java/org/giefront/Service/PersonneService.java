package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PersonneService implements IService<Personne>{
    @Override
    public List<Personne> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getAll").build();
        List<Personne> personnes;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }
    public void add(Personne p){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(p));

            Request request = new Request.Builder().url("http://localhost:9998/personne/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();
    public Personne getById (int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getById/").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Personne.class);
        }

    }
    public Personne getBynom (String nom) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/GetBynom").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Personne.class);
        }

    }
    public Personne getByprenom (String Prenom) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/GetByprenom").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), Personne.class);
        }

    }
    public List<Personne> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortById").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Personne>>() {});
        }

    }
    public List<Personne> sortBynom(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortBynom").build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            return mapper.readValue(response.body().charStream(), new TypeReference<List<Personne>>() {});
        }

    }
    public void modifierPersonne(int id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/personne/Modifier").patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }

    public void deletePersonne(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/DeletePersonne ").delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }


}
