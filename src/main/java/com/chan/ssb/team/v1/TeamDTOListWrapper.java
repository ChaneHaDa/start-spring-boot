package com.chan.ssb.team.v1;

import jakarta.validation.Valid;

import java.util.List;

public class TeamDTOListWrapper {
    @Valid
    private List<TeamDTO> teamDTOList;

    public TeamDTOListWrapper() {
    }

    public TeamDTOListWrapper(List<TeamDTO> teamDTOList) {
        this.teamDTOList = teamDTOList;
    }

    public List<TeamDTO> getTeamDTOList() {
        return teamDTOList;
    }

    public void setTeamDTOList(List<TeamDTO> teamDTOList) {
        this.teamDTOList = teamDTOList;
    }
}
