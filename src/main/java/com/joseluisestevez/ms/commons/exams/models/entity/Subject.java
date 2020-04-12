package com.joseluisestevez.ms.commons.exams.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // limit cascading relationships with @JsonIgnoreProperties
    @JsonIgnoreProperties(value = { "children" }, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject parent;

    // limit cascading relationships with @JsonIgnoreProperties
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Subject> children = new ArrayList<>();
}
