package com.portfolio.service;

import com.portfolio.dto.AboutDTO;
import com.portfolio.entity.About;

public interface AboutService {

	About getAbout();

	About saveAbout(AboutDTO dto);

}
