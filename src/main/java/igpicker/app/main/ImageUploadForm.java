package igpicker.app.main;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 画像アップロード用のフォーム
 *
 * @author myk99s
 *
 */
public class ImageUploadForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 画像のタイトル
	 */
	private String title;

	/**
	 * 画像のコメント
	 */
	private String comment;

	/**
	 * アップロードされたファイル
	 */
	private MultipartFile file;

	/**
	 * @return 画像のタイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            画像のタイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 画像のコメント
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            画像のコメント
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return アップロードされたファイル
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            アップロードされたファイル
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
