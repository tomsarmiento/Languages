package springdata1.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springdata1.languages.models.Language;
import springdata1.languages.repositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	public List<Language> allLanguages(){
		return languageRepository.findAll();
	}
	
	public void createLanguage(Language lang) {
		languageRepository.save(lang);
	}
	
	public Language findLanguage(Long id) {
		Optional<Language> optionalLang = languageRepository.findById(id);
		if(optionalLang.isPresent())
				return optionalLang.get();
		else
				return null;
	}
	
	public void deleteLanguage(Long id) {
		languageRepository.deleteById(id);
	}
	
	public void updateLanguage(Language lang) {
		languageRepository.save(lang);
	}
}
