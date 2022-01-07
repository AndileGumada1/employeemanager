package com.andile.gumada.employee.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the entity responsible for storing necessary data for the application
 * default system values
 */

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_config")
public class SystemConfig implements Serializable {

    @EmbeddedId
    private SystemConfigId id;

    @Column(name = "config_key", nullable = false)
    private String configKey;

    @Column(name = "config_value", nullable = false)
    private String configValue;

    @Embeddable
    @Data
    public static class SystemConfigId implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "config_id", nullable = false)

        private Integer configId;
        @Column(name = "system_name")
        private String systemName;
    }
}
