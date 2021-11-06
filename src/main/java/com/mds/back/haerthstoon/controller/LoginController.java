package com.mds.back.haerthstoon.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.back.haerthstoon.BattleSettings;
import com.mds.back.haerthstoon.domain.User;
import com.mds.back.haerthstoon.repositery.UserRepositery;

@Controller
public class LoginController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String get(Model model) {
		model.addAttribute("settings", settings);
		return "login";
	}
	
	@Autowired
	private BattleSettings settings;
	@Autowired
	private UserRepositery users;

	@PostMapping("/login")
	public String post(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		model.addAttribute("settings", settings);
		LOGGER.info("Login email={} password ={}", email, password);
		List<User> match = users.findByEmail(email);
		if (match.size() == 1) {
			User user = match.get(0);
			try {
				//L'utilisateur existe mais vérifions le mot de passe !
				// FIXME on utilise MD5
				// TODO Il faudrait utiliser plutôt PBKDF2, BCrypt ou Scrypt
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest = md.digest();
				String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
				if (hash.equals(user.hash)) {
					session.setAttribute("user", user);
					return "redirect:/home";
				}
			} catch (NoSuchAlgorithmException e){
				LOGGER.warn("Impossible de hacher le mot de passe en MD5", e);
				return get(model);
			}
		}
		//Echec !
		LOGGER.info("Login email={} + password -> refused", email);

			return get(model);
	}

}
