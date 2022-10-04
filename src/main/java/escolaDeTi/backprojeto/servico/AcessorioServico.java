package escolaDeTi.backprojeto.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import escolaDeTi.backprojeto.entidade.Acessorio;
import escolaDeTi.backprojeto.repositorio.AcessorioRepositorio;

@Service
public class AcessorioServico {

    @Autowired
    private AcessorioRepositorio repositorio;
    

    public List<Acessorio> BuscarAcessorios(){
        List<Acessorio> acessorios = new ArrayList<>();
        acessorios = Optional.ofNullable(repositorio.findAll()).get();
        return acessorios;
    }

    public Acessorio salvar(Acessorio novo){
        try {
            return repositorio.save(novo);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel salvar Acessorio, verifique os campos obrigatorios, Error: " + e);
        }
    }

    public Acessorio atualizar(Acessorio novo, Integer id){
        try{
            if(novo.getId() == id){
                if(repositorio.findById(id).isPresent()){
                    return repositorio.save(novo);
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Acessorio não encontrado com id: " + id);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id Acessorio não confere com o id da requisição");

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error:" + e);
        }

    }

    public void deletar(Integer id){
        try{
            Acessorio Acessorio = repositorio.findById(id).get();
            repositorio.delete(Acessorio);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Acessorio não encontrado, Error: " + e);
        }
    }
}
