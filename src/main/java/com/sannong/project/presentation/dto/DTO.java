package com.sannong.project.presentation.dto;


import java.util.HashMap;
import java.util.Map;

/**
 * create DTO class
 * @author william zhang
 */
public class DTO {
    private Map models = new HashMap();

	public DTO(Map models) {
		this.models = models;
	}

    public Map getModels() {
        return models;
    }

    public void setModels(Map models) {
        this.models = models;
    }
}
