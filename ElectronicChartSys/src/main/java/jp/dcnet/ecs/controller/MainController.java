package jp.dcnet.ecs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ecs.dto.BunsyoJyohoDto;

@Controller
public class MainController {

	/**
	 * 初期表示
	 * @return
	 */
	@PostMapping("/main")
	public ModelAndView mainProcess(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("/main");

		HttpSession session = request.getSession(false);
		if(session != null) {

			BunsyoJyohoDto bunsyoJyohoDto = (BunsyoJyohoDto) session.getAttribute("bunsyoJyohoDto");
			System.out.println(bunsyoJyohoDto.getIshiId());
		}
		

		return mav;
	}

}
