package br.com.devchallenge.empresa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url="http://localhost:8888/auth/",name="authClient")
@Component
public interface AuthClient {

    @PostMapping("/validate/{token}")
    Authentication validarToken(@PathVariable("token") String token);

}
