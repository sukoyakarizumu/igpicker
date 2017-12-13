package igpicker.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 画像の情報を表すクラス。画像の一覧表示等で使用する。
 *
 * @author myk99s
 *
 */
public class ImageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 画像ID */
	private Integer id;

	/** 画像のタイトル */
	private String title;

	/** 画像のコメント */
	private String comment;

	/** 持ち主のユーザID */
	private Integer ownerId;

	/** 持ち主の名前 */
	private String ownerName;

	/** サムネイル画像のデータ(BASE64エンコード) */
	private String thumbnail;

	/** 画像を登録した日 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;

	/**
	 * @return 画像ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            画像ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	 * @return 持ち主のユーザID
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            持ち主のユーザID
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return 持ち主の名前
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName
	 *            持ち主の名前
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return サムネイル画像のデータ(BASE64エンコード)
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbMine
	 *            サムネイル画像のデータ(BASE64エンコード)
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return 画像を登録した日
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            画像を登録した日
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
