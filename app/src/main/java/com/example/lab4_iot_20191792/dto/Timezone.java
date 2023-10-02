package com.example.lab4_iot_20191792.dto;

import java.io.Serializable;

public class Timezone implements Serializable {

    private String offset;
    private String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
