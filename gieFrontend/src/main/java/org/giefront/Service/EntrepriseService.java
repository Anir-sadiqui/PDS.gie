package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntrepriseService implements IService {
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
    public void modifierEntreprise(int id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/ModifierEntreprise/"+id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }

    public void deleteEntreprise(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/DeleteEntreprise/"+id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));

            }
        }
    }
    public List<Entreprise> sortById(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortById/"+ordre).build();
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
    public List<Entreprise> sortByRs(String ordre) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/sortByRs/"+ordre).build();
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

    public Entreprise getByRs(String rs) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getByRs/" + rs).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            return mapper.readValue(response.body().string(), Entreprise.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute getByRs request", e);
        }
    }
//    public List<Entreprise> getByFj(String fj) throws IOException {
//        Request request = new Request.Builder().url("http://localhost:9998/entreprise/getByFj/"+fj).build();
//        try (Response response = okHttpClient.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException(String.valueOf(response));
//            }
//            return Collections.singletonList(mapper.readValue(response.body().charStream(), Entreprise.class));
//        }
//
//    }
public List<Entreprise> getByFj(String fj) {
    Request request = new Request.Builder().url("http://localhost:9998/entreprise/getByFj/" + fj).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code: " + response.code());
        }
        return mapper.readValue(response.body().string(), new TypeReference<List<Entreprise>>() {});
    } catch (IOException e) {
        throw new RuntimeException("Failed to execute getByFj request", e);
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
        List<Entreprise> e1;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            e1 = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return e1;
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