package com.middelware.middelware.Services;

import com.middelware.middelware.Models.Theme;
import com.middelware.middelware.Repo.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
@Transactional
public class ThemeService {
    @Autowired
    private ThemeRepo themeRepo;

    public Theme getThemeById(Long id){return themeRepo.findById(id).get();}
    public List<Theme> getAllTheme(){return  themeRepo.findAll();}
    public  Theme getThemeByName(String themeName){return themeRepo.getThemeByName(themeName);}
    public Theme createTheme(String themeName){
        Theme theme=new Theme(null,themeName);
        return themeRepo.save(theme); }
    public  void deleteTheme(Long themeId){themeRepo.deleteById(themeId);}
    public Theme updateTheme(Theme theme){return themeRepo.save(theme);}
}
