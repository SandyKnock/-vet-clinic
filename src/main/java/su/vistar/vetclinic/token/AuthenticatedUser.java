package su.vistar.vetclinic.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Владислав on 14.02.2017.
 */

public class AuthenticatedUser implements Authentication {

    private String name;
    private String type;

    private boolean authenticated = true;

    AuthenticatedUser(String name,String type){
        this.name = name;
        this.type = type;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getGrantedAuthorities(getType());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private List<GrantedAuthority> getGrantedAuthorities(String type) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(type));
        return authorities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
