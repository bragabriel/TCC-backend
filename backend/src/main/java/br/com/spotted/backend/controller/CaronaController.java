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
//public class CaronaController {
//
//    private final CaronaService caronaService;
//
//    //Listar Carona com paginação
//    @GetMapping("api/carona")
//    public ResponseEntity pesquisar(PaginatedSearchRequest searchRequest) {
//
//        ResponseBase<Page<CaronaResponse>> retorno = caronaService.pesquisar(searchRequest);
//
//        return ResponseEntity.ok(retorno);
//    }
//
//    //Buscar Carona por Id
//    @GetMapping(value = "api/carona/{idCarona}")
//    public ResponseEntity pesquisarPorId(@PathVariable Long idCarona) {
//
//        ResponseBase<CaronaResponse> retorno = caronaService.pesquisarPorId(idcarona);
//
//        return ResponseEntity.ok(retorno);
//    }
//
//    //Cadastrar Carona
//    @PostMapping("api/carona")
//    public ResponseEntity cadastrar(@Valid @RequestBody CaronaCreateRequest postModel) {
//
//        ResponseBase<CaronaResponse> retorno = caronaService.cadastrar(postModel);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
//    }
//
//    //Deletar Carona por Id
//    @DeleteMapping(value = "api/carona/{idCarona}")
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
