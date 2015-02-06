package com.sannong.project.presentation.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user-center")
public class UserCenterController {
    private static final Logger logger = Logger.getLogger(UserCenterController.class);
    private static final String USER_CENTER_PAGE = "user-center";


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showUserCenter() {
        Map<String, Object> models = new HashMap<String, Object>();
        models.put("user-center", new Object());
        return new ModelAndView(USER_CENTER_PAGE, models);
    }

    /*
    @Resource
    private IUserService userService;
    @Resource
    private ApplicationService applicationService;
*/

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
     * @param request
     * @return
     * @throws Exception
     */
    /*
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsersOnPagination(@RequestParam("pageNumber") Integer pageNumber,
                                                         @RequestParam("perPage") Integer perPage,
                                                         HttpServletRequest request) throws Exception {

        Map<String, Object> map = buildUserQueryCondition(request);

        int pageStart = (pageNumber == null ? 0 : (pageNumber - 1)  * perPage);
        map.put("pageStart", pageStart);
        map.put("pageSum", PAGE_ROW_NUMBER);
        List<User> users = userService.getUserByFuzzyMatch(map);

        return users;
    }
    */

    /*
    @RequestMapping(value = "/users/count", method = RequestMethod.GET)
    public @ResponseBody int getUserTotalCount(HttpServletRequest request) throws Exception {
        return userService.getUserTotalCount(buildUserQueryCondition(request));
    }
    */

    /*
    @RequestMapping(value = "/users/{userName}/application", method = RequestMethod.GET)
    public @ResponseBody
    Application getApplication(@PathVariable("userName") String userName) throws Exception {
        if (StringUtils.isBlank(userName)){
            userName = getUserName();
        }
        return applicationService.findByUserName(userName).get(0);
    }
    */

    /*
    @RequestMapping(value = "/users/{userName}/profile", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUserProfile(@PathVariable("userName") String userName) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        return userService.getUserByCondition(map);
    }
    */

    /*
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
        int questionSum = applicationService.getTotalQuestions();
        String filePath = CsvExporter.export(applicants, questionSum);

        String[] filePathSplit = filePath.split("/");
        String fileName = filePathSplit[3];

        return new DTO(true,fileName);
    }
    */

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

        /*
    private static final Logger logger = Logger.getLogger(UserProfileController.class);

    @Resource
    private IUserService userService;
    @Resource
    private ISmsService smsService;
    @Autowired
    private IValidationService validationService;
    @Autowired
    private IRegionService regionService;


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

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Response getProfile(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        if (StringUtils.isBlank(userName)){
            userName = getUserName();
        }

        Response response = new Response();
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

            response.setStatusCode(Status.OK.getCode());
            response.setStatusMessage(Status.OK.getMessage());
            response.setData(models);
        }else{
            response.setStatusCode(Status.USER_NOT_FOUND.getCode());
            response.setStatusMessage(Status.USER_NOT_FOUND.getMessage());
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateProfile(HttpServletRequest request,
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

    @RequestMapping(value = "/validateUniqueCellphone",method = RequestMethod.GET)
    public @ResponseBody boolean validateUniqueCellphone(HttpServletRequest request){
        String cellphone = request.getParameter("cellphone");
        return validationService.validateUniqueCellphone(cellphone);
    }

    @RequestMapping(value = "/sendValidationCode", method = RequestMethod.POST)
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

        Response response = new Response();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", userName);
        List<User> users = userService.getUserByCondition(map);
        if ( ! (users.isEmpty()) ){
            User user = users.get(0);
            String encryptOldPassword = PasswordGenerator.encryptPassword(oldPassword, userName);
            if ( ! (user.getPassword().equals(encryptOldPassword))){
                response.setStatusCode(Status.OLD_PASSWORD_MISMATCH.getCode());
                response.setStatusMessage(Status.OLD_PASSWORD_MISMATCH.getMessage());
            }else if ( ! (newPassword.equals(confirmedPassword)) ){
                response.setStatusCode(Status.CONFIRMED_PASSWORD_MISMATCH.getCode());
                response.setStatusMessage(Status.CONFIRMED_PASSWORD_MISMATCH.getMessage());
            }
            String encryptedNewPassword = PasswordGenerator.encryptPassword(newPassword, userName);
            user.setPassword(encryptedNewPassword);
            userService.updatePassword(user);
            response.setStatusCode(Status.PASSWORD_UPDATED.getCode());
            response.setStatusMessage(Status.PASSWORD_UPDATED.getMessage());
        }else{
            response.setStatusCode(Status.USER_NOT_FOUND.getCode());
            response.setStatusMessage(Status.USER_NOT_FOUND.getMessage());
        }

        return response;
    }
*/

}
