package com.mycompany.runfun.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mycompany.runfun.domain.Land;
import com.mycompany.runfun.domain.Record;
import com.mycompany.runfun.domain.User;
import com.mycompany.runfun.service.RecordService;
import com.mycompany.runfun.service.UserService;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/records")
@Controller
@RooWebScaffold(path = "records", formBackingObject = Record.class, create = false, update = false, populateMethods = false)
public class RecordController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected RecordService recordService;
	
    @RequestMapping(value = "/{username}", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Record record, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, @PathVariable("username") String username) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, record, username);
            return "records/user/create";
        }
        uiModel.asMap().clear();
        record.setUser(userService.findByUsername(username));
        record.persist();
        return "redirect:/records/" + username + "/" + encodeUrlPathSegment(record.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{username}",  params = "form", produces = "text/html")
    public String createForm(@PathVariable("username") String username, Model uiModel) {
        populateEditForm(uiModel, new Record(), username);
        List<String[]> dependencies = new ArrayList<String[]>();
        if (Land.findLandsByUser(userService.findByUsername(username)).getResultList().size() == 0) {
            dependencies.add(new String[] { "land", "lands/" + username });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "records/user/create";
    }

    @RequestMapping(value = "/{username}/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, @PathVariable("username") String username, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("record", recordService.findByUsernameAndId(username, id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("username", username);
        return "records/show";
    }
    
    @RequestMapping(value = "/{username}",  produces = "text/html")
    public String list(@PathVariable("username") String username, Model uiModel,
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size) {
    	uiModel.addAttribute("username", username);
    	User user = userService.findByUsername(username);
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("records", Record.findRecordEntries(user, firstResult, sizeNo));
            float nrOfPages = (float) Record.countRecords(user) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("records", Record.findRecordsByUser(user).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "records/list";
    }
    
    @RequestMapping(value = "/{username}/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @PathVariable("username") String username, 
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Record record = Record.findRecord(id);
        record.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        uiModel.addAttribute("username", username);
        return "redirect:/records/" + username;
    }
    
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("record_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void populateEditForm(Model uiModel, Record record, String username) {
    	if (record.getDate() == null)
    		record.setDate(new Date());
        uiModel.addAttribute("record", record);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("lands", Land.findLandsByUser(userService.findByUsername(username)).getResultList());
        uiModel.addAttribute("username", username);
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

}
