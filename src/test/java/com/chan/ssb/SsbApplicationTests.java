package com.chan.ssb;

import com.chan.ssb.player.PlayerDTO;
import com.chan.ssb.player.PlayerService;
import com.chan.ssb.team.TeamDTO;
import com.chan.ssb.team.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsbApplicationTests {

	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	@Test
	void testTeamAndPlayerService() {
		TeamDTO team1 = new TeamDTO(0, "Team1", "City1", 1);
		TeamDTO createdTeam = teamService.createTeam(team1);

		TeamDTO team2 = new TeamDTO(0, "Team2", "City2", 2);
		TeamDTO createdTeam2 = teamService.createTeam(team2);

		PlayerDTO player1 = new PlayerDTO(0L, "Player1", 1, 1);
		PlayerDTO player2 = new PlayerDTO(0L, "Player2", 2, 1);

		PlayerDTO player3 = new PlayerDTO(0L, "Player3", 3, 2);
		PlayerDTO player4 = new PlayerDTO(0L, "Player4", 4, 2);

		PlayerDTO createdPlayer1 = playerService.createPlayer(player1);
		PlayerDTO createdPlayer2 = playerService.createPlayer(player2);
		PlayerDTO createdPlayer3 = playerService.createPlayer(player3);
		PlayerDTO createdPlayer4 = playerService.createPlayer(player4);

	}

}
