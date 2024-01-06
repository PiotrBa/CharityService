package com.piotrba.charity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Controller
public class AuthLoginController {

    private static final Logger logger = LoggerFactory.getLogger(AuthLoginController.class);

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
            logger.info("Admin logged in");
            response.sendRedirect("/admin-profile-donations");
        } else {
            logger.info("User logged in");
            response.sendRedirect("/user-homepage");
        }
    }
}
