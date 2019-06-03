package com.training.validator.beans;

public class StatusCreate {
    private boolean created;
    private String errors;

    public boolean getCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
