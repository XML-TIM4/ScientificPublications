package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.Project.DTOs.LoginDTO;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.security.TokenUtils;
import xmlteam4.Project.services.AuthenticationService;
import xmlteam4.Project.services.UserDetailsServiceImpl;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword());
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
            return new ResponseEntity<>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(authenticationService.register(user), HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}