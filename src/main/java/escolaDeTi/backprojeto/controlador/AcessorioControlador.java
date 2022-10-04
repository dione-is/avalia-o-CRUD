package escolaDeTi.backprojeto.controlador;

import escolaDeTi.backprojeto.entidade.Acessorio;
import escolaDeTi.backprojeto.servico.AcessorioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acessorio")
public class AcessorioControlador {
    @Autowired
    private AcessorioServico servico;

    @GetMapping
    public ResponseEntity<List<Acessorio>> buscarAcessorios(){
        return ResponseEntity.ok(servico.BuscarAcessorios());
    }

    @PostMapping
    public ResponseEntity<Acessorio> salvar(@RequestBody Acessorio Acessorio){
        Acessorio novo = servico.salvar(Acessorio);

        if(novo != null){
            return ResponseEntity.ok(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acessorio> atualizar(@RequestBody Acessorio Acessorio, @PathVariable Integer id){
        Acessorio novo = servico.atualizar(Acessorio, id);

        if(novo != null){
            return ResponseEntity.ok(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        servico.deletar(id);
        return ResponseEntity.ok().build();
    }
}
