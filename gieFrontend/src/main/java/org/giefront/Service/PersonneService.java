package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import okhttp3.*;

import org.giefront.DTO.Contact;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Personne;


import java.io.IOException;
import java.util.*;

public class PersonneService implements IService{
    private OkHttpClient okHttpClient = new OkHttpClient();
     ObjectMapper mapper = new ObjectMapper();

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
//    public void add(Personne p){
//        try {
//            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(p));
//
//            Request request = new Request.Builder().url("http://localhost:9998/personne/add").post(requestBody).build();
//            Call call = okHttpClient.newCall(request);
//            Response response = call.execute();
//            System.out.println(response.code());
//            System.out.println(response.body().toString());
//
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void add(Personne p) {
        try {
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("application/json"), mapper.writeValueAsString(p));

            Request request = new Request.Builder()
                    .url("http://localhost:9998/personne/add")
                    .post(requestBody)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.err.println("Failed to add person: HTTP " + response.code());
                    if (response.body() != null) {
                        System.err.println("Response body: " + response.body().string());
                    }
                } else {
                    System.out.println("Person added successfully!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with the server", e);
        }
    }

    public Personne getById (int id)  {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getById/"+id).build();
        Personne personne;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personne = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personne;
    }
    public Contact getAsContactBynom (String nom) {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getPersonneAsContact/"+nom+"/here").build();
        Contact personne;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personne = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(personne);
        return personne;
    }
    public Personne getBynom (String nom) {
        Request request = new Request.Builder().url("http://localhost:9998/personne/GetBynom/"+nom).build();
        Personne personne;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personne = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(personne);
        return personne;
    }
    public List<Personne> getByPrenom (String prenom) {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getPersonneByPrenom/"+prenom).build();
        List<Personne> personnes;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }
    public List<Personne> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortByid/"+ordre).build();
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
    public List<Personne> sortBynom(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortBynom/"+ordre).build();
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
    public void modifierPersonne(String id, HashMap<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/personne/Modifier/"+id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }

    public void deletePersonne(Long id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/DeletePersonne/"+id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }
    public Personne getByEmail (String email)  {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getByemail/"+email).build();
        Personne personne;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personne = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personne;
    }
    public List<Personne> getByType(ContactType t){
        Request request = new Request.Builder().url("http://localhost:9998/personne/GetByType/"+t).build();
        List<Personne> personnes;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }
    public void addType(int id , ContactType t) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/addType/" + id + "/" + t).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }
    public void deleteType (int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/deleteType/" + id ).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Échec de la requête : " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'exécution de la requête HTTP", e);
        }
    }
}
