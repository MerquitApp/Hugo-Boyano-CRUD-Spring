package one.hgo.crudspring.service;

import one.hgo.crudspring.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UsersService usersService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String githubId = Integer.toString(oauthUser.getAttribute("id"));
        String username = oauthUser.getAttribute("login");

        this.usersService.findOrCreateUser(new UserDetailsDTO(username, null, githubId));

        return new DefaultOAuth2User(null, oauthUser.getAttributes(), "login");
    }
}
