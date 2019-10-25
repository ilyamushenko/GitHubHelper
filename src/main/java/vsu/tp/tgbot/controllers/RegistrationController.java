package vsu.tp.tgbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.tp.tgbot.response.ApiResponse;
import vsu.tp.tgbot.response.Login;
import vsu.tp.tgbot.service.AuthService;
import vsu.tp.tgbot.response.RegistrationUser;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private AuthService authenticationService;

    @RequestMapping(value ="authenticate", method = RequestMethod.POST)
    public ApiResponse<Void> login(@RequestBody Login login) {
        authenticationService.authenticate(login.getLogin(), login.getToken());
        return new ApiResponse<>("Success");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ApiResponse<Void> addUser(@RequestBody @Valid RegistrationUser userRegistration) {
        authenticationService.register(userRegistration.getLogin(),
                userRegistration.getIdChat(),
                userRegistration.getToken());
        return new ApiResponse<>("Success");
    }

}
