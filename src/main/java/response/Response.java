package response;

import lombok.Getter;
import utils.Utils;

import java.net.http.HttpResponse;

@Getter
public abstract class Response<T> {

    private final int statusCode;
    private final String body;
    private T instance;

    public Response(HttpResponse<String> response) {
        statusCode = response.statusCode();
        body = response.body();
    }

    public T getObject() {
        if (instance == null) {
            instance = getInstance();
        }
        return instance;
    }

    protected abstract T getInstance();

    public boolean isJSON() {
        return Utils.isJson(getBody());
    }
}
