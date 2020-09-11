package com.game.common.Utils;

import com.game.CustomUserDetails;
import com.game.data.entities.Game;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@Component
public class SecurityUtils {
    @Autowired
    private GameRepository gameRepository;
    private static SecurityUtils securityUtils = null;
    private SecurityUtils(){}

    public static SecurityUtils getInstance(){
        if (securityUtils == null)
            securityUtils = new SecurityUtils();
        return securityUtils;
    }
    public boolean isGamePlayer(Set<User> users)
    {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null)
            return false;
        for (User user : users)
            if (user.getId() == userDetails.getUser().getId())
                return true;
        return false;
    }
    public List<String> getRoles()
    {
        List<GrantedAuthority> auAthorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> roles = new ArrayList<>();
        auAthorities.forEach(grantedAuthority -> {
            roles.add(grantedAuthority.getAuthority());
        });
        return roles;
    }
    public boolean isAdmin()
    {
        List<String> roles = getRoles();
        if (roles.contains("ROLE_ADMIN"))
            return true;
        return false;
    }

}
