package com.sannong.presentation.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sannong.domain.applications.Application;
import com.sannong.domain.sms.SMS;
import com.sannong.domain.user.User;
import com.sannong.domain.region.City;
import com.sannong.domain.region.District;
import com.sannong.domain.message.ResponseStatus;
import com.sannong.infrastructure.util.PasswordGenerator;
import com.sannong.presentation.model.Response;
import com.sannong.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sannong.infrastructure.export.CsvExporter;
import com.sannong.presentation.model.DTO;

@Controller
@RequestMapping(value = "user-personal-center")
public class PersonalCenterController {
    private static final Logger logger = Logger.getLogger(PersonalCenterController.class);
    private static final String USER_PERSONAL_CENTER_PAGE = "user-personal-center";
    private static final long PAGE_ROW_NUMBER = 10;

    @Resource
    private IUserService userService;
    @Resource
    private ISmsService smsService;
    @Resource
    private IProjectApplicationService projectService;
    @Autowired
    private IValidationService validationService;
    @Autowired
    private IRegionService regionService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("user-personal-center", new Object());
        return new ModelAndView(USER_PERSONAL_CENTER_PAGE, models);
    }

    @RequestMapping(value = {"/user-profile"}, method = RequestMethod.GET)
    public @ResponseBody Response showUserProfile(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        if (StringUtils.isBlank(userName)){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
            } else {
                userName = principal.toString();
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        List<User> users = userService.getUserByCondition(map);
        if (!users.isEmpty()){
            User user = users.get(0);
            List<City> cities = regionService.getCities(user.getCompanyProvince());
            List<District> districts = regionService.getDistricts(user.getCompanyCity());
            Map<String, Object> models = new HashMap<String, Object>();
            models.put("userProfile", users.get(0));
            models.put("cities", cities);
            models.put("districts", districts);
            return new Response(
                    ResponseStatus.SUCCESS.getStatusCode(),
                    ResponseStatus.SUCCESS.getStatusDescription(),
                    models);
        }else{
            return new Response(
                    ResponseStatus.USER_NOT_FOUND.getStatusCode(),
                    ResponseStatus.USER_NOT_FOUND.getStatusDescription());
        }
    }

    @RequestMapping(value = {"/user-profile"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateUserProfile(HttpServletRequest request,
                                                               @ModelAttribute("userProfile") User user) {
        String newCellphone = request.getParameter("newCellphone");
        String validationCode = request.getParameter("validationCode");

        Map<String, Object> models = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(newCellphone) && StringUtils.isNotBlank(validationCode)){
            List<SMS> smsList = smsService.getSmsByCellphoneAndValidationCode(newCellphone, validationCode);
            if (smsList.isEmpty()){
                models.put("status", "error");
                models.put("userProfile", user);
                return models;
            }else{
                user.setMobilePhone(newCellphone);
            }
        }

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        user.setLastUpdated(ts);
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            models.put("status", "error");
        }
        models.put("userProfile", user);
        models.put("status", "saved");
        return models;
    }

    @RequestMapping(value = "/users/page/{number}", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsersOnPagination(@PathVariable("number") Integer number,
                                              HttpServletRequest request) throws Exception {

        Map<String, Object> map = buildUserQueryCondition(request);

        int pageStart = (number == null ? 0 : (number - 1)  * 10);
        map.put("pageStart", pageStart);
        map.put("pageSum", PAGE_ROW_NUMBER);

        List<User> users = userService.getUserByFuzzyMatch(map);

        return users;
    }

    @RequestMapping(value = "/userTotalCount", method = RequestMethod.GET)
    public @ResponseBody int getUserTotalCount(HttpServletRequest request) throws Exception {
    	return userService.getUserTotalCount(buildUserQueryCondition(request));
    }

    @RequestMapping(value = "/applications/{userName}", method = RequestMethod.GET)
    public @ResponseBody
    Application getUserApplication(@PathVariable("userName") String userName) throws Exception {
        return projectService.getApplicationBy(userName);
    }


    private Map buildUserQueryCondition(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>();

        map.put("userName", request.getParameter("userName"));
        map.put("mobilePhone", request.getParameter("mobilePhone"));
        map.put("realName", request.getParameter("realName"));
        map.put("companyName", request.getParameter("companyName"));
        map.put("jobTitle", request.getParameter("jobTitle"));
        map.put("companyAddress", request.getParameter("companyAddress"));
        map.put("mailbox", request.getParameter("mailbox"));
        map.put("companyProvince", request.getParameter("provinceId"));
        map.put("companyCity", request.getParameter("cityId"));
        map.put("companyDistrict", request.getParameter("districtId"));

        return map;
    }


    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    Response updatePassword(HttpServletRequest request) throws Exception {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmedPassword = request.getParameter("confirmedPassword");
        String userName = request.getParameter("userName");

        if (StringUtils.isBlank(userName)){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
            } else {
                userName = principal.toString();
            }
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", userName);
        List<User> users = userService.getUserByCondition(map);
        if ( ! (users.isEmpty()) ){
            User user = users.get(0);
            String encryptOldPassword = PasswordGenerator.encryptPassword(oldPassword, userName);
            if ( ! (user.getPassword().equals(encryptOldPassword))){
                return new Response(
                        ResponseStatus.OLD_PASSWORD_MISMATCH.getStatusCode(),
                        ResponseStatus.OLD_PASSWORD_MISMATCH.getStatusDescription());
            }else if ( ! (newPassword.equals(confirmedPassword)) ){
                return new Response(
                        ResponseStatus.CONFIRMED_PASSWORD_MISMATCH.getStatusCode(),
                        ResponseStatus.CONFIRMED_PASSWORD_MISMATCH.getStatusDescription());
            }
            String encryptedNewPassword = PasswordGenerator.encryptPassword(newPassword, userName);
            user.setPassword(encryptedNewPassword);
            userService.updatePassword(user);

            return new Response(
                    ResponseStatus.PASSWORD_UPDATED.getStatusCode(),
                    ResponseStatus.PASSWORD_UPDATED.getStatusDescription());
        }else{
            return new Response(
                    ResponseStatus.USER_NOT_FOUND.getStatusCode(),
                    ResponseStatus.USER_NOT_FOUND.getStatusDescription());
        }
    }


    @RequestMapping(value = "/exportCSV", method = RequestMethod.POST)
    public @ResponseBody DTO exportAll(HttpServletRequest request) throws Exception {

        Map<String, Object> map = new HashMap<String,Object>();
        String cellphone = request.getParameter("cellphone");
    	String realName = request.getParameter("realName");
    	String company = request.getParameter("company");
    	String jobTitle = request.getParameter("jobTitle");
    	String companyAddress = request.getParameter("companyAddress");
        String mailbox = request.getParameter("mailbox");
        String provinceIndex = request.getParameter("provinceIndex");
        String cityIndex = request.getParameter("cityIndex");
        String districtIndex = request.getParameter("districtIndex");

        map.put("pageStart", 0);
        map.put("cellphone", cellphone);
        map.put("realName", realName);
        map.put("company", company);
        map.put("jobTitle", jobTitle);
        map.put("companyAddress", companyAddress);
        map.put("mailbox", mailbox);
        map.put("companyProvince", provinceIndex);
        map.put("companyCity", cityIndex);
        map.put("companyDistrict", districtIndex);

        int pageSum = userService.getUserTotalCount(map);
        map.put("pageSum", pageSum);

        List<User> applicants = userService.getUserByFuzzyMatch(map);
        List<Application> applicationList = new ArrayList<Application>();
        for(User application :applicants){
            Application app = projectService.getApplicationBy(application.getUserName());
            if(app != null && app.getQuestionnaires().size() > 0){
                applicationList.add(app);
            }
        }
        int questionSum = projectService.getTotalQuestions();
        String filePath = CsvExporter.export(applicationList,questionSum);

        String[] filePathSplit = filePath.split("/");
        String fileName = filePathSplit[3];

        return new DTO(true,fileName);
    }


    @RequestMapping(value = "/downloadCsv", method = RequestMethod.GET)
    public void downloadCsv(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String fileName = request.getParameter("csvFileName");

        java.io.BufferedInputStream bufferInputStream = null;
        java.io.BufferedOutputStream bufferOutputStream = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "csvfiles/";
        String downLoadPath = ctxPath + fileName;

        try {
            long fileLength = new File(downLoadPath).length();

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));

            bufferInputStream = new BufferedInputStream(new FileInputStream(downLoadPath));
            bufferOutputStream = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bufferInputStream.read(buff, 0, buff.length))) {
                bufferOutputStream.write(buff, 0, bytesRead);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (bufferInputStream != null){
                bufferInputStream.close();
            }
            if (bufferOutputStream != null){
                bufferOutputStream.close();
            }
        }
    }


    @RequestMapping(value = "/user-profile/validateUniqueCellphone",method = RequestMethod.GET)
    public @ResponseBody boolean validateUniqueCellphone(HttpServletRequest request){
        String cellphone = request.getParameter("cellphone");
        return validationService.validateUniqueCellphone(cellphone);
    }

    @RequestMapping(value = "/user-profile/sendValidationCode", method = RequestMethod.POST)
    public @ResponseBody String sendValidationCode(HttpServletRequest request) throws Exception {
        String cellphone = request.getParameter("cellphone");
        String newCellphone = request.getParameter("newCellphone");
        String validationCode = PasswordGenerator.generateValidationCode(4);

        if (StringUtils.isNotBlank(newCellphone)){
            return smsService.sendValidationCode(newCellphone, validationCode);
        } else{
            return smsService.sendValidationCode(cellphone, validationCode);
        }
    }
}
