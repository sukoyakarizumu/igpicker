package igpicker.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ユーザ認証を行うクラス。
 *
 * @author myk99s
 *
 */
/**
 * @author myk99s
 *
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示する。
	 *
	 * @return ログイン画面を表示
	 */
	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	/**
	 * ログイン画面(エラー表示あり)を表示する。
	 *
	 * @return ログイン画面を表示
	 */
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", true);
		return "login";
	}
}
