package escolaDeTi.backprojeto.controlador;

import escolaDeTi.backprojeto.entidade.Veiculo;
import escolaDeTi.backprojeto.servico.AcessorioServico;
import escolaDeTi.backprojeto.servico.VeiculoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoControlador {

    @Autowired
    private VeiculoServico servico;

    @Autowired
    AcessorioServico acessorioServico;

    @GetMapping
    public ResponseEntity buscarveiculos() {
        return ResponseEntity.ok(servico.BuscarVeiculos());
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo) {
        Veiculo novo = servico.salvar(veiculo);
        if (novo != null) {
            return ResponseEntity.ok(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@RequestBody Veiculo veiculo, @PathVariable Integer id) {
        Veiculo novo = servico.atualizar(veiculo, id);

        if (novo != null) {
            return ResponseEntity.ok(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        servico.deletar(id);
        return ResponseEntity.ok().build();
    }
}
