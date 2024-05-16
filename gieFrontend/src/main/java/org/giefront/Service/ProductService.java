package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Category;
import org.giefront.DTO.Product;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
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

        @Override
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


        @Override
        public void deleteProduct(int idProduct) {
                try {
                    Request request = new Request.Builder().url("http://localhost:9998/lit/delete/"+idProduct ).delete().build();
                    Response response = okHttpClient.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        @Override
        public List<Product> getbyname(String name) {
            return null;
        }

        @Override
        public List<Product> getbyCat(Category cat) {
            return null;
        }
    @Override
    public void update(int id, String name, String description, Category category, int quantite, Double prix) {
        try {
            String descriptionEncoded = URLEncoder.encode(description, StandardCharsets.UTF_8.toString());
            String nameEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
            String categoryEncoded = URLEncoder.encode(category.name(), StandardCharsets.UTF_8.toString()); // Utilise getCategoryName() pour obtenir le nom de la catégorie

            String url = String.format("http://localhost:9998/produit/modify/%d/%s/%s/%s/%f/%d", id, nameEncoded, descriptionEncoded, categoryEncoded, prix, quantite);
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();

            System.out.println(response.body().string());
            System.out.println(response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}






