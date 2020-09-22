package com.game.presentation.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.data.dto.LogDto;
import com.game.data.dto.UserResultDto;
import com.game.service.ILogService;
import com.game.service.IUserResultService;

@RestController
@RequestMapping("/api/user-result")
public class UserResultController {
	@Autowired
	private IUserResultService iUserResultService;

	@GetMapping
	public ResponseEntity<List<UserResultDto>> getUserResults(@RequestParam(value = "userid", required = false) Integer userId,
			@RequestParam(value = "gameId", required = false) Integer gameId) {
		List<UserResultDto> userResults = iUserResultService.findAllByUserIdAndGameId(userId, gameId);
		return ResponseEntity.ok(userResults);
	}

	@PostMapping
	public ResponseEntity<UserResultDto> addUserResult(@RequestBody UserResultDto userResultDto) {
		return ResponseEntity.ok(iUserResultService.add(userResultDto));
	}

	@PutMapping
	public ResponseEntity<UserResultDto> updateUserResult(@RequestBody UserResultDto userResultDto) {
		return ResponseEntity.ok(iUserResultService.update(userResultDto));
	}

	@DeleteMapping
	public ResponseEntity<String> removeLog(@RequestParam(value = "userid", required = false) Integer userId,
			@RequestParam(value = "gameId", required = false) Integer gameId) {
		iUserResultService.delete(userId, gameId);
		return ResponseEntity.ok("User Result is removed");
	}
}
