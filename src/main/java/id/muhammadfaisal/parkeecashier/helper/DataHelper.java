package id.muhammadfaisal.parkeecashier.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

public class DataHelper<T> {
    public T convertLinkedTreeMap(LinkedTreeMap<?, ?> map, Class<T> tClass) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(map).getAsJsonObject();
        return gson.fromJson(jsonObject, tClass);

    }
}
