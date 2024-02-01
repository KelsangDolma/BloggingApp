package com.takeo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    private Long catid;

    @NotBlank(message = "Category name is mandatory")
    @JsonProperty("name")
    private String cname;

    // Constructors
    public CategoryDto() {
        // Default constructor
    }

    public CategoryDto(Long catid, String cname) {
        this.catid = catid;
        this.cname = cname;
    }

    // Getters and setters
    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
