package com.ssafy.devlog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.ProjectStack;
import com.ssafy.devlog.dto.UserStack;
import com.ssafy.devlog.service.ProjectStackService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/projectstack")
public class ProjectStackController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectStackController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ProjectStackService projectStackService;

	@ApiOperation(value = "프로젝트의 모든 기술스택을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_project}")
	public ResponseEntity<List<ProjectStack>> selectAllProjectStack(@PathVariable int seq_post_project)
			throws Exception {
		logger.debug("selectAllProjectStack - 호출");
		return new ResponseEntity<List<ProjectStack>>(projectStackService.selectAllProjectStack(seq_post_project),
				HttpStatus.OK);
	}

	@ApiOperation(value = "프로젝트에 기술스택을 추가한다. //중복 입력시 401", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertProjectStack(@RequestBody ProjectStack projectStack) throws Exception {
		logger.debug("inserProjectStack - 호출");
		ProjectStack check = projectStackService.selectProjectStackByProjectAndStack(projectStack);
		if (check != null)
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		else if (projectStackService.insertProjectStack(projectStack) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "프로젝트의 기술스택을 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deleteProjectStack(@PathVariable int seq) throws Exception {
		logger.debug("deleteProjectStack - 호출");
		if (projectStackService.deleteProjectStack(seq) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
