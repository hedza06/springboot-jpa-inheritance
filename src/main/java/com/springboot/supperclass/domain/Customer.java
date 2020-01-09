package com.springboot.supperclass.domain;

import com.springboot.supperclass.enumerations.CustomerType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
@DynamicInsert
@DynamicUpdate
public class Customer extends CommonHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CustomerType customerType = CustomerType.INDIVIDUAL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            ", address='" + address + '\'' +
            ", customerType=" + customerType +
            ", validFrom=" + super.getValidFrom() +
            ", validTo=" + super.getValidTo() +
            ", isActive=" + super.getActive() +
            ", sourceIdentifier=" + super.getSourceIdentifier() +
            '}';
    }
}
