package igpicker.app.admin;

import java.io.Serializable;

/**
 * ユーザ情報変更に使用するフォーム
 *
 * @author myk99s
 *
 */
public class UserAccountForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 新しいユーザ名
	 */
	private String newUsername;

	/**
	 * 新しいパスワード
	 */
	private String newPassword;

	/**
	 * 新しいパスワード(確認用)
	 */
	private String newPasswordConfirmation;

	/**
	 * 現在のパスワード
	 */
	private String password;

	/**
	 * @return 新しいユーザ名
	 */
	public String getNewUsername() {
		return newUsername;
	}

	/**
	 * @param newUsername
	 *            新しいユーザ名
	 */
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	/**
	 * @return 新しいパスワード
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            新しいパスワード
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return 新しいパスワード(確認用)
	 */
	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	/**
	 * @param newPasswordConfirmation
	 *            新しいパスワード(確認用)
	 */
	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	/**
	 * @return 現在のパスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            現在のパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
