package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Commande;
import org.giefront.DTO.EtatCommande;

import java.io.IOException;
import java.util.List;

import static org.giefront.Service.IService.mapper;
import static org.giefront.Service.IService.okHttpClient;

public class CommandeService {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private static final String API_URL = "http://localhost:9998/Commande/"; // Replace with your actual backend URL


    public List<Commande> getAll() {
        Request request = new Request.Builder().url(API_URL+"Commandes").build();
        List<Commande> cmnds;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            cmnds = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cmnds;

    }


    public List<Commande> getByEt(EtatCommande e){
        Request request = new Request.Builder().url("http://localhost:9998/Commande/CommandeByEtat/"+e).build();
        List<Commande> m;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            m = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException ec) {
            throw new RuntimeException(ec);
        }
        return m;

    }

    public List<Commande> getBydate(EtatCommande d){
        Request request = new Request.Builder().url("http://localhost:9998/Commande/CommandeBydate").build();
        List<Commande> n;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            n = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
        } catch (IOException ec) {
            throw new RuntimeException(ec);
        }
        return n;

    }


    public void addComm(Commande commande) throws IOException {
        String jsonInputString = mapper.writeValueAsString(commande);
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonInputString, JSON);
        Request request = new Request.Builder()
                .url(API_URL + "add")
                .post(body)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public CommandeService() {
    }
}
