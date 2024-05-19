package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Achat;
import org.giefront.DTO.Personne;


import java.io.IOException;
import java.util.List;

public class AchatService{
    private OkHttpClient okHttpClient = new OkHttpClient();
     ObjectMapper mapper = new ObjectMapper();

    public void ajouter(Achat achat){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(achat));

            Request request = new Request.Builder().url("http://localhost:9998/achat/ajouter").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Achat> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/achat/getAll").build();
        List<Achat> personnes;
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

}
