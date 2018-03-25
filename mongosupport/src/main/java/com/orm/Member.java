package com.orm;

import lombok.Data;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;

@Data
public class Member implements Serializable {
    @Property("name")
    private String name;
    @Property("workAge")
    private String workAge;
}