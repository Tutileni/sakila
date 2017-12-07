package isep.web.sakila.webapi.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import isep.web.sakila.dao.enums.Profile;

public class Login implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private byte id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public Login() {
	}
	
	public Login(byte b, String email, String password, Set<Profile> profile) {
		super();
		this.id = b;
		this.email = email;
		this.password = password;
		this.authorities = profile.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
	}

	public byte getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(Profile profile) {
		return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
	}
}
