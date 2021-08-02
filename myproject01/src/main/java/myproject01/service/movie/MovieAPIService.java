package myproject01.service.movie;

import java.util.List;

import myproject01.domain.dto.movie.Movie;
import myproject01.domain.dto.movie.naver.Item;

public interface MovieAPIService {

	List<Movie> getDailyBoxOfficeList();

	List<Item> searchNaverMovie(String searchText); 

}
