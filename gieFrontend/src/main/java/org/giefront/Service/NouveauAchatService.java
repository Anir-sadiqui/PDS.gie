package org.giefront.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.giefront.DTO.Achat;


import java.io.IOException;

public class NouveauAchatService {
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

}
