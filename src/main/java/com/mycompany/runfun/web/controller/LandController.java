package com.mycompany.runfun.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mycompany.runfun.domain.Land;
import com.mycompany.runfun.domain.User;
import com.mycompany.runfun.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/lands")
@Controller
@RooWebScaffold(path = "lands", formBackingObject = Land.class, update = false, create = false, populateMethods = false)
public class LandController {
	
	protected static final String VIEW_CREATE = "lands/user/create"; 
	
	@Autowired
	protected UserService userService;
	
    @RequestMapping(value = "/{username}", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Land land, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, @PathVariable("username") String username) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, land, username);
            return VIEW_CREATE;
        }
        uiModel.asMap().clear();
        land.setUser(userService.findByUsername(username));
        land.persist();
        return "redirect:/records/" + username + "?form";
    }
    @RequestMapping(value = "/{username}/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, @PathVariable("username") String username, Model uiModel) {
        uiModel.addAttribute("land", Land.findLandsByUserAndIdEquals(userService.findByUsername(username), id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("username", username);
        return "lands/show";
    }
    
    @RequestMapping(value = "/{username}" , produces = "text/html")
    public String list(@PathVariable("username") String username,
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
    	uiModel.addAttribute("username", username);
    	User user = userService.findByUsername(username);
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("lands", Land.findLandEntries(user, firstResult, sizeNo));
            float nrOfPages = (float) Land.countLands(user) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("lands", Land.findLandsByUser(user).getResultList());
        }
        return "lands/list";
    }
    
    @RequestMapping(value = "/{username}/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @PathVariable("username") String username,
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Land land = Land.findLand(id);
        land.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        uiModel.addAttribute("username", username);
        return "redirect:/lands/" + username;
    }
    
    @RequestMapping(value = "/{username}", params = "form", produces = "text/html")
    public String createForm(Model uiModel, @PathVariable("username") String username) {
        populateEditForm(uiModel, new Land(), username);
        List<String[]> dependencies = new ArrayList<String[]>();
        if (User.countUsers() == 0) {
            dependencies.add(new String[] { "user", "users" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return VIEW_CREATE;
    }
    
    void populateEditForm(Model uiModel, Land land, String username) {
        uiModel.addAttribute("land", land);
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
