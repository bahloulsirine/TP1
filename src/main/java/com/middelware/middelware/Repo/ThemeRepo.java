package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepo extends JpaRepository<Theme,Long> {
    Theme getThemeByName(String name);
}
