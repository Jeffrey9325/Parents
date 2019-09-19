package com.everis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Parents class.
 * @author jeffrey
 * @version v1.0
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parents")
public class Parents implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * id.
   */
  @Id
  //@NotNull
   private String idParents;
  /**
   * fullname.
   */
  @NotEmpty(message = "No debe ser vacio")
  private String fullName;
  /**
   * gender.
   */
  @NotEmpty(message = "No debe ser vacio")
  private String gender;
  /**
   * date of birth.
   */
  @JsonProperty
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  @Past
  @NotNull
  private Date dateofBirth;
  /**
   * type of identification document.
   */
  @NotEmpty(message = "No debe ser vacio")
  private String typeDocument;
  /**
   * identification document number.
   */
  @Size(min = 8, max = 8,message = "'numberID' debe tener 8 caracteres")
  private String documentNumber;  
    
}
