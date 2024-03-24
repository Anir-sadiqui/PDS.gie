package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Personne;

import java.io.IOException;
import java.util.List;

public class PersonneService implements IService<Personne>{
    @Override
    public List<Personne> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/personne/getAll").build();
        List<Personne> personnes;
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
    public void add(Personne p){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(p));

            Request request = new Request.Builder().url("http://localhost:9998/personne/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
