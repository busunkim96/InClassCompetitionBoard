package com.ooad.InClassComp.ui;

import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ooad.InClassComp.doa.CompetitionDAO;
import com.ooad.InClassComp.doa.SubmissionDAO;
import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.Competition;
import com.ooad.InClassComp.model.Submission;
import com.ooad.InClassComp.model.TestCriteria;
import com.ooad.InClassComp.model.UploadSubmission;
import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.model.UserType;
import com.ooad.InClassComp.ui.model.CompetitionResponse;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CompetitionController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	CompetitionDAO competitionDAO;

	@Autowired
	SubmissionDAO submissionDAO;



	@RequestMapping(value="/competition/create")
	@ResponseBody
	public ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity>
	createCompetition(String userName, String competitionName,
			String endDate, String className,
			String description) {
		List<User> users = null;
		try {
			users = userDAO.findByUserName(userName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		DateFormat format = new SimpleDateFormat("MM-DD-yyyy", Locale.ENGLISH);
		Date endD = null;
		try {
			if(endDate != null) {
				endD= format.parse(endDate);
			}
		}catch(ParseException e) {
			e.printStackTrace();
		}
		try {
			if(users != null && users.size() == 1 && 
					users.get(0).getAccepted() == Boolean.TRUE &&
					users.get(0).getType().equals(UserType.FACULTY.ordinal())) {
				Competition competition = new Competition();
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				competition.setCreatedBy(users.get(0));
				competition.setCreatedDate(date);
				competition.setEndDate(endD);
				competition.setCompetitionName(competitionName);
				competition.setDescription(description);
				competition.setClassName(className);
				competition = competitionDAO.save(competition);
				com.ooad.InClassComp.ui.model.ResponseEntity response  = new com.ooad.InClassComp.ui.model.ResponseEntity();
				response.setStatus(competition.getId().toString());
				response.setMessage("COMPETITION CREATED");
				ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity> responseEntity = 
						new  ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity>(response,HttpStatus.OK);
				return responseEntity ;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity>(HttpStatus.BAD_REQUEST);
	}


	@RequestMapping(value="/competition/getAll")
	@ResponseBody
	public List<CompetitionResponse> getAllCompetitions(HttpServletResponse response) {
		List<Competition> competitions = null;
		try {
			competitions = (List<Competition>) competitionDAO.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		List<CompetitionResponse> competitionResponses = new ArrayList<CompetitionResponse>();
		for(Competition comp : competitions) {
			competitionResponses.add(new CompetitionResponse(comp));
		}
		response.setHeader("Access-Control-Allow-Origin", "*");

		return competitionResponses;
	}

	@RequestMapping(value="/competition/getByName/")
	@ResponseBody
	public List<CompetitionResponse> getByCompetitionName(String competitionName) {
		List<Competition> competitions = null;
		try {
			competitions = (List<Competition>) competitionDAO.findByCompetitionNameLikeIgnoreCase(competitionName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		List<CompetitionResponse> competitionResponses = new ArrayList<CompetitionResponse>();
		for(Competition comp : competitions) {
			competitionResponses.add(new CompetitionResponse(comp));
		}
		return competitionResponses;
	}

	@RequestMapping(value="/competition/join/")
	@ResponseBody
	public Long joinCompetition(Long userId,Long compId) {
		Optional<Competition> competitions = null;
		Optional<User> users = null;
		try {
			users = (Optional<User>)userDAO.findById(userId);
			competitions = (Optional<Competition> )competitionDAO.findById(compId);

		} catch(Exception e) {
			e.printStackTrace();
		}
		User user = null;
		Competition competition = null;
		if(users.isPresent() && competitions.isPresent()) {
			user = users.get();
			competition = competitions.get(); 
		}		
		if(user.getAccepted() != Boolean.TRUE || !user.getType().equals(UserType.STUDENT.ordinal())) {
			return Long.valueOf(-1);
		}
		try {
			Set<Submission> submissions = competition.getCompetitionUserSubmissions();
			Submission submission = new Submission();
			submission.setUser(user);
			submission.setCompetition(competition);
			submissions.add(submission);
			competition.setCompetitionUserSubmissions(submissions);
			competitionDAO.save(competition);
			return competition.getId();
		} catch(Exception e) {
			e.printStackTrace();
			return Long.valueOf(-1);
		}
	}

	@RequestMapping(value="/competition/updateDescription/")
	@ResponseBody
	public ResponseEntity<String> updateDescription(Long compId,String description) {
		Optional<Competition> competitions = null;
		try {
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(!competitions.isPresent()) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		competition = competitions.get(); 
		try {
			competition.setDescription(description);
			competitionDAO.save(competition);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="/competition/updateEndDate/")
	@ResponseBody
	public ResponseEntity<String> updateEndDate(Long compId,String endDate) {
		Optional<Competition> competitions = null;
		try {
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(!competitions.isPresent()) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		competition = competitions.get(); 
		try {
			DateFormat format = new SimpleDateFormat("MM-DD-yyyy", Locale.ENGLISH);
			Date endD = null;
			try {
				if(endDate != null) {
					endD= format.parse(endDate);
				}
			}catch(ParseException e) {
				e.printStackTrace();
			}
			competition.setEndDate(endD);
			competitionDAO.save(competition);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="/competition/uploadFile/")
	@ResponseBody
	public ResponseEntity<String> uploadFile(Long compId,@RequestParam MultipartFile fileUpload) {
		Optional<Competition> competitions = null;
		try {
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(!competitions.isPresent()) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		competition = competitions.get(); 
		try {
			if (fileUpload != null) {
				TestCriteria uploadFile = new TestCriteria();
				uploadFile.setCompetition(competition);
				uploadFile.setFileName(fileUpload.getOriginalFilename());
				uploadFile.setData(fileUpload.getBytes());
				Set<TestCriteria> criterias = competition.getCompetitionCriteria();
				criterias.add(uploadFile);
				competition.setCompetitionCriteria(criterias);
				competitionDAO.save(competition);
			}
			competitionDAO.save(competition);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="/competition/getCompetitionCriteria/")
	@ResponseBody
	public ResponseEntity<String> getCompetitionCriteria(Long compId) {
		Optional<Competition> competitions = null;
		try {
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(!competitions.isPresent()) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		competition = competitions.get(); 
		try {
			Set<TestCriteria> criteria = competition.getCompetitionCriteria();
			List<TestCriteria> criteriaList = new ArrayList<TestCriteria>(criteria);
			criteriaList = sortTestCriteria(criteriaList);
			return new ResponseEntity<String>(new String((criteriaList.get(0).getData()), StandardCharsets.UTF_8),HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	public List<TestCriteria> sortTestCriteria(List<TestCriteria> criteriaList){
		if(criteriaList.size() >1) {
			Collections.sort(criteriaList,new Comparator<TestCriteria>(){
				@Override
				public int compare(TestCriteria a, TestCriteria b) {
					return b.getUploadTime().compareTo(a.getUploadTime());
				}
			});
		}
		return criteriaList;
	}
	@RequestMapping(value="/competition/uploadSubmission/")
	@ResponseBody
	public Long uploadSubmission(Long userId,Long compId,@RequestParam MultipartFile fileUpload) {
		Optional<Competition> competitions = null;
		Optional<User> users = null;
		try {
			users = (Optional<User>)userDAO.findById(userId);
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(users.isPresent() && competitions.isPresent()) {
			competition = competitions.get(); 
		}		
		try {
			for(Submission submission:competition.getCompetitionUserSubmissions()) {
				if(submission.getUser().getId().equals(userId) &&
						submission.getCompetition().getId().equals(compId)) {
					Set<UploadSubmission> uploadedSubmission = submission.getUploadedSubmissions();
					uploadedSubmission.add(new UploadSubmission(submission,fileUpload.getBytes() ,new Date(),Long.valueOf(-1)));
					submissionDAO.save(submission);
					return submission.getId();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return Long.valueOf(-1);
		}
		return Long.valueOf(-1);
	}


	@RequestMapping(value="/competition/evaluateSubmission/")
	@ResponseBody
	public Long evaluateSubmission(Long userId,Long compId) {
		//TODO evaluateSubmission
		Optional<Competition> competitions = null;
		Optional<User> users = null;
		try {
			users = (Optional<User>)userDAO.findById(userId);
			competitions = (Optional<Competition> )competitionDAO.findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Competition competition = null;
		if(users.isPresent() && competitions.isPresent()) {
			competition = competitions.get(); 
		} else {
			return Long.valueOf(-1);
		}
		Set<TestCriteria> criterias = competition.getCompetitionCriteria();
		Submission targetSubmission = null;
		for(Submission submission:competition.getCompetitionUserSubmissions()) {
			if(submission.getUser().getId().equals(userId) &&
					submission.getCompetition().getId().equals(compId)) {
				targetSubmission = submission;
			}
		}
		TestCriteria targetCriteria = null;
		if((criterias.size() > 0)) {
			List<TestCriteria> criteriasList = new ArrayList<TestCriteria>(criterias);
			criteriasList = sortTestCriteria(criteriasList);
			targetCriteria = criteriasList.get(0);
		}
		if((targetCriteria == null) || (targetSubmission == null)) {
			return Long.valueOf(-1);
		} else {
			Set<UploadSubmission> targetUploads = targetSubmission.getUploadedSubmissions();
			if(targetUploads == null || targetUploads.size() <=0) {
				return Long.valueOf(-1);
			}
			List<UploadSubmission> targetUploadList = new ArrayList<UploadSubmission>(targetUploads);
			targetUploadList = sortUploadSubmission(targetUploadList);
			UploadSubmission targetUpload = targetUploadList.get(0);
			File file = new File("out/Testcase.java");
			FileWriter fw = null;
			try {
				fw = new FileWriter(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedWriter writer  = new BufferedWriter(fw);
			try {
				writer.write(new String(targetCriteria.getData()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			File solnFile = new File("out/Solution.java");
			FileWriter fwSoln = null;
			try {
				fwSoln = new FileWriter(solnFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedWriter solnWriter  = new BufferedWriter(fwSoln);
			try {
				solnWriter.write(new String(targetUpload.getData()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				solnWriter.close();
				fwSoln.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Process pBuild = Runtime.getRuntime().exec("javac -d ./build ./out/Solution.java");
				Process pBuild2 = Runtime.getRuntime().exec("javac -d ./build ./out/Testcase.java");
				String err = IOUtils.toString(pBuild.getErrorStream());
				System.out.println(err);
				Runtime.getRuntime().exec("jar cvf YourJar.jar ./build/*");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return Long.valueOf(-1);
	}
	public List<UploadSubmission> sortUploadSubmission(List<UploadSubmission> criteriaList){
		if(criteriaList.size() >1) {
			Collections.sort(criteriaList,new Comparator<UploadSubmission>(){
				@Override
				public int compare(UploadSubmission a, UploadSubmission b) {
					return b.getUploadTime().compareTo(a.getUploadTime());
				}
			});
		}
		return criteriaList;
	}
}
