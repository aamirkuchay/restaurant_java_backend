package com.developer.zomato.config;

import com.developer.zomato.entity.Cart;
import com.developer.zomato.entity.Role;
import com.developer.zomato.entity.User;
import com.developer.zomato.repository.CartRepository;
import com.developer.zomato.repository.UserRepository;
import com.developer.zomato.request.SigninRequest;
import com.developer.zomato.response.AuthResponse;
import com.developer.zomato.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>signUp(@RequestBody User user) throws Exception {
        User isEmailExists = userRepository.findByEmail(user.getEmail());
        if(isEmailExists != null){
            throw  new Exception("Email already used with another account");
        }
        User createUser = new User();
        createUser.setEmail(user.getEmail());
        createUser.setFullName(user.getFullName());
        createUser.setRole(user.getRole());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(createUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signIn(@RequestBody SigninRequest signin){
          String username = signin.getEmail();
          String password= signin.getPassword();

          Authentication authentication = authenticate(username,password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setRole(Role.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("invalid username or password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
