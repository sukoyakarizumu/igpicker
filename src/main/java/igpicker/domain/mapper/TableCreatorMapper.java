package igpicker.domain.mapper;

/**
 * テーブルの生成／削除を行うマッパー。
 *
 * @author myk99s
 *
 */
public interface TableCreatorMapper {


	/**
	 * 画像テーブルを生成する。
	 */
	public void createImages();

	/**
	 * 画像テーブルを削除する。
	 */
	public void dropImages();

	/**
	 * ユーザテーブルを生成する。
	 */
	public void createUsers();

	/**
	 * ユーザテーブルを削除する。
	 */
	public void dropUsers();
}
