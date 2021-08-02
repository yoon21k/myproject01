package myproject01.service.visual;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import myproject01.domain.dto.visual.VisualDto;

public interface VisualService {

	void uploadAndSave(VisualDto dto, MultipartFile file);

	void getList(Model model);

	String uploadByTemp(MultipartFile tempFile);

	void uploadAndSave2(VisualDto dto, MultipartFile file);

	void delete(long no);


}
