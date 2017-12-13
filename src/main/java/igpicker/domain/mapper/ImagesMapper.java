package igpicker.domain.mapper;

import java.util.List;
import java.util.Map;

import igpicker.domain.model.ImageData;
import igpicker.domain.model.ImageInfo;

/**
 * Imagesテーブルに関する処理のマッパー。
 *
 * @author myk99s
 *
 */
public interface ImagesMapper {

	/**
	 * テーブルに登録されている画像の件数を取得する。
	 *
	 * @return 登録済みの画像の件数
	 */
	int countAll();

	/**
	 * 画像を削除する。
	 *
	 * @param id
	 *            削除する画像の画像ID
	 */
	void deleteImage(int id);

	/**
	 * ImageInfoを取得する。
	 *
	 * @param id
	 *            取得する画像の画像ID
	 * @return ImageInfo
	 */
	ImageInfo selectImageInfo(int id);

	/**
	 * ImageInfoの一覧を取得する。
	 *
	 * @return ImageInfoの一覧
	 */
	List<ImageInfo> selectImageInfoList();

	/**
	 * ImageInfoの一覧を取得する。
	 *
	 * @param map
	 *            offset, limitを指定する。
	 * @return ImageInfoの一覧
	 */
	List<ImageInfo> selectImageInfoListWithLimit(Map<String, Integer> map);

	/**
	 * ImageInfoを登録する。登録後、ImageInfo.idに画像IDが格納される。
	 *
	 * @param info
	 *            登録するImageInfo
	 */
	void insertImageInfo(ImageInfo info);

	/**
	 * ImageInfoを更新する。
	 *
	 * @param info
	 *            変更内容が記録されたImageInfo
	 */
	void updateImageInfo(ImageInfo info);

	/**
	 * ImageDataを取得する。
	 *
	 * @param id
	 *            取得する画像の画像ID
	 * @return ImageData
	 */
	ImageData selectImageData(int id);

	/**
	 * ImageDataを更新する。
	 * 画像を新規登録する場合は、insertImageInfoを使用しImageInfoを登録した後、本メソッドで画像データを登録する。
	 *
	 * @param data
	 *            登録するImageData
	 */
	void updateImageData(ImageData data);
}