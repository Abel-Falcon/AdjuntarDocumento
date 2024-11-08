package pe.edu.upeu.consolidado.consolidado.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upeu.consolidado.consolidado.entity.Consolidado;
import pe.edu.upeu.consolidado.consolidado.repository.ConsolidadoRepository;
import pe.edu.upeu.consolidado.consolidado.response.ResponseFile;


@Service
public class ConsolidadoServiceImpl implements ConsolidadoService{
	@Autowired
	private ConsolidadoRepository consolidadoRepository;
	@Override
	public Consolidado store(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Consolidado cosolidado = Consolidado.builder()
				.nombre(fileName)
				.tipo(file.getContentType())
				.data(file.getBytes())
				.build();
		return consolidadoRepository.save(cosolidado);
	}
	@Override
	public Optional<Consolidado> getFile(Long id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Optional<Consolidado> file = consolidadoRepository.findById(id);
		if (file.isPresent()) {
			return file;
		}
		return Optional.empty();
	}
	@Override
	public List<ResponseFile> getAllFile() {
		// TODO Auto-generated method stub
		List<ResponseFile> files = consolidadoRepository.findAll().stream().map(dbFile -> {
			String  fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("api/fileManager/files/")
					.path(dbFile.getId().toString())
					.toUriString();
			return ResponseFile.builder()
					.id(dbFile.getId())
					.name(dbFile.getNombre())
					.url(fileDownloadUri)
					.type(dbFile.getTipo())
					.size(dbFile.getData().length).build();
		}).collect(Collectors.toList());
		return files;
	}
	@Override
	public void deleteFile(Long id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if (!consolidadoRepository.existsById(id)) {
            throw new FileNotFoundException("Archivo no encontrado con el id: " + id);
        }
        consolidadoRepository.deleteById(id);
    }	

}
