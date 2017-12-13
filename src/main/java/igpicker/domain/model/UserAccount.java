package igpicker.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccount implements UserDetails {
	private static final long serialVersionUID = 1L;

	// From users table
	private Integer id;
	private String username;
	private String password;
	private Boolean administrator;

	private Collection<GrantedAuthority> authorities;

	/**
	 * @return the value of users.id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the value for users.id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the value of users.username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the value for users.username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the value of users.password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the value for users.password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the value of users.administrator
	 */
	public Boolean getAdministrator() {
		return administrator;
	}

	/**
	 * @param id
	 *            the value for users.administrator
	 */
	public void setAministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
