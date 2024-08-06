package com.ingrammicro.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a resource is not found
 */
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String resourceName;
    private final String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        if (resourceName == null || resourceName.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s", resourceName, fieldName));
        if (resourceName == null || resourceName.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return String.format("ResourceNotFoundException{resourceName='%s', fieldName='%s', fieldValue=%s}",
                resourceName, fieldName, fieldValue);
    }

}
