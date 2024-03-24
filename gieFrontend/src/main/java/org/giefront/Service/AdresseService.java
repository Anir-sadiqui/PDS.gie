package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.gieback.Entity.Adresse;

import java.io.IOException;
import java.util.List;

public class AdresseService implements IService<Adresse>{
    @Override
    public List<Adresse> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/adresse/getAll").build();
        List<Adresse> adresses;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            adresses = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return adresses;
    }
    public void add(Adresse a){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),mapper.writeValueAsString(a));

            Request request = new Request.Builder().url("http://localhost:9998/adresse/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
