package igpicker.domain.model;

import java.io.Serializable;

/**
 * 画像データを表すクラス。
 *
 * @author myk99s
 *
 */
public class ImageData implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 画像ID */
	private Integer id;

	/** 画像のMIME */
	private String mime;

	/** 画像の幅 */
	private Integer width;

	/** 画像の高さ */
	private Integer height;

	/** ファイル名(アップロード時の名前) */
	private String filename;

	/** 画像データ(アプロードされたファイル) */
	private byte[] data;

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
	 * @return 画像のMIME
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * @param mime
	 *            画像のMIME
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * @return 画像の幅
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            画像の幅
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return 画像の幅
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            画像の幅
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return ファイル名(アップロード時の名前)
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            ファイル名(アップロード時の名前)
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return 画像データ(アプロードされたファイル)
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            画像データ(アプロードされたファイル)
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}
