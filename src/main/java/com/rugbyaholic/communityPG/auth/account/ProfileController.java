package com.rugbyaholic.communityPG.auth.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rugbyaholic.communityPG.auth.AuthenticatedUser;
import com.rugbyaholic.communityPG.common.util.NotificationMessage;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private NotificationMessage notificationMessage;

	@GetMapping("/profile/Profile.html")
	public String onPageRequested(@AuthenticationPrincipal AuthenticatedUser user, Model model) {
		
		ProfileEditForm form = profileService.providePersonalInfo(user.getId());
		model.addAttribute("profileEditForm", form);
		return "profile/Profile.html";
	}
	
	@GetMapping("/profile/UserProfile.html")
	public String onProfileRequested(@RequestParam(value = "id", required = false) Long id, Model model,
			@AuthenticationPrincipal AuthenticatedUser user) {
		
		AuthenticatedUser targetUser = profileService.provideUserInfo(id);
		ProfileEditForm form = profileService.providePersonalInfo(id);
		model.addAttribute("targetUser", targetUser);
		model.addAttribute("profileEditForm", form);
		return "profile/UserProfile.html";
	}

	@PostMapping("/profile/ProfileEdit.do")
	public String onProfileEditRequested(@Valid @ModelAttribute ProfileEditForm profileEditForm,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal AuthenticatedUser user) {
		if (bindingResult.hasErrors()) {
			return "profile/Profile.html";
		}

		try {
			profileService.editProfile(profileEditForm, user);
		} catch (Exception e) {
			// TODO ???????????????????????????
			System.out.println(e.getLocalizedMessage());
			return "profile/Profile.html";
		}

		model.addAttribute("notificationMessage",
				notificationMessage.builder().messageLevel(NotificationMessage.MESSAGE_LEVEL_SUCCESS)
						.messageCode("communityPG.web.message.proc.success").build());
		return "profile/Profile.html";
	}

	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}