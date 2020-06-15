package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.Project.DTOs.LoginDTO;
import xmlteam4.Project.DTOs.UserDTO;
import xmlteam4.Project.model.AuthenticationResponse;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.security.TokenUtils;
import xmlteam4.Project.services.AuthenticationService;
import xmlteam4.Project.services.UserDetailsServiceImpl;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    //@PreAuthorize("!(hasAuthority('ROLE_AUTHOR') or hasAuthority('ROLE_EDITOR'))")
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDTO) {
        final Authentication authentication;
        System.out.println(loginDTO);
        try {
            authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                            loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenUtils.generateToken(authentication);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    @PreAuthorize("!(hasAuthority('ROLE_AUTHOR') or hasAuthority('ROLE_EDITOR'))")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(authenticationService.register(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
