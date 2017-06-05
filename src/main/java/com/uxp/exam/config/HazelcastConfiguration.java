package com.uxp.exam.config;

import org.springframework.cache.CacheManager;

import com.hazelcast.config.Config;

//@Configuration
public class HazelcastConfiguration {
	//@Bean
	public Config hazelcastConfig() {
		return new Config();
	}
	
	//@Bean
	public CacheManager cacheManager() {
		return this.cacheManager();
	}
}