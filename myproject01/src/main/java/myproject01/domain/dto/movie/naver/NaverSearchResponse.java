package myproject01.domain.dto.movie.naver;

import java.util.List;

import lombok.Data;

@Data
public class NaverSearchResponse {

	String lastBuildDate; 
	int total; 
	int start;
	int display; 
	List<Item> items;
}
