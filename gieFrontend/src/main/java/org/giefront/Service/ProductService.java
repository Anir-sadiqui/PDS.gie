package org.giefront.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;

import org.giefront.DTO.Category;
import org.giefront.DTO.Personne;
import org.giefront.DTO.Product;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.giefront.Service.IService.okHttpClient;

public class ProductService   {
        private OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        public void add(Product p) {
            try {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(p));

                Request request = new Request.Builder().url("http://localhost:9998/Product/add").post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                Response response = call.execute();
                System.out.println(response.code());
                System.out.println(response.body().toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public List<Product> getAll() {
            Request request = new Request.Builder().url("http://localhost:9998/Product/getAllprod").build();
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
            Request request = new Request.Builder().url("http://localhost:9998/Product/Modify/" + id).patch(body).build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException(String.valueOf(response));
                }
            }
        }



        public void deleteProduct(int idProduct) {
                try {
                    Request request = new Request.Builder().url("http://localhost:9998/Product/delete/"+idProduct ).delete().build();
                    Response response = okHttpClient.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        public List<Product> getbyname(String name) {
            Request request = new Request.Builder().url("http://localhost:9998/Product/getByName/"+name).build();
            List<Product> personnes;
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException(String.valueOf(response));
                }
                personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return personnes;
        }


        public List<Product> getbyCat(Category cat) {
            Request request = new Request.Builder().url("http://localhost:9998/Product/getByCat/"+cat).build();
            List<Product> personnes;
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException(String.valueOf(response));
                }
                personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return personnes;
        }
        public List<Product> isAvailable(String t){ Request request = new Request.Builder().url("http://localhost:9998/Product//isAvailable/"+t).build();
            List<Product> personnes;
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException(String.valueOf(response));
                }
                personnes = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return personnes;
        }
}












