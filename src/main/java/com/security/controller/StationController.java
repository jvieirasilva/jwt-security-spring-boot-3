package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.StationDTO;
import com.security.filter.StationFilter;
import com.security.response.PaginatedResponse;
import com.security.service.StationService;

@RestController
@RequestMapping("/stations")
//@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("isAuthenticated()")
public class StationController {
    
	@Autowired
	private StationService stationService;
    
    @PostMapping("/station")
     public PaginatedResponse<StationDTO> getAllStations(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size) {    	
        return stationService.findAllPaginated(page, size);
    }
}