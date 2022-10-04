package escolaDeTi.backprojeto.entidade;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(generator="veiculo_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="veiculo_seq",sequenceName="veiculo_id_seq", allocationSize=1)
    private Integer id;

    @NotNull
    private String modelo;
    @NotNull
    private Integer anoFabricacao;
    @NotNull
    private String placa;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "veiculo")
    @JsonManagedReference
    private List<Acessorio> acessorio;
}
