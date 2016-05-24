package com.quinnox.qsmr.service;

import java.util.Iterator;
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
	
	@GET
	@Path("/projectnamelist")
	public Set getProjectNameList(){
		
		Logger logger = Logger.getLogger(QSMRService.class);
		
		logger.info("Entered in QSMRService->getProjectNameList()");
		
		Set projectList = QsmrDao.listProjectName("nodetest1", "user");
		
		return projectList;
	}
	
	@GET
	@Path("/projectname/{param}/datelist")
	public Set getDateList(@PathParam("param") String ProjectName){
		
		Logger logger = Logger.getLogger(QSMRService.class);
		
		logger.info("Entered in QSMRService->getDateList()");
		logger.info("Project Name :- "+ProjectName);
		
		Set dateList = QsmrDao.listDate("nodetest1", "user",ProjectName);		
		return dateList;
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
