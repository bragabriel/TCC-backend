package br.com.spotted.backend.domain.dto.Crush;

import br.com.spotted.backend.domain.entity.Crush;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private byte[] imagemCrush;

    private Long idUsuario;

    public CrushResponse(Crush crush) {
        this.idCrush = crush.getIdCrush();
        this.tituloCrush = crush.getTituloCrush();
        this.descricaoCrush = crush.getDescricaoCrush();
        this.localizacaoCrush = crush.getLocalizacaoCrush();
        this.cursoCrush = crush.getCursoCrush();
        this.periodoCrush = crush.getPeriodoCrush();
        this.imagemCrush = crush.getImagemCrush();
        this.idUsuario = crush.getIdUsuario();
    }
}
