//package br.com.spotted.backend.controller;
//
//import br.com.spotted.backend.domain.dto.PaginatedSearchRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController()
//@RequiredArgsConstructor
//public class CrushController {
//
//    private final CrushService crushService;
//
//    //Listar Crush com paginação
//    @GetMapping("api/crush")
//    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {
//
//        ResponseBase<Page<CaronaResponse>> retorno = crushService.pesquisar(searchRequest);
//
//        return ResponseEntity.ok(retorno);
//    }
//
//    //Buscar Crush por Id
//    @GetMapping(value = "api/crush/{idCrush}")
//    public ResponseEntity pesquisarPorId(@PathVariable Long idCarona) {
//
//        ResponseBase<CaronaResponse> retorno = caronaService.pesquisarPorId(idcarona);
//
//        return ResponseEntity.ok(retorno);
//    }
//
//    //Cadastrar Crush
//    @PostMapping("api/crush")
//    public ResponseEntity cadastrar(@Valid @RequestBody CaronaCreateRequest postModel) {
//
//        ResponseBase<CaronaResponse> retorno = caronaService.cadastrar(postModel);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
//    }
//
//    //Deletar Crush por Id
//    @DeleteMapping(value = "api/crush/{idCrush}")
//    public ResponseEntity<CaronaResponse> deletar(@PathVariable Long idCarona) {
//        var carona = caronaService.deletar(idCarona);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carona);
//    }
//
//    //Atualizar Carona
//    @PutMapping(value = "api/caronaAtualizar/{idCarona}")
//    public ResponseEntity<CaronaResponse> atualizarCarona(
//            @PathVariable Long idCarona,
//            @RequestBody @Valid CaronaUpdateRequest caronaUpdateRequest
//    ){
//        var carona = caronaService.atualizarCarona(idCarona, caronaUpdateRequest);
//        return ResponseEntity.ok(carona);
//    }
//}
