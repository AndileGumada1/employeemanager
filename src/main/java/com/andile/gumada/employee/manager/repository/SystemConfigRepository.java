package com.andile.gumada.employee.manager.repository;

import com.andile.gumada.employee.manager.model.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for performing all database operations on System configuration
 */
@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, SystemConfig.SystemConfigId> {

	/**
	 * This method retrieves existing system configuration objects using the System
	 * Name from Id.
	 *
	 * @param systemName it is the text used to find the existing system
	 *                   configuration.
	 *
	 * @return a list of System Configuration objects
	 */

	List<SystemConfig> findByIdSystemName(String systemName);

	/**
	 * This method retrieves existing system configuration objects using
	 * configuration key.
	 *
	 * @param configKey it is the text used to find the existing system configuration.
	 * @return System Configuration objects
	 */

	Optional<SystemConfig> findByConfigKey(String configKey);

}
