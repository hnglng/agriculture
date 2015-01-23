package com.sannong.presentation.controller.personalcenter;

import com.sannong.domain.application.ApplicationEntity;
import com.sannong.domain.user.User;
import com.sannong.infrastructure.export.CsvExporter;
import com.sannong.presentation.dto.DTO;
import com.sannong.service.application.IApplicationService;
import com.sannong.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bright Huang on 1/22/15.
 */
@Controller
@RequestMapping(value = "/user-management")
public class UserManagementController {
    private static final Logger logger = Logger.getLogger(UserManagementController.class);
    private static final long PAGE_ROW_NUMBER = 10;

    @Resource
    private IUserService userService;
    @Resource
    private IApplicationService projectService;


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

    private String getUserName(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * Get users with pagination.
     *  URL example: https://api.github.com/user/repos?page=3&per_page=100>
     * @param pageNumber
     * @param perPage
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsersOnPagination(@Param("pageNumber") Integer pageNumber,
                                                         @Param("perPage") Integer perPage,
                                                         HttpServletRequest request) throws Exception {

        Map<String, Object> map = buildUserQueryCondition(request);

        int pageStart = (pageNumber == null ? 0 : (pageNumber - 1)  * perPage);
        map.put("pageStart", pageStart);
        map.put("pageSum", PAGE_ROW_NUMBER);
        List<User> users = userService.getUserByFuzzyMatch(map);

        return users;
    }

    @RequestMapping(value = "/users/count", method = RequestMethod.GET)
    public @ResponseBody int getUserTotalCount(HttpServletRequest request) throws Exception {
        return userService.getUserTotalCount(buildUserQueryCondition(request));
    }

    @RequestMapping(value = "/users/{userName}/application", method = RequestMethod.GET)
    public @ResponseBody
    ApplicationEntity getApplication(@PathVariable("userName") String userName) throws Exception {
        if (StringUtils.isBlank(userName)){
            userName = getUserName();
        }
        return projectService.getApplicationBy(userName);
    }

    @RequestMapping(value = "/users/{userName}/profile", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUserProfile(@PathVariable("userName") String userName) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        return userService.getUserByCondition(map);
    }

    @RequestMapping(value = "/exportCSV", method = RequestMethod.POST)
    public @ResponseBody
    DTO exportAll(HttpServletRequest request) throws Exception {

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
        int questionSum = projectService.getTotalQuestions();
        String filePath = CsvExporter.export(applicants, questionSum);

        String[] filePathSplit = filePath.split("/");
        String fileName = filePathSplit[3];

        return new DTO(true,fileName);
    }


    @RequestMapping(value = "/downloadCsv", method = RequestMethod.GET)
    public void downloadCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

}
