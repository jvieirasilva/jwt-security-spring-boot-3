package com.security.service;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.security.dto.EstationModelDTO;
import com.security.dto.StateDTO;
import com.security.dto.StationDTO;
import com.security.dto.UserDTO;
import com.security.model.Station;
import com.security.repository.StationRepository;
import com.security.response.PaginatedResponse;

@Service
public class StationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StationService.class);

	
	@Autowired
    private StationRepository stationRepository;
	

	
	
	public PaginatedResponse<StationDTO> findAllPaginated(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<Station> stationPage = stationRepository.findAllWithFilters(pageable);
	    
	    return new PaginatedResponse<>(
	            stationPage.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
	            stationPage.getTotalElements(),
	            page,
	            size
	    );
	}
	
	


	private StationDTO toDTO(Station station) {
		LOGGER.trace("Converting Station entity to DTO - ID: {}, Serial: {}", 
	            station.getId(), station.getSerial());
		try {
			StationDTO dto = new StationDTO();
			dto.setId(station.getId());
			dto.setSerial(station.getSerial().toUpperCase());
	
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId(station.getState().getId());
			stateDTO.setDescription(station.getState().getDescription());
			dto.setStateDTO(stateDTO);
	
			dto.setCreatedAt(station.getCreatedAt());
			dto.setInstallationDate(station.getInstallationDate());
			dto.setOnLineStatus(station.getOnLineStatus());
	
			UserDTO userDTO = new UserDTO();
			userDTO.setId(station.getUser().getId());
			userDTO.setFullName(station.getUser().getFullName());
			userDTO.setEmail(station.getUser().getEmail());
			dto.setUserDto(userDTO);
			dto.setSimCardState(station.getSimCardState());
			
			if(station.getEstationModel() != null && station.getEstationModel().getId() >0) {
				EstationModelDTO estationModelDTO = new EstationModelDTO();
				estationModelDTO.setId(station.getEstationModel().getId());
				estationModelDTO.setName(station.getEstationModel().getName());
				dto.setEstationModelDTO(estationModelDTO);
			}else {
				dto.setEstationModelDTO(null);
			}
			dto.setSimCardIccid(station.getSimCardIccid());
	
			return dto;
			
		 } catch (Exception e) {
	            LOGGER.error("Error converting station to DTO - ID {}: ", station.getId(), e);
	            throw e;
	        }		
	}
}