package pe.edu.upeu.consolidado.consolidado.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.upeu.consolidado.consolidado.entity.Consolidado;
import pe.edu.upeu.consolidado.consolidado.response.ResponseFile;

public interface ConsolidadoService {
	
	Consolidado store(MultipartFile file) throws	IOException;
	Optional<Consolidado> getFile(Long id) throws FileNotFoundException;
	List<ResponseFile> getAllFile();
	void deleteFile(Long id) throws FileNotFoundException;
	
}
