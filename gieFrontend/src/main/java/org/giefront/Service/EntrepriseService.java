package org.giefront.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EntrepriseService  {
    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();


    public List<Entreprise> getAll(ContactType t) {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/GetByType/"+t).build();
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
    public void add(Entreprise f){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(f));

            Request request = new Request.Builder().url("http://localhost:9998/entreprise/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Entreprise getById (int id)  {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getById/"+id).build();
        Entreprise entreprise;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprise = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprise;
    }
    public List<Entreprise> getBynom (String nom) {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/GetBynom/"+nom).build();
        List<Entreprise> entreprises;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprises = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprises;
    }
    public List<Entreprise> getByPrenom (String prenom) {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getPersonneByPrenom/"+prenom).build();
        List<Entreprise> entreprises;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprises = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprises;
    }
    public List<Entreprise> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortByid/"+ordre).build();
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
    public List<Entreprise> sortBynom(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortBynom/"+ordre).build();
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
    public void modifierPersonne(String id, HashMap<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/Modifier/"+id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }

    public void deleteEntro(Long id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/DeleteEntreprise/"+id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }
    public Entreprise getByEmail (String email)  {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getByemail/"+email).build();
        Entreprise entreprise;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprise = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprise;
    }
    public List<Entreprise> getByType(ContactType t){
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/GetByType/"+t).build();
        List<Entreprise> entreprises;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            entreprises = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entreprises;
    }
    public void addType(int id , ContactType t) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/addType/" + id + "/" + t).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }
    public void deleteType (int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/deleteType/" + id ).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }



}
