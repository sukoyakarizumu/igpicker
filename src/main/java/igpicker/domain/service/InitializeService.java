package igpicker.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import igpicker.domain.mapper.TableCreatorMapper;
import igpicker.domain.mapper.UsersMapper;
import igpicker.domain.model.UserAccount;

/**
 * システムの初期化に関する処理を大なうサービスクラス。
 *
 * @author myk99s
 *
 */
@Service
public class InitializeService {

	@Autowired
	TableCreatorMapper tableMapper;

	@Autowired
	UsersMapper usersMapper;

	/**
	 * テーブルを生成する。
	 */
	@Transactional
	public void createTables() {
		tableMapper.createImages();
		tableMapper.createUsers();
	}

	/**
	 * テーブルを削除する。テーブルに格納済みのデータも併せて削除される。
	 */
	@Transactional
	public void dropTables() {
		tableMapper.dropImages();
		tableMapper.dropUsers();
	}

	/**
	 * サンプルのユーザデータを登録する。
	 */
	@Transactional
	public void addSampleData() {
		UserAccount user1 = new UserAccount();
		user1.setUsername("admin");
		user1.setPassword("admin");
		user1.setAministrator(true);

		usersMapper.insert(user1);
	}
}
