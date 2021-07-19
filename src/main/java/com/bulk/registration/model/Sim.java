package com.bulk.registration.model;

import com.bulk.registration.constant.Gender;
import com.bulk.registration.constant.SimTypes;
import com.bulk.registration.validator.ValidateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Sim implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("MSISDN")
    @NotEmpty(message = "Invalid MSISDN")
    @Pattern(regexp = "^(00|\\+)[1-9]{1}([0-9][\\s]*){9,16}$", message = "MSISDN should follow International Format e.g; +971523331545")
    private String phoneNumber;

    @JsonProperty("SIM_TYPE")
    @ValidateEnum(targetClassType = SimTypes.class, message = "Invalid SimType. Allowed POSTPAID or PREPAID")
    private String simType;

    @JsonProperty("NAME")
    @NotEmpty(message = "MSISDN is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name should not contain any special characters")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("DATE_OF_BIRTH")
    @NotEmpty(message = "Invalid Date of Birth. Format should be yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("GENDER")
    @ValidateEnum(targetClassType = Gender.class, message = "Invalid Gender. Allowed F or M")
    private String gender;

    @NotEmpty(message = "Invalid Address")
    @Size(min = 20)
    @JsonProperty("ADDRESS")
    private String address;

    @NotEmpty(message = "Invalid ID Number")
    @JsonProperty("ID_NUMBER")
    private Long idNumber;

    @Override
    public String toString() {
        return "MSISDN: ".concat(this.phoneNumber).concat(",\t")
                .concat("SIM_TYPE: ").concat(this.simType).concat(",\t")
                .concat("NAME: ").concat(this.name).concat(",\t")
                .concat("ID_NUMBER: ").concat(this.idNumber.toString())
                ;
    }
}