package com.security.dto;

import java.time.LocalDateTime;

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
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSerial() {
			return serial;
		}
		public void setSerial(String serial) {
			this.serial = serial;
		}
		
		public StateDTO getStateDTO() {
			return stateDTO;
		}
		public void setStateDTO(StateDTO stateDTO) {
			this.stateDTO = stateDTO;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getInstallationDate() {
			return installationDate;
		}
		public void setInstallationDate(LocalDateTime installationDate) {
			this.installationDate = installationDate;
		}
		public StationDTO getStationDTO() {
			return stationDTO;
		}
		public void setStationDTO(StationDTO stationDTO) {
			this.stationDTO = stationDTO;
		}
		public Boolean getOnLineStatus() {
			return onLineStatus;
		}
		public void setOnLineStatus(Boolean onLineStatus) {
			this.onLineStatus = onLineStatus;
		}
		public UserDTO getUserDto() {
			return userDto;
		}
		public void setUserDto(UserDTO userDto) {
			this.userDto = userDto;
		}
		public EstationModelDTO getEstationModelDTO() {
			return estationModelDTO;
		}
		public void setEstationModelDTO(EstationModelDTO estationModelDTO) {
			this.estationModelDTO = estationModelDTO;
		}
		public String getObservations() {
			return observations;
		}
		public void setObservations(String observations) {
			this.observations = observations;
		}
		public String getSimCardIccid() {
			return simCardIccid;
		}
		public void setSimCardIccid(String simCardIccid) {
			this.simCardIccid = simCardIccid;
		}
		public Boolean getSimCardState() {
			return simCardState;
		}
		public void setSimCardState(Boolean simCardState) {
			this.simCardState = simCardState;
		}
}