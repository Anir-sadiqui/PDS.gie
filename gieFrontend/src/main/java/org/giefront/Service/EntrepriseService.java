package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;

import java.io.IOException;
import java.util.List;

public class EntrepriseService implements IService<Entreprise>{
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
    public void add(Entreprise e1){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(e1));

            Request request = new Request.Builder().url("http://localhost:9998/entreprise/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
