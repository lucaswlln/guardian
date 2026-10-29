package com.guardian.backend.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @PostMapping
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        request.getSession().invalidate();

        jakarta.servlet.http.Cookie cookie =
                new jakarta.servlet.http.Cookie("JSESSIONID", "");

        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        // cookie.setSecure(request.isSecure());

        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }
}