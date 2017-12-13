package igpicker.domain.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import igpicker.domain.mapper.ImagesMapper;
import igpicker.domain.model.ImageData;
import igpicker.domain.model.ImageInfo;

/**
 * 画像に対する処理を行うサービスクラス。
 *
 * @author myk99s
 *
 */
@Service
public class ImageService {
	private static final int THUMBNAIL_MAX_WIDTH = 150;
	private static final int THUMBNAIL_MAX_HEIGHT = 150;

	@Autowired
	ImagesMapper imagesMapper;

	/**
	 * MultipartFileを読込み、BufferedImageを生成する。
	 *
	 * @param file
	 *            読込み元のMultipartFile
	 * @return BufferedImageまたはnull
	 * @throws IOException
	 *             読み込み中にエラーが発生した場合
	 */
	private static BufferedImage convertBufferdImage(MultipartFile file) throws IOException {
		BufferedImage image = null;
		image = ImageIO.read(file.getInputStream());
		return image;
	}

	/**
	 * 画像のサイズを変更する。
	 *
	 * @param src
	 *            変換対象のBufferedImage
	 * @param width
	 *            変更後の画像の幅
	 * @param height
	 *            変更後の画像の高さ
	 * @return BufferdImage
	 * @throws IOException
	 *             読み込み中にエラーが発生した場合
	 */
	private static BufferedImage resizeBufferdImage(BufferedImage src, int width, int height) throws IOException {
		Image tmp = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dst.createGraphics();
		g.drawImage(tmp, 0, 0, null);
		g.dispose();
		return dst;
	}

	/**
	 * BufferedImageの内容をBASE64でエンコードする。
	 *
	 * @param src
	 *            変換対象のBufferedImage
	 * @param format
	 *            画像のフォーマット
	 * @return BASE64でエンコードされた文字列
	 * @throws IOException
	 *             読み込み中にエラーが発生した場合
	 */
	private static String convertBase64(BufferedImage src, String format) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream os = new BufferedOutputStream(bos);
		String ret = null;

		ImageIO.write(src, format, os);
		os.flush();
		os.close();

		byte[] bb = bos.toByteArray();
		ret = Base64.getEncoder().encodeToString(bb);
		return "data:image/" + format + ";base64," + ret;
	}

	/**
	 * BufferedImageの内容を画像形式に変換する。
	 *
	 * @param src
	 *            変換対象のBufferedImage
	 * @param format
	 *            画像のフォーマット
	 * @return 返還後のバイト配列
	 * @throws IOException
	 *             読み込み中にエラーが発生した場合
	 */
	@SuppressWarnings("unused")
	private static byte[] convertByteArray(BufferedImage src, String format) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedOutputStream os = new BufferedOutputStream(bos);

		ImageIO.write(src, format, os);
		os.flush();
		os.close();

		return bos.toByteArray();
	}

	/**
	 * 引数に指定されたBufferedImageのサムネイル画像を作成する。
	 *
	 * @param src
	 *            変換対象のBufferedImage
	 * @return BufferedImage
	 * @throws IOException
	 *             読み込み中にエラーが発生した場合
	 */
	private static BufferedImage convertThumbnail(BufferedImage src) throws IOException {
		double xScale = THUMBNAIL_MAX_WIDTH / (double)src.getWidth();
		double yScale = THUMBNAIL_MAX_HEIGHT / (double)src.getHeight();
		int newWidth = 0;
		int newHeight = 0;
		BufferedImage resizedImage = null;

		if (xScale <= yScale) {
			newWidth = THUMBNAIL_MAX_WIDTH;
			newHeight = (int)(src.getHeight() * xScale);
		} else {
			newWidth = (int)(src.getWidth() * yScale);
			newHeight = THUMBNAIL_MAX_HEIGHT;
		}
		resizedImage = resizeBufferdImage(src, newWidth, newHeight);
		return resizedImage;
	}

	/**
	 * 画像をDBに登録する。
	 *
	 * @param file
	 *            対象の画像ファイルを示すMultipartFile
	 * @param ownerId
	 *            登録したユーザのユーザID
	 * @param title
	 *            画像のタイトル
	 * @param commen
	 *            画像の説明
	 * @return 登録された画像の画像ID
	 */
	@Transactional
	public int registerImage(MultipartFile file, int ownerId, String title, String comment) {
		ImageInfo imageInfo = new ImageInfo();
		ImageData imageData = new ImageData();
		BufferedImage orgImage = null; // 元の画像データ
		BufferedImage thumbImage = null; // サムネイル画像のデータ
		byte[] orgData = null;
		String thumbStr = null; // サムネイル画像のデータ(BASE64エンコード)

		try {
			orgImage = convertBufferdImage(file);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			orgData = file.getBytes();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			thumbImage = convertThumbnail(orgImage);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			thumbStr = convertBase64(thumbImage, "jpg");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		imageInfo.setTitle(title);
		imageInfo.setComment(comment);
		imageInfo.setOwnerId(ownerId);
		imageInfo.setThumbnail(thumbStr);
		imagesMapper.insertImageInfo(imageInfo);

		imageData.setId(imageInfo.getId());
		imageData.setMime(file.getContentType());
		imageData.setWidth(orgImage.getWidth());
		imageData.setHeight(orgImage.getHeight());
		imageData.setFilename(file.getOriginalFilename());
		imageData.setData(orgData);
		imagesMapper.updateImageData(imageData);

		return imageInfo.getId();
	}

	/**
	 * 引数で指定された画像のImageInfoを取得する。
	 *
	 * @param id
	 *            画像ID
	 * @return 指定された画像のImageInfo
	 */
	@Transactional
	public ImageInfo getImageInfo(int id) {
		ImageInfo imageInfo = null;
		imageInfo = imagesMapper.selectImageInfo(id);
		return imageInfo;
	}

	/**
	 * DBに登録されている画像のImageInfoの一覧を取得する。
	 *
	 * @return ImageInfoのリスト
	 */
	@Transactional
	public List<ImageInfo> getImageInfoList() {
		List<ImageInfo> list = null;
		list = imagesMapper.selectImageInfoList();
		return list;
	}

	/**
	 * DBに登録されている画像のImageInfoの一覧を取得する。offsetとlimit
	 *
	 * @param offset
	 *            DBの画像のオフセット
	 * @param limit
	 *            取得する一覧の件数
	 * @return ImageInfoのリスト
	 */
	@Transactional
	public List<ImageInfo> getImageInfoList(int offset, int limit) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("offset", offset);
		param.put("limit", limit);
		List<ImageInfo> list = null;
		list = imagesMapper.selectImageInfoListWithLimit(param);
		return list;
	}

	/**
	 * 引数で指定された画像のImageDataを取得する。
	 *
	 * @param id
	 *            画像ID
	 * @return 指定された画像のImageData
	 */
	@Transactional
	public ImageData getImageData(int id) {
		ImageData imageData = null;
		imageData = imagesMapper.selectImageData(id);
		return imageData;
	}

	/**
	 * 引数で指定された画像を削除する。
	 *
	 * @param id
	 *            画像ID
	 */
	@Transactional
	public void deleteImage(int id) {
		imagesMapper.deleteImage(id);
	}
}
