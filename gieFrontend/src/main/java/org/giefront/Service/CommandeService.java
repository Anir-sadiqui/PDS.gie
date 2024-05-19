package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Commande;
import org.giefront.DTO.EtatCommande;
import org.giefront.DTO.Product;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
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

}



