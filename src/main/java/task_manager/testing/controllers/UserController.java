package task_manager.testing.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_manager.testing.model.responce.AuthenticationRequest;
import task_manager.testing.model.responce.AuthenticationResponce;
import task_manager.testing.service.UserService;

import static task_manager.testing.controllers.ControllerConstant.TASK_CONTROLLER_HEAD;

@RestController
@RequiredArgsConstructor
@RequestMapping(TASK_CONTROLLER_HEAD)
public class UserController {

    private final UserService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponce> authenticate (@RequestBody AuthenticationRequest authenticationRequest){
        return  ResponseEntity.ok(authService.authUser(authenticationRequest));
    }
}
