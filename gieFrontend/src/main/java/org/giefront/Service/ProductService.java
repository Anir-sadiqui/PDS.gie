package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import org.giefront.DTO.Product;
import java.util.List;
import java.util.Map;

public class ProductService  implements IService {
    private OkHttpClient okHttpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public void add(Product p) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(p));

            Request request = new Request.Builder().url("http://localhost:9998/entreprise/add").post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            System.out.println(response.code());
            System.out.println(response.body().toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
   public List<Product> getAll() {
                Request request = new Request.Builder().url("http://localhost:9998/entreprise/getAll").build();
                List<Product> products;
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new IOException(String.valueOf(response));
                    }
                    products = mapper.readValue(response.body().charStream(), new TypeReference<>() {
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return products;
            }


    public void modify(int id, Map<String, String> attributs) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = mapper.writeValueAsString(attributs);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("http://localhost:9998/entreprise/ModifierEntreprise/" + id).patch(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
        }
    }


}
