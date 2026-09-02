package com.medicamento.controle.medicamentos;


import com.medicamento.controle.medicamentos.enums.CLASSIFICAO;
import com.medicamento.controle.medicamentos.enums.TIPO;
import com.medicamento.controle.validade.ValidadeEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_medicamento")
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ean;
    private String descricao;
    private String fabricante;

    @Enumerated(EnumType.STRING)
    private TIPO tipo;        // Ex: Ético, Similar, Genérico

    @Enumerated(EnumType.STRING)
    private CLASSIFICAO classificacao; // Ex: Controlado, MIP

    private String principio_ativo;

    private String registro_anvisa;

    /** Lista da Portaria 344: A1, A2, A3, B1, B2, C1... null se não for controlado especial */
    @Column(name = "lista_controle", length = 5)
    private String listaControle;

    @OneToMany(mappedBy = "medicamento",fetch = FetchType.LAZY)
    private List<ValidadeEntity> listaValidade = new ArrayList<>();

    public List<ValidadeEntity> getListaValidade() {
        return listaValidade;
    }

    public void setListaValidade(List<ValidadeEntity> listaValidade) {
        this.listaValidade = listaValidade;
    }

    public String getPrincipio_ativo() {
        return principio_ativo;
    }

    public void setPrincipio_ativo(String principio_ativo) {
        this.principio_ativo = principio_ativo;
    }

    public String getRegistro_anvisa() {
        return registro_anvisa;
    }

    public void setRegistro_anvisa(String registro_anvisa) {
        this.registro_anvisa = registro_anvisa;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public CLASSIFICAO getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(CLASSIFICAO classificacao) {
        this.classificacao = classificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

