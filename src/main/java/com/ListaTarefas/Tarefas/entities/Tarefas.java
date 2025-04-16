package com.ListaTarefas.Tarefas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD

=======
>>>>>>> 3d36c8dd6d95081f32cf5940be964aae8920f48e

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tarefas")
public class Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotNull
    @NotBlank
    @Column (name = "descricao")
    private String descricao;

}
