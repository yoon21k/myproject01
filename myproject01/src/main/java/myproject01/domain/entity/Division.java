package myproject01.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Division {
	
	exchange("교환반품"),   // 0
	order("주문결제"),   // 1
	delivery("배송"),   // 2
	etc("기타");   // 3
	
	final String title;
}
