package br.com.julianapinho.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //@NotBlank
    //private String title;

    private String description;

    @NotBlank(message = "Esse campo 'obrigatório")
    private String level;

    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity companyEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;
}