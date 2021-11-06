package com.mds.back.haerthstoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mds.back.haerthstoon.BattleSettings;

@Controller
public class LandingController {
	@Autowired
	private BattleSettings settings;
	
	@GetMapping("/")
	public String get(Model model) {
		model.addAttribute("settings", settings);
		return "landing";
	}
}