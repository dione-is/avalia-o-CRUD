package escolaDeTi.backprojeto.entidade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Acessorio {

    @Id
    @GeneratedValue(generator = "acessorio_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="acessorio_seq",sequenceName="acessorio_id_seq", allocationSize=1)
    private Integer id;

    @NotNull
    private String nome;

    @ManyToOne
    @JsonBackReference
    private Veiculo veiculo;
}
