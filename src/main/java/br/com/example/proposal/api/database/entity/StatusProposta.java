package br.com.example.proposal.api.database.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "STATUS_PROPOSTA")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusProposta {
    @Id
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;


}
