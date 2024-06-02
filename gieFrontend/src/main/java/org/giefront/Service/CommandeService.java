package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Achat;
import org.giefront.DTO.Commande;
import org.giefront.DTO.EtatCommande;
import org.giefront.DTO.Product;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CommandeService {

    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();
    public CommandeService() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

        public List<Commande> getAll() {
            Request request = new Request.Builder()
                    .url("http://localhost:9998/Commande/Commandes")
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                return mapper.readValue(response.body().string(), mapper.getTypeFactory().constructCollectionType(List.class, Commande.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void addCom(Commande c){
            try {
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("application/json"), mapper.writeValueAsString(c));

                Request request = new Request.Builder()
                        .url("http://localhost:9998/Commande/add")
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
            Request request = new Request.Builder().url("http://localhost:9998/Commande/deleteComm/"+id).delete().build();
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void validerComm(int id) throws IOException {
        RequestBody requestBody = RequestBody.create(new byte[0]);

        Request request = new Request.Builder()
                .url("http://localhost:9998/Commande/validerComm/" + id)
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

    public List<Commande> getByDate(LocalDate date){

        Request request = new Request.Builder().url("http://localhost:9998/Commande/CommandeByDate/?date="+date).build();
        List<Commande> commandes;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            commandes = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }



    public List<Commande> getByEtat (String e1){
        Request request = new Request.Builder().url("http://localhost:9998/Commande/CommandeByEtat/"+e1).build();
        List<Commande> commandes;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            commandes = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }




}



