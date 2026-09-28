package dev.sorokin.eventmanager.security;

import dev.sorokin.eventmanager.user.Role;
import dev.sorokin.eventmanager.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


public class SecurityUtils {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public static String getCurrentUserLogin() {
        return getCurrentUser().getLogin();
    }
    public static Role getCurrentUserRole(){ return getCurrentUser().getRole();}

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}