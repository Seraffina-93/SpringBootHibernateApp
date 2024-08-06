package com.ingrammicro.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for representing the results of a vote
 */
@Getter
@Setter
public class ResultDTO {
    private String name;
    private double percentage;

    public ResultDTO(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

}
