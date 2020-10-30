package com.orm;

import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Entity(value = "Dept", noClassnameStored = true)
public class Dept implements Serializable {
    @Id
    private String id;
    @Property
    private Object leader;
    @Embedded
    private Map<String, List<TaskPojo>> execution;
}
