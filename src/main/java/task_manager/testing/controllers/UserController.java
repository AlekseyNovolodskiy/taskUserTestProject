package task_manager.testing.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Поле для авторизации в системе"
        , description = "'adminemail/password' для  доступа ADMIN и 'useremail/password' для  доступа User ")
public class UserController {

    private final UserService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponce> authenticate (@RequestBody AuthenticationRequest authenticationRequest){
        return  ResponseEntity.ok(authService.authUser(authenticationRequest));
    }
}
