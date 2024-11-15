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
import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;
import pe.edu.upeu.consolidado.consolidado.repository.ConsolidadoRepository;
import pe.edu.upeu.consolidado.consolidado.repository.DetallePPPRepository;
import pe.edu.upeu.consolidado.consolidado.response.ResponseFile;

@Service
public class ConsolidadoServiceImpl implements ConsolidadoService {
    @Autowired
    private ConsolidadoRepository consolidadoRepository;
    
    @Autowired
    private DetallePPPRepository detallePPPRepository;

    @Override
    public Consolidado store(MultipartFile file, Long detallePPPId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        Detalle_PPP detallePPP;

        // Verificar si el detallePPPId es proporcionado o no
        if (detallePPPId != null) {
            // Busca el DetallePPP por su ID
            detallePPP = detallePPPRepository.findById(detallePPPId)
                .orElseThrow(() -> new IllegalArgumentException("DetallePPP no encontrado con id: " + detallePPPId));
        } else {
            // Si no se proporciona detallePPPId, crea un nuevo Detalle_PPP automáticamente
            detallePPP = new Detalle_PPP();
            detallePPP = detallePPPRepository.save(detallePPP);
        }

        // Construye y guarda el Consolidado asociado con el DetallePPP
        Consolidado consolidado = Consolidado.builder()
                .nombre(fileName)
                .tipo(file.getContentType())
                .data(file.getBytes())
                .detallePPP(detallePPP) // Asociamos el DetallePPP
                .build();

        return consolidadoRepository.save(consolidado);
    }

    @Override
    public Optional<Consolidado> getFile(Long id) throws FileNotFoundException {
        Optional<Consolidado> file = consolidadoRepository.findById(id);
        return file;
    }

    @Override
    public List<ResponseFile> getAllFile() {
        return consolidadoRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/fileManager/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return ResponseFile.builder()
                    .id(dbFile.getId())
                    .name(dbFile.getNombre())
                    .url(fileDownloadUri)
                    .type(dbFile.getTipo())
                    .size(dbFile.getData().length)
                    .detallePPPId(dbFile.getDetallePPP() != null ? dbFile.getDetallePPP().getId() : null)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteFile(Long id) throws FileNotFoundException {    
        if (!consolidadoRepository.existsById(id)) {
            throw new FileNotFoundException("Archivo no encontrado con el id: " + id);
        }
        consolidadoRepository.deleteById(id);
    }
}
