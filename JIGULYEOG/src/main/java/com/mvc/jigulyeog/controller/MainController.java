package com.mvc.jigulyeog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.jigulyeog.biz.MainBiz;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MainBiz mb;
	
	@RequestMapping("index.do")
	public String startIndexPage(Model model) {
		logger.info("[start index page]");
		
		model.addAttribute("count",mb.countOrg());
		model.addAttribute("pProject",mb.popularProject());
		model.addAttribute("pList",mb.ongoingProject());
		model.addAttribute("cList",mb.ongoingChungwon());
		model.addAttribute("tList",mb.ongoingTogether());
		return "index";
	}

}
