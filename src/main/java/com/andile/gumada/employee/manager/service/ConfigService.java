package com.andile.gumada.employee.manager.service;

import com.andile.gumada.employee.manager.exception.MissingConfigValueException;
import com.andile.gumada.employee.manager.model.SystemConfig;
import com.andile.gumada.employee.manager.repository.SystemConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This is the Configuration service used to acquire all the configuration information needed within the application
 */
@Service
@RequiredArgsConstructor
public class ConfigService {

    final SystemConfigRepository configRepository;

    /**
     * This method is used to retrieve the configuration values
     *
     * @return a map of configuration values
     */
    public Map<String, String> getConfig() {
        final Map<String, String> systemConfigsMap = new HashMap<>();
        List<SystemConfig> systemConfigs = configRepository.findAll();
        systemConfigs.forEach(config -> systemConfigsMap.put(config.getConfigKey(), config.getConfigValue()));
        return systemConfigsMap;
    }

    /**
     * This method is used to get the configuration values by key
     *
     * @param key represents the text which will be used to retrieve the configuration value
     * @return String object
     */
    public String getConfigByKey(String key) {
        Optional<SystemConfig> config = configRepository.findByConfigKey(key);
        if (!config.isPresent())
            throw new MissingConfigValueException("No such value defined in System configuration for key:" + key);
        return config.get().getConfigValue();
    }

    /**
     * This method will be used to get boolean configuration values by key
     *
     * @param key represent the text which will be used to retrieve the configuration value
     * @return Boolean object
     */
    public Boolean getBooleanConfigByKey(String key) {
        Optional<SystemConfig> config = configRepository.findByConfigKey(key);
        if (!config.isPresent())
            throw new MissingConfigValueException("No such value defined in System configuration for key:" + key);

        return Boolean.valueOf(config.get().getConfigValue());
    }

    /**
     * This method is used to get int configuration values by key
     *
     * @param key represents the text which will be used to retrieve the configuration value
     * @return Integer object
     */
    public Integer getIntConfigByKey(String key) {
        Optional<SystemConfig> config = configRepository.findByConfigKey(key);
        if (!config.isPresent())
            throw new MissingConfigValueException("No such value defined in System configuration for key:" + key);

        try {
            return Integer.parseInt(config.get().getConfigValue());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method adds a new system configuration key and value
     *
     * @param key   text used to identify the value saved in the configuration table
     * @param value text is the actual value which is to be added alongside the key
     */
    public void addConfig(String key, String value) {
        Optional<SystemConfig> optional = configRepository.findByConfigKey(key);
        if (optional.isPresent())
            throw new RuntimeException("System configuration already contains value for key:" + key);

        SystemConfig config = new SystemConfig();
        SystemConfig.SystemConfigId configId = new SystemConfig.SystemConfigId();
        configId.setSystemName(AppConstants.APP);

        config.setId(configId);
        config.setConfigKey(key);
        config.setConfigValue(value);
        configRepository.save(config);

    }

    /**
     * This method updates the system configuration table
     *
     * @param key   is the text used to find the system configuration value in the table
     * @param value is the text which carries the data that is used to update the configuration value
     */
    public void updateConfig(String key, String value) {
        Optional<SystemConfig> optional = configRepository.findByConfigKey(key);
        if (!optional.isPresent())
            throw new MissingConfigValueException("No such value defined in System configuration for key:" + key);

        SystemConfig config = optional.get();
        config.setConfigValue(value);
        configRepository.save(config);
    }

    /**
     * This method is used to delete an existing configuration value in the database
     *
     * @param key is the text used to find the existing system configuration key and value
     */
    public void deleteConfig(String key) {
        Optional<SystemConfig> optional = configRepository.findByConfigKey(key);
        if (!optional.isPresent())
            throw new MissingConfigValueException("No such value defined in System configuration for key:" + key);

        SystemConfig config = optional.get();
        configRepository.delete(config);
    }

}
