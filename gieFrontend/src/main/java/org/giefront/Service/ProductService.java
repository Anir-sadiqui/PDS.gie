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
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("application/json"), mapper.writeValueAsString(p));

                Request request = new Request.Builder()
                        .url("http://localhost:9998/Product/add")
                        .post(requestBody)
                        .build();

                try (Response response = okHttpClient.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        System.err.println("Failed to add product: HTTP " + response.code());
                        if (response.body() != null) {
                            System.err.println("Response body: " + response.body().string());
                        }
                    } else {
                        System.out.println("Product added successfully!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to communicate with the server", e);
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
                    Request request = new Request.Builder().url("http://localhost:9998/Product/deleteProd/"+idProduct ).delete().build();
                    Response response = okHttpClient.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

        public Product getbyname(String name) {
            Request request = new Request.Builder()
                    .url("http://localhost:9998/Product/getByName/" + name)
                    .build();
            Product product = null;

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + response.code() + " - " + response.message());
                }

                if (response.body() == null) {
                    throw new IOException("Response body is null");
                }

                product = mapper.readValue(response.body().charStream(), new TypeReference<>() {});
            } catch (IOException e) {
                return null;
            }

            return product;

        }


        public List<Product> getbyCat(String cat) {
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


        public List<Product> isAvailable(String t){
            Request request = new Request.Builder().url("http://localhost:9998/Product/isAvailable/"+t).build();
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
        public void ajoutQ(int q , int id) throws IOException {
            RequestBody requestBody = RequestBody.create(new byte[0]);

            Request request = new Request.Builder()
                    .url("http://localhost:9998/Product/ajouterQ/" + q+"/"+id)
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
}












