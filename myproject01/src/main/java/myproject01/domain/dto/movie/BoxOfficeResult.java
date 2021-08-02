package myproject01.domain.dto.movie;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeResult {
	
	String boxofficeType;
	String showRange;
	List<Movie> dailyBoxOfficeList;
}
