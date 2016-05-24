package com.quinnox.qsmr.service;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.quinnox.qsmr.db.QsmrDao;

@Path("/rest")
public class QSMRService {
	
	JSONArray jsonArray = null;
	JSONObject jsonObject = null;
	
	Logger logger = Logger.getLogger(QSMRService.class);
	
	@GET
	@Path("/projectnamelist")
	public Set getProjectNameList(){
		
		logger.info("Entered in QSMRService->getProjectNameList()");
		
		Set projectList = QsmrDao.listProjectName("nodetest1", "user");
		
		return projectList;
	}
	
	@GET
	@Path("/datelist/projectname/{projectname}")
	public Set getDateList(@PathParam("projectname") String ProjectName){
				
		logger.info("Entered in QSMRService->getDateList()");
		logger.info("Project Name :- "+ProjectName);
		
		Set dateList = QsmrDao.listDate("nodetest1", "user",ProjectName);		
		return dateList;
	}
	
	@GET
	@Path("/generatereport/projectname/{projectname}/date/{date}")
	public Set generateReportBasedOnProjectNameAndDate(@PathParam("projectname") String ProjectName, @PathParam("date") String date){
				
		logger.info("Entered in QSMRService->generateReportBasedOnProjectNameAndDate()");
		logger.info("Project Name :- "+ProjectName+", Date :- "+date);
		
		try {
			
			Map<String,String> currentMapValue =  QsmrDao.findData("nodetest1", "user", ProjectName, date);
			Map<String,String> previousMapValue =  QsmrDao.getPreviousMonthValue("nodetest1", "user");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GET
	@Path("/{param}")
	public String getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say HELOLO : " + msg;
		System.out.println("Message ============= "+output);
 
		//return Response.status(200).entity(output).build();
		return output; 
	}
}
