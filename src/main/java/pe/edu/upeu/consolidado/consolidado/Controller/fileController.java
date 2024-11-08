package pe.edu.upeu.consolidado.consolidado.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import pe.edu.upeu.consolidado.consolidado.entity.Consolidado;
import pe.edu.upeu.consolidado.consolidado.response.ResponseFile;
import pe.edu.upeu.consolidado.consolidado.response.ResponseMessage;
import pe.edu.upeu.consolidado.consolidado.service.ConsolidadoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/fileManager")
@CrossOrigin(origins = "http://localhost:4200")
public class fileController {
    
    @Autowired
    private ConsolidadoService consolidadoService;
    
    @PostMapping("/upload")
    public ResponseEntity<List<ResponseMessage>> uploadFiles(@RequestParam("file") List<MultipartFile> files) {
        List<ResponseMessage> responses = files.stream().map(file -> {
            try {
                consolidadoService.store(file);
                return new ResponseMessage("Archivo '" + file.getOriginalFilename() + "' subido exitosamente");
            } catch (IOException e) {
                return new ResponseMessage("Error al subir archivo '" + file.getOriginalFilename() + "': " + e.getMessage());
            }
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) throws FileNotFoundException {
        Consolidado consolidado = consolidadoService.getFile(id).orElseThrow(() -> new FileNotFoundException("Archivo no encontrado con el id: " + id));

        // Configura el tipo MIME específico o un valor genérico si no está definido
        String contentType = consolidado.getTipo() != null ? consolidado.getTipo() : "application/octet-stream";

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + consolidado.getNombre() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(consolidado.getData());
    }
    
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = consolidadoService.getAllFile();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
    @DeleteMapping("/files/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable Long id) {
        try {
            consolidadoService.deleteFile(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Archivo eliminado exitosamente"));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }
}
