package igpicker.domain.mapper;

import java.util.List;

import igpicker.domain.model.UserAccount;

/**
 * Usersテーブルに関する処理のマッパー。
 *
 * @author myk99s
 *
 */
public interface UsersMapper {
	List<UserAccount> findAll();
	UserAccount findById(int id);
	UserAccount findByUsername(String username);
	void insert(UserAccount user);
	void update(UserAccount user);
	void deleteById(int id);
}