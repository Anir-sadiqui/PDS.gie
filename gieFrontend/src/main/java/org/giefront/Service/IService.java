package org.giefront.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import java.util.List;
public interface IService<T> {
    OkHttpClient okHttpClient = new OkHttpClient();

    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);

    List<T> getAll();
}
