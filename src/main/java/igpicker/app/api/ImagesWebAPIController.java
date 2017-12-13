package igpicker.app.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import igpicker.domain.model.ImageData;
import igpicker.domain.model.ImageInfo;
import igpicker.domain.service.ImageService;

/**
 * 画像の操作を行うWebAPIに関するクラス
 *
 * @author myk99s
 *
 */
@Transactional
@RestController
@RequestMapping("api/v1/images")
public class ImagesWebAPIController {

	@Autowired
	ImageService imagesService;

	/**
	 * 画像の情報一覧を取得する。
	 *
	 * @param offset
	 * @param limit
	 * @return
	 */
	@GetMapping("")
	public List<ImageInfo> getImageInfoList(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "20") int limit) {

		List<ImageInfo> list = imagesService.getImageInfoList(offset, limit);

		return list;
	}

	/**
	 * 指定された画像の情報を取得する。
	 *
	 * @param imageID
	 * @return
	 */
	@GetMapping("/{imageID}")
	public ImageInfo getImageInfo(@PathVariable int imageID) {
		ImageInfo imageInfo = imagesService.getImageInfo(imageID);
		return imageInfo;
	}

	/**
	 * 指定された画像のファイルを取得する
	 *
	 * @param res
	 * @param imageID
	 * @throws IOException
	 */
	@GetMapping("/{imageID}/file")
	public void getImageFile(HttpServletResponse res, @PathVariable int imageID) throws IOException {
		ImageData imageData = imagesService.getImageData(imageID);

		res.setContentType(imageData.getMime());
		res.setHeader("Content-Disposition", "attachment; filename=" + imageData.getFilename());

		OutputStream os = res.getOutputStream();
		InputStream in = new ByteArrayInputStream(imageData.getData());

		byte[] b = new byte[1024];
		int len;
		while ((len = in.read(b)) != -1) {
			os.write(b, 0, len);
		}
		in.close();
		os.close();
	}

	/**
	 * 指定された画像を削除する。
	 *
	 * @param imageID
	 */
	@DeleteMapping("/{imageID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteImage(@PathVariable int imageID) {
		imagesService.deleteImage(imageID);
	}
}
