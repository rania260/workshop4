package com.tekup.workshop4.models.reaquests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductForm {

    @NotBlank(message = "Code is requried")
    private String code;
    @NotEmpty
    @Size(min=2, max=30)
    private String name;
    @NotEmpty
    @DecimalMin(value = "0,1")
    private Double price;
    @NotEmpty
    private int quantity;
    @NotEmpty
    private String image;
    
}