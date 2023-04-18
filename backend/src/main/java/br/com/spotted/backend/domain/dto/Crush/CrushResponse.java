package br.com.spotted.backend.domain.dto.Crush;

import br.com.spotted.backend.domain.entity.Crush;
import br.com.spotted.backend.domain.entity.image.CrushImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrushResponse {

    private Long idCrush;
    private String tituloCrush;

    private String descricaoCrush;

    private String localizacaoCrush;

    private String cursoCrush;

    private String periodoCrush;

    private List<CrushImage> listaImagensCrush;

    private Long idUsuario;

    public CrushResponse(Crush crush) {
        this.idCrush = crush.getIdCrush();
        this.tituloCrush = crush.getTituloCrush();
        this.descricaoCrush = crush.getDescricaoCrush();
        this.localizacaoCrush = crush.getLocalizacaoCrush();
        this.cursoCrush = crush.getCursoCrush();
        this.periodoCrush = crush.getPeriodoCrush();
        this.listaImagensCrush = crush.getListaImagensCrush();
        this.idUsuario = crush.getIdUsuario();
    }
}
