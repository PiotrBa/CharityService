package com.piotrba.charity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Controller
public class AuthLoginController {

    @GetMapping("/authLogin")
    public void postLogin(Authentication authentication, HttpServletResponse response) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = false;

        for (GrantedAuthority authority : authorities) {
            if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            response.sendRedirect("/admin-profile-donations");
        } else {
            response.sendRedirect("/user-homepage");
        }
    }
}
