package com.SpringAuthentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SpringAuthentication.model.dto.ResetPasswordDto;
import com.SpringAuthentication.model.dto.UserRegDto;
import com.SpringAuthentication.service.UserApiService;
import com.SpringAuthentication.utils.Utils;

/**
 * @author Ankit Khatri
 */
@Controller
public class AccountController {

	@Autowired
	private Utils utils;
	@Autowired
	private UserApiService userService;

	/* Function to render HomePage / IndexPage */
	@GetMapping(value = { "/", "/home", "/index", "/login", "register" })
	public String showHomePage(Model model) {
		if (utils.getAuthentication() == null) {
			model.addAttribute("user", new UserRegDto());
			return "index.jsp";
		}
		return "redirect:/welcome";
	}

	/* Function to register new user (signup) */
	@PostMapping(value = { "/register", "/signup" })
	public String registerNewUser(@Valid @ModelAttribute("user") UserRegDto user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("regMsg", result.getFieldError().getDefaultMessage().split(":")[0]);
			return "index.jsp";
		} else if (!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("regMsg", "Password does not match..!");
			return "index.jsp";
		}
		try {
			userService.save(user);
			model.addAttribute("user", new UserRegDto());
			model.addAttribute("regMsg", "A verification mail has been sent to your email..!");
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("regMsg", "Account already exists with given email/mobile..!");
		}
		return "index.jsp";
	}

	/* Function to show User DashBoard */
	@GetMapping("/welcome")
	public String showDashBoardPage() {
		return "dashboard.jsp";
	}

	/* Function to verify the user account */
	@GetMapping("/verify/user")
	public String verifyUser(@RequestParam(value = "vkey", required = false) String vkey,
			@RequestParam(value = "email", required = false) String email, RedirectAttributes redirectAttributes) {
		if (email == null || email.trim().isEmpty() || vkey == null || vkey.trim().isEmpty()
				|| utils.getAuthentication() != null || !userService.verifyUser(email, vkey)) {
			redirectAttributes.addFlashAttribute("redirectMsg", "Link is either broken or has been expired..!");
			return "redirect:/brokenLink";
		}
		redirectAttributes.addFlashAttribute("redirectMsg", "Account has been verified. Login to your Account");
		return "redirect:/login";
	}

	/* Function to show Reset Password page */
	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		if (utils.getAuthentication() != null)
			return "redirect:/welcome";
		else
			return "forgotPassword.jsp";
	}

	/* Function to reset password and send verification email */
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam(value = "email", required = false) String email, Model model) {
		if (email == null || email.trim().isEmpty() || !email.contains("@")) {
			model.addAttribute("forgotPwdMsg", "Invalid Email..!");
			return "forgotPassword.jsp";
		}
		userService.sendForgotPasswordEmail(email);
		model.addAttribute("forgotPwdMsg", "Reset Password Link has been sent to the given email id if the account with this email exists.");
		return "forgotPassword.jsp";
	}

	/* Function to show reset password page if the reset-password link is valid */
	@GetMapping("/reset-password")
	public String showResetPasswordPage(@RequestParam(value = "vkey", required = false) String vkey,
			@RequestParam(value = "email", required = false) String email, RedirectAttributes redirectAttributes,
			Model model) {
		if (email == null || email.trim().isEmpty() || vkey == null || vkey.trim().isEmpty()
				|| utils.getAuthentication() != null || !userService.verifyResetPasswordLink(email, vkey)) {
			redirectAttributes.addFlashAttribute("redirectMsg", "Link is either broken or has been expired..!");
			return "redirect:/brokenLink";
		}
		ResetPasswordDto rpDto = new ResetPasswordDto(email, vkey);
		model.addAttribute("rpDto", rpDto);
		return "resetPassword.jsp";
	}

	/* Function to reset Password */
	@PostMapping("/reset-password")
	public String resetPassword(@Valid @ModelAttribute("rpDto") ResetPasswordDto rpDto, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("resetPassMsg", result.getFieldError().getDefaultMessage().split(":")[0]);
			return "resetPassword.jsp";
		} else if (!rpDto.getNewPassword().equals(rpDto.getConfirmNewPassword())) {
			model.addAttribute("resetPassMsg", "Confirm Password does not match..!");
			return "resetPassword.jsp";
		}
		if (!userService.resetPassword(rpDto.getEmail(), rpDto.getRpvkey(), rpDto.getNewPassword())) {
			redirectAttributes.addFlashAttribute("redirectMsg", "Link is either broken or has been expired..!");
			return "redirect:/brokenLink";
		}
		redirectAttributes.addFlashAttribute("redirectMsg", "Password has been reset. Login to your Account");
		return "redirect:/login";

	}

	/* Function to show Broken Page Link */
	@GetMapping("/brokenLink")
	public String showErrorPage() {
		return "access/dummyPage.jsp";
	}
}
