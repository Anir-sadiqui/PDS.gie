package org.giefront.Service;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;
import org.giefront.DTO.Achat;

import java.io.IOException;
import java.util.List;

import static org.giefront.Service.IService.okHttpClient;

public class AchatService {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.getFactory().setStreamReadConstraints(
                StreamReadConstraints.builder().maxNestingDepth(2000).build()
        );
    }

    public List<Achat> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/achat/getAll").build();
        List<Achat> achats;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Failed to retrieve data: " + response);
            }
            achats = mapper.readValue(response.body().string(), new TypeReference<List<Achat>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error while retrieving achats: " + e.getMessage(), e);
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
