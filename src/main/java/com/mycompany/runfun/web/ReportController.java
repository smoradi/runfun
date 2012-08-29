package com.mycompany.runfun.web;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.runfun.domain.User;


/**
 * This class is generated by <b>jasperoo setup</b>.
 * Request mapping methods are added by <b>jasperoo add</b> or <b>jasperoo all</b>.
 * 
 * <b>ANYTHING ADDED AFTER THE CLOSING BRACE WILL BE DELETED BY <u>jasperoo add</u>!</b>
 */
@RequestMapping("/reports")
@Controller
public class ReportController {

/*
 * The template for the "List" Request mapping methods is: 
 *
 *	@RequestMapping(value ="/**ENTITY_NAME_LOWER**List/{format}", method = RequestMethod.GET)
 *	public String report**ENTITY_NAME**List(ModelMap modelMap, @PathVariable("format") String format) {
 *		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(**ENTITY_NAME**.findAll**REPORT_TITLE**(),false);
 *		modelMap.put("reportData", jrDataSource);
 *		modelMap.put("format", format);
 *		return "**ENTITY_NAME_LOWER**ReportList";
 *	}
 */

	@RequestMapping(value ="/{username}/recordList/{format}", method = RequestMethod.GET)
	public String reportRecordList(ModelMap modelMap, @PathVariable("format") String format, @PathVariable("username") String username) {
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(com.mycompany.runfun.domain.Record.findRecordsByUser(User.findUsersByUsernameEquals(username).getSingleResult()).getResultList(),false);
		modelMap.put("reportData", jrDataSource);
		modelMap.put("format", format);
		return "recordReportList";
	}

	@RequestMapping(value ="/{username}/recordDetail/{id}/{format}", method = RequestMethod.GET)
	public String reportRecordDetail(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("format") String format, @PathVariable("username") String username) {
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(com.mycompany.runfun.domain.Record.findRecordsByUserAndIdEquals(User.findUsersByUsernameEquals(username).getSingleResult(), id).getResultList(),false);
		modelMap.put("reportData", jrDataSource);
		modelMap.put("format", format);
		return "recordReportDetail";
	}

}