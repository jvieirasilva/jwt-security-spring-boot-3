package com.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // Certifique-se de que essa anotação está presente
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateDTO {

    private Long id;
    private String description;
    
}