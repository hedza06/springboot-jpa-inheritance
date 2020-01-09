package com.springboot.supperclass.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class Product extends CommonHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @NotNull
    @Column(nullable = false, precision = 28, scale = 2)
    private BigDecimal price;

    @Column
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Product{" +
            "id=" + id +
            ", catalogName='" + catalogName + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            ", validFrom=" + super.getValidFrom() +
            ", validTo=" + super.getValidTo() +
            ", isActive=" + super.getActive() +
            ", sourceIdentifier=" + super.getSourceIdentifier() +
        '}';
    }
}
