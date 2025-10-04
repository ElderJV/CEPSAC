package com.example.cepsacbackend.Dto.Descuento;

import com.example.cepsacbackend.Enums.TipoDescuento;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DescuentoResponseDTO {
    private Short idDescuento;
    private TipoDescuento tipoDescuento;
    private BigDecimal valor;
    private Boolean vigente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer idUsuario;

    public Short getIdDescuento() { return idDescuento; }
    public void setIdDescuento(Short idDescuento) { this.idDescuento = idDescuento; }

    public TipoDescuento getTipoDescuento() { return tipoDescuento; }
    public void setTipoDescuento(TipoDescuento tipoDescuento) { this.tipoDescuento = tipoDescuento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Boolean getVigente() { return vigente; }
    public void setVigente(Boolean vigente) { this.vigente = vigente; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
}
