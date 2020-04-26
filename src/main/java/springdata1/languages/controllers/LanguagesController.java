package springdata1.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springdata1.languages.models.Language;
import springdata1.languages.services.LanguageService;

@Controller
public class LanguagesController {
	private final LanguageService languageService;
	
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@RequestMapping("/languages")
	public String index(Model model, @ModelAttribute Language lang) {
		System.out.println("/languages, GET.");
		List<Language> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		return "/languages/index.jsp";
	}
	
	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public String createLanguage(@Valid @ModelAttribute Language lang, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println("/languages, POST. Tienes errores.");
			List<Language> languages = languageService.allLanguages();
			model.addAttribute("languages", languages);
			return "languages/index.jsp";
		}
		else {
			System.out.println("/languages, POST. No tienes errores.");
			languageService.createLanguage(lang);
			return "redirect:/languages";
		}
	}
	
	@RequestMapping("languages/{id}")
	public String showLanguage(@PathVariable Long id, Model model) {
		System.out.println("/languages/{id}, GET.");
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "languages/show.jsp";
	}
	
	@RequestMapping("languages/{id}/edit")
	public String editLanguage(@PathVariable Long id, Model model) {
		System.out.println("/languages/{id}/edit, GET.");
		Language lang = languageService.findLanguage(id);
		model.addAttribute("language", lang);
		return "languages/edit.jsp";
	}
	
	@RequestMapping(value="languages/{id}", method=RequestMethod.PUT)
	public String updateLanguage(@Valid @ModelAttribute Language lang, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("/languages/{id}, PUT. Tienes errores."); 
			return "redirect:/languages/{id}/edit";
		}
		else {
			System.out.println("/languages/{id}, PUT. No tienes errores."); 
			languageService.updateLanguage(lang);
			return "redirect:/languages/{id}/edit";
		}
	}
	
	@RequestMapping(value="languages/{id}", method=RequestMethod.DELETE)
	public String deleteLanguage(@PathVariable Long id) {
		System.out.println("/languages/{id}, DELETE."); 
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}
