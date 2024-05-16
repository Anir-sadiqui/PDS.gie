package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.Request;
import okhttp3.Response;
import org.giefront.DTO.Achat;
import org.giefront.DTO.Adresse;

import java.io.IOException;
import java.util.List;

import static org.giefront.Service.IService.mapper;
import static org.giefront.Service.IService.okHttpClient;

public class AchatService {
    public List<Achat> getAllAchats() {
        Request request = new Request.Builder().url("http://localhost:9998/achat/getAll").build(); // Corrected URL
        List<Achat> achats; // Corrected variable type
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Failed to retrieve data: " + response);
            }
            achats = mapper.readValue(response.body().string(), new TypeReference<List<Achat>>() {}); // Corrected TypeReference
        } catch (IOException e) {
            throw new RuntimeException("Error while retrieving achats: " + e.getMessage(), e); // Improved exception handling
        }
        return achats;
    }

    public void deleteAchatById(int id) throws IOException {
        Request request = new Request.Builder().url("http://localhost:9998/achat/Supprimer/" + id).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to delete Achat with ID: " + id + ", response code: " + response.code());
            }
        }
    }
}
