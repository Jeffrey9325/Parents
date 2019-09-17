package com.everis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parents")
public class Parents implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @NotNull
   String id;

  @NotEmpty(message = "No debe ser vacio!")
  String fullName;
  @NotEmpty(message = "No debe ser vacio!")
  String gender;
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date dateofBirth;
  @NotEmpty(message = "No debe ser vacio!")
  String typeofIdentificationDocument;
  @NotEmpty(message = "No debe ser vacio!")
  String identificationDocumentNumber;  
    
}
