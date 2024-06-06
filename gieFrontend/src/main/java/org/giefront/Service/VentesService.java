package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;
import org.giefront.DTO.Ventes;
import org.giefront.DTO.Contact;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VentesService {
    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public VentesService() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Contact.class, new ContactDeserializer());
        mapper.registerModule(module);
    }
    public void ajouter(Ventes Ventes){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(Ventes));

            Request request = new Request.Builder().url("http://localhost:9998/Ventes/ajouter").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id){
        try {
            Request request = new Request.Builder().url("http://localhost:9998/Ventes/Supprimer/"+id).delete().build();
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifier(String id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/Ventes/modifier/" + id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }

    public List<Ventes> chercherParFournisseur(int idf){
        Request request = new Request.Builder().url("http://localhost:9998/Ventes/chercherParFournisseur/"+idf).build();
        List<Ventes> p;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            p = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public List<Ventes> getByComm(int idf){
        Request request = new Request.Builder().url("http://localhost:9998/Ventes/getByComm/"+idf).build();
        List<Ventes> p;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            p = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

}
