package escolaDeTi.backprojeto.servico;

import escolaDeTi.backprojeto.entidade.Veiculo;
import escolaDeTi.backprojeto.repositorio.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServico {

    @Autowired
    private VeiculoRepositorio repositorio;

    public Optional<List<Veiculo>> BuscarVeiculos(){
         return  Optional.ofNullable(repositorio.findAll());
    }

    public Veiculo salvar(Veiculo novo){
        try {
            return repositorio.save(novo);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel salvar Veiculo, verifique os campos obrigatorios, Error: " + e);
        }
    }

    public Veiculo atualizar(Veiculo novo, Integer id){
        try{
            if(novo.getId() == id){
                if(repositorio.findById(id).isPresent()){
                   return repositorio.save(novo);
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Veiculo não encontrado com id: " + id);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id veiculo não confere com o id da requisição");

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error:" + e);
        }

    }

    public void deletar(Integer id){
        try{
            Veiculo veiculo = repositorio.findById(id).get();
            repositorio.delete(veiculo);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado, Error: " + e);
        }
    }
}
