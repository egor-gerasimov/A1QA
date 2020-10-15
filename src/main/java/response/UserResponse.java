package response;

import models.User;
import utils.Utils;

import java.net.http.HttpResponse;

public class UserResponse extends Response<User> {

    public UserResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected User getInstance() {
        return Utils.readObjectFromJSON(getBody(), User.class);
    }
}
