package pe.edu.upeu.consolidado.consolidado.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFile {
	private Long id;
	private String name;
	private String url;
	private String type;
	private long size;
}
