package com.security.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.security.model.Role;
import com.security.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // Certifique-se de que essa anotação está presente
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationDTO  {
	 private Long id;
	    private String serial;
	    private Boolean onLineStatus;
	    private StateDTO stateDTO;
	    private UserDTO userDto;
	    private EstationModelDTO estationModelDTO;
	    private LocalDateTime createdAt;
	    private LocalDateTime installationDate;
	    private String observations;
	    
	    private StationDTO stationDTO;
	    private String simCardIccid;
	    private Boolean simCardState;
	 
}