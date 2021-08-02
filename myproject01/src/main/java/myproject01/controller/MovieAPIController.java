package myproject01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import myproject01.service.movie.MovieAPIService;

@RestController   // @Controller + @ResponeBody
public class MovieAPIController {
	
	@Autowired
	MovieAPIService service;

	@GetMapping("/movie/daily")
	public ModelAndView dailyBoxOfficeList(ModelAndView mv) {
		mv.addObject("list", service.getDailyBoxOfficeList());
		
		mv.setViewName("/movie/daily"); 
		
		return mv;
	}
	
	@GetMapping("/movie/naver")
	public ModelAndView searchNaverMovie(String searchText, ModelAndView mv) {
		mv.addObject("list", service.searchNaverMovie(searchText));
		
		mv.setViewName("/movie/naver");
		
		return mv;
	}
}
