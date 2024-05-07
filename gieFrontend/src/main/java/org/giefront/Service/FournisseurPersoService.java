package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FournisseurPersoService implements IService{
    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<FournisseurPersoDTO> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getAll").build();
        List<FournisseurPersoDTO> fournisseurPerso;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }
    public void add(FournisseurPersoDTO f){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(f));

            Request request = new Request.Builder().url("http://localhost:9998/personne/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public FournisseurPersoDTO getById (int id)  {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getById/"+id).build();
        FournisseurPersoDTO fournisseurPerso;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }
    public List<FournisseurPersoDTO> getBynom (String nom) {
        Request request = new Request.Builder().url("http://localhost:9998/personne/GetBynom/"+nom).build();
        List<FournisseurPersoDTO> fournisseurPerso;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }
    public List<FournisseurPersoDTO> getByPrenom (String prenom) {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getPersonneByPrenom/"+prenom).build();
        List<FournisseurPersoDTO> fournisseurPerso;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }
    public List<FournisseurPersoDTO> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortByid/"+ordre).build();
        List<FournisseurPersoDTO> fournisseurPerso;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }
    public List<FournisseurPersoDTO> sortBynom(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/sortBynom/"+ordre).build();
        List<FournisseurPersoDTO> fournisseurPerso;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
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

    public void deletePersonne(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/personne/DeletePersonne/"+id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }
    public FournisseurPersoDTO getByEmail (String email)  {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getByemail/"+email).build();
        FournisseurPersoDTO fournisseurPerso;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            fournisseurPerso = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurPerso;
    }


}
