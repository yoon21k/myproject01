package myproject01.domain.entity.visual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VisualEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	@Column(nullable = false)
	private String title;
	@Column
	private String link;
	
	@Column(nullable = false)
	private String fileName;
	@Column(nullable = false)
	private String fileUrl;
	@Column(nullable = false)
	private long fileSize;
}
