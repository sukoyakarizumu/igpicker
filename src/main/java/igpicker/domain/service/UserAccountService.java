package igpicker.domain.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import igpicker.domain.mapper.UsersMapper;
import igpicker.domain.model.UserAccount;


/**
 * ユーザアカウントに関する処理を集めたサービスクラス。
 *
 * @author myk99s
 *
 */
@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	UsersMapper usersMapper;

	/* (非 Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = null;

		user = usersMapper.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found.");
		}
		setAuthorities(user);
		return user;
	}

	/**
	 * @param id
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Transactional(readOnly = true)
	public UserAccount loadUserById(int id) throws UsernameNotFoundException {
		UserAccount user = null;

		user = usersMapper.findById(id);
		if (user == null) {
			throw new UsernameNotFoundException("user not found.");
		}
		//setAuthorities(user);
		return user;
	}

	/**
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Transactional(readOnly = true)
	public List<UserAccount> loadUserList() throws UsernameNotFoundException {
		List<UserAccount> list = null;

		list = usersMapper.findAll();
		if (list == null) {
			throw new UsernameNotFoundException("user not found.");
		}
		//for(UserAccount user: list) {
		//	setAuthorities(user);
		//}

		return list;
	}

	private void setAuthorities(UserAccount user) {
		Collection<GrantedAuthority> authorities = null;
		if (user.getAdministrator()) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		} else {
			authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		}
		user.setAuthorities(authorities);
	}

}

