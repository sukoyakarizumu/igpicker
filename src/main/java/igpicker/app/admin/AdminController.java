package igpicker.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import igpicker.domain.service.InitializeService;

/**
 * 管理画面を表示するクラス。
 *
 * @author myk99s
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	/**
	 * ユーザアカウントを操作するためのサービスクラス。
	 */
	@Autowired
	UserDetailsService userAccountService;

	/**
	 * システムの初期化を行うためのサービスクラス。
	 */
	@Autowired
	InitializeService initializeService;

	/**
	 * 管理画面を表示する。
	 *
	 * @param model
	 *            - Model
	 * @return 管理画面を表示
	 */
	@GetMapping("")
	public String admin(Model model) {
		UserAccountForm form = new UserAccountForm();
		model.addAttribute("form", form);
		return "/admin";
	}

	/**
	 * テーブルの初期化を行う。
	 *
	 * @return 管理画面へリダイレクトする。
	 */
	@GetMapping("initialize")
	public String createTables() {
		initializeService.dropTables();
		initializeService.createTables();
		initializeService.addSampleData();
		return "redirect:/admin";
	}
}
