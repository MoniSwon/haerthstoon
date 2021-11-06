package com.mds.back.haerthstoon.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mds.back.haerthstoon.BattleSettings;
import com.mds.back.haerthstoon.domain.User;

@Controller
public class HomeController {

	@Autowired
	private BattleSettings settings;

	@GetMapping("/home")
	public String get(@SessionAttribute User user, Model model) {
		model.addAttribute("settings", settings);
		model.addAttribute("stars", Collections.nCopies(user.xp, "star"));
		return "home";
	}
}
