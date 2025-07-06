package task_manager.testing.service;

import task_manager.testing.model.responce.AuthenticationRequest;
import task_manager.testing.model.responce.AuthenticationResponce;

public interface UserService {

    AuthenticationResponce authUser(AuthenticationRequest authenticationRequest);

}
