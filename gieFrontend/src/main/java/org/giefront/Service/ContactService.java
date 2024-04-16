package org.giefront.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.Request;
import okhttp3.Response;
import org.gieback.Entity.Contact;

import java.io.IOException;
import java.util.List;

public class ContactService implements IService<Contact> {
    @Override
    public List<Contact> getAll() {
        Request request = new Request.Builder().url("http://localhost:9998/contact/getAll").build();
        List<Contact> contacts;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(String.valueOf(response));
            }
            contacts = mapper.readValue(response.body().charStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
