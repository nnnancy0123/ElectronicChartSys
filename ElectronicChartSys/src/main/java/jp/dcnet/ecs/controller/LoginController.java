package jp.dcnet.ecs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ecs.dto.BunsyoJyohoDto;
import jp.dcnet.ecs.form.LoginForm;
import jp.dcnet.ecs.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * 初期表示
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView login(Model model) {

		ModelAndView mav = new ModelAndView("/login");
		
		model.addAttribute("loginx", "佐藤二");
		model.addAttribute("userId", "サトウ二");

		return mav;
	}

	/**
	 * 
	 * @param ishiId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(@RequestParam String ishiId, @RequestParam String password,
			HttpServletRequest request, @Validated @ModelAttribute LoginForm loginForm, BindingResult result,
			Model model) {

		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			model.addAttribute("loginForm", loginForm);
			mav.setViewName("/login");
			return mav;
		}

		//新規セッション指定のときは存在しているセッションを無効にする
		HttpSession session = request.getSession(true);

		boolean res = loginService.findUser(ishiId, password);

		if (res) {

			BunsyoJyohoDto bunsyoJyohoDto = new BunsyoJyohoDto();
			bunsyoJyohoDto.setIshiId(ishiId);
			session.setAttribute("bunsyoJyohoDto", bunsyoJyohoDto);

			mav.setViewName("/main");

			return mav;
		} else {
			// 再入力 login
			mav.setViewName("/login");

			return mav;

		}
	}

}
