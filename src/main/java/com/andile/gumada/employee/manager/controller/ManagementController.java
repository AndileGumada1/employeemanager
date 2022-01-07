package com.andile.gumada.employee.manager.controller;

import com.andile.gumada.employee.manager.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for refreshing System configuration.
 */
@RequiredArgsConstructor
@RestController
public class ManagementController {

    final ConfigService configService;

    /**
     * This is an end-point used to show/retrieve all the system configuration values
     *
     * @return Map objects with a list of system configuration values
     */
    @GetMapping(value = "/emp/mgmt/showConfigs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> listConfig() {
        return configService.getConfig();
    }

    /**
     * This is an end-point for adding new system configuration values
     *
     * @param key         is used to offer the name of the value to be entered
     * @param configValue is the actual data that is inserted alongside the key
     * @return Map objects with a list of all the system configuration values including the newly added
     */
    @PostMapping(value = "/emp/mgmt/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addConfig(@RequestParam String key, @RequestParam String configValue) {
        configService.addConfig(key, configValue);
        return configService.getConfig();
    }

    /**
     * This is an end-point for updating the system configuration values
     *
     * @param key         used to identify the stored key and value pair
     * @param configValue the actual value of the key that is going to be changed
     * @return Map objects with a list of the system configuration values including the newly updated
     */
    @PutMapping(value = "/emp/mgmt/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateConfig(@PathVariable String key, @RequestParam String configValue) {
        configService.updateConfig(key, configValue);
        return configService.getConfig();
    }

    /**
     * This is an end-point used for deleting an existing system configuration value
     *
     * @param key is used for finding the existing system configuration value that will be deleted
     * @return Map objects with a list of the system configuration values excluding the recently deleted
     */
    @DeleteMapping(value = "/emp/mgmt/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> deleteConfig(@PathVariable String key) {
        configService.deleteConfig(key);
        return configService.getConfig();
    }
}
