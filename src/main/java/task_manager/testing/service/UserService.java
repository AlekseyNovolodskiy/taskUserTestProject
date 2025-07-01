package task_manager.testing.service;

import task_manager.testing.model.responce.AuthenticationRequest;
import task_manager.testing.model.responce.AuthenticationResponce;
import task_manager.testing.model.responce.RegisterRequest;

public interface UserService {
    AuthenticationResponce registrationNewUser (RegisterRequest request);
    AuthenticationResponce authUser(AuthenticationRequest authenticationRequest);

}
