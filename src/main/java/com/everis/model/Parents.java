package com.everis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
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
@JsonPropertyOrder({"id", "fullName", "gender",
    "dateofBirth", "typeDocument", "documentNumber"})
public class Parents implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * id.
   */
  @Id
   private String id;
  /**
   * full name.
   */
  @NotEmpty(message = "should not be empty")
  private String fullName;
  /**
   * gender.
   */
  @NotEmpty(message = "should not be empty")
  private String gender;
  /**
   * date of birth.
   */
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  @NotNull
  private LocalDate dateofBirth;
  /**
   * type of identification document.
   */
  @NotEmpty(message = "should not be empty")
  private String typeDocument;
  /**
   * identification document number.
   */
  @Size(min = 8, max = 8, message = "must contain 8 characters")
  private String documentNumber;  
  /**
   * student identification.
   */
  @NotEmpty(message = "should not be empty")
  private String idStudent;
  /**
   * family identification.
   */
  @NotEmpty(message = "should not be empty")
  private String idFamily;
    
}
