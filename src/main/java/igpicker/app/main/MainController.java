package igpicker.app.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import igpicker.domain.model.ImageInfo;
import igpicker.domain.model.UserAccount;
import igpicker.domain.service.ImageService;

/**
 * メイン画面を表示するクラス。
 *
 * @author myk99s
 *
 */
@Transactional
@Controller
public class MainController {
	@Autowired
	ImageService imagesService;

	/**
	 * 画像一覧画面を表示する。
	 *
	 * @param model
	 *            Model
	 * @return 画像一覧画面を表示
	 */
	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {
		List<ImageInfo> list = imagesService.getImageInfoList();
		ImageUploadForm form = new ImageUploadForm();

		model.addAttribute("list", list);
		model.addAttribute("form", form);

		return "/index";
	}

	/**
	 * 画像アップロード画面を表示する。
	 *
	 * @param model
	 *            Model
	 * @return 画像アップロード画面を表示
	 */
	@GetMapping("/upload")
	public String imageUpload(Model model) {
		ImageUploadForm form = new ImageUploadForm();

		model.addAttribute("form", form);

		return "/upload";
	}

	/**
	 * 画像アップロードの結果を処理する。
	 *
	 * @param model
	 *            Model
	 * @param form
	 *            入力フォームの内容を保持するImageUploadForm
	 * @return 画像一覧画面へリダイレクト
	 */
	@PostMapping("/upload")
	public String imageUploadPost(Model model, @ModelAttribute("form") ImageUploadForm form) {

		MultipartFile file = form.getFile();
		String title = form.getTitle();
		String comment = form.getComment();
		int ownerId = 0;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserAccount) {
			ownerId = ((UserAccount) principal).getId();
		}

		if (form.getFile() == null || form.getFile().isEmpty()) {
			return "redirect:" + "/index";
		}

		imagesService.registerImage(file, ownerId, title, comment);

		return "redirect:" + "/index";
	}

}
