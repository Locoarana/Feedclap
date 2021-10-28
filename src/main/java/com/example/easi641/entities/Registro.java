package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "developer_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "developer_reviewers_fk"))
	private Developer developer;

	@ManyToOne
	@JoinColumn(name = "reviewers_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "reviewers_developer_fk"))
	private Reviewer reviewer;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "monto", nullable = false)
	private Double monto;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime localDateTime;

}
