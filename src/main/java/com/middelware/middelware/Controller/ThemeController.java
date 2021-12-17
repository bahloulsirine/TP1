package com.middelware.middelware.Controller;


import com.middelware.middelware.Models.Theme;
import com.middelware.middelware.Services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/Theme")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @GetMapping("")//valid
    public List<Theme> getAllThemes(){return themeService.getAllTheme();}

    @GetMapping("/{id}")//valid
    public Theme getThemeById(@PathVariable Long id){return themeService.getThemeById(id);}

    @GetMapping("/byName/{name}")//valid
    public Theme getThemeByName(@PathVariable String name){return themeService.getThemeByName(name);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")//valid
    public Theme createTheme(@RequestBody String themeName) {return themeService.createTheme(themeName);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("")//valid
    public Theme updateTheme(@RequestBody Theme theme) {return themeService.updateTheme(theme);}

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable Long id){themeService.deleteTheme(id);}
}
