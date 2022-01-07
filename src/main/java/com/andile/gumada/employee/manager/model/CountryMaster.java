package com.andile.gumada.employee.manager.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "country_master")
public class CountryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;
    private String countryName;
    private String phoneCode;
    private String countryCode;
    private String currencyCode;
    private String countryCodeAlpha3;
    private String numericCode;
}
