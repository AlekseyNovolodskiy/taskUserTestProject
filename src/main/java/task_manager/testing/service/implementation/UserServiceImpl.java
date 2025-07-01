package task_manager.testing.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.model.UserRole;
import task_manager.testing.model.responce.AuthenticationRequest;
import task_manager.testing.model.responce.AuthenticationResponce;
import task_manager.testing.model.responce.RegisterRequest;
import task_manager.testing.repositories.UserRepository;
import task_manager.testing.security.JwtService;
import task_manager.testing.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponce registrationNewUser(RegisterRequest request) {
        UserEntity user = new UserEntity();

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponce authenticationResponce = new AuthenticationResponce(jwtToken);
        return authenticationResponce;
    }

    @Override
    public AuthenticationResponce authUser(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findUserEntitiesByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }
    }

