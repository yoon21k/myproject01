package myproject01.domain.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PageInfo {
	
	private int from;
	private int to;
	private int size;
	private int psize;
	private int page;
	private int tot;
	private long totE;


}
