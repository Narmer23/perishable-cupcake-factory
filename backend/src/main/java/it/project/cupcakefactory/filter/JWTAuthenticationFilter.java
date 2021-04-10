package it.project.cupcakefactory.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.project.cupcakefactory.dto.UserDto;
import it.project.cupcakefactory.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String secret;
    private long expireMillis;
    private ObjectMapper mapper;
    private UserService userService;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String secret, long expireMillis, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.secret = secret;
        this.expireMillis = expireMillis;
        this.mapper = new ObjectMapper();
        this.userService = ctx.getBean(UserService.class);

        setFilterProcessesUrl("/rest/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserDto creds = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireMillis))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        String email = ((User) auth.getPrincipal()).getUsername();
        UserDto user = userService.getUserByEmail(email);
        user.setToken(token);
        res.setContentType("application/json");
        res.getWriter().write(mapper.writeValueAsString(user));
        res.getWriter().flush();
    }
}