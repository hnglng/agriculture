/**
 * Created by Bright Huang on 11/1/14.
 */

define(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler', 'jqueryForm', 'formValidator',
        'selector', 'additionalMethods','eventHandler'],
    function($, bootstrap, handlebars, sannong, validate, ajaxHandler, jqueryForm, formValidator,
             selector, additionalMethods, eventHandler) {

        "use strict";

        var userProfile = {};

        userProfile.Model = {
            userProfileInitData: {
                "uerName": "", "cellphone": "", "realName": "", "jobTitle": "",
                "company": "", "companyAddress": "", "deskPhone": "", "mailbox": ""
            }
        };

        userProfile.View = {
            newCellphoneErrorLabel: '<label id="newCellphone-error" class="error" for="newCellphone" style="display: inline-block;">手机号码已存在</label>',
            userProfileView: $("#user-profile-view"),
            sideBar: $(".sidebar"),
            userManagementTab: $("#userManagementTab"),
            userProfileTab: $("#userProfileTab"),
            userPasswordTab: $("#userPasswordTab"),
            newCellphone: $("#newCellphone"),
            newCellphoneError: $("#newCellphone-error"),
            showValidationError: function(){
                userProfile.View.newCellphoneError.remove();
                userProfile.View.newCellphone.removeClass("error");
                userProfile.View.newCellphone.after(userProfile.View.newCellphoneErrorLabel);
                userProfile.View.newCellphone.addClass("error");
            },
            renderUserProfileView: function(isPageLoad, data){
                var user = data.user,
                    userProfileViewHandler = handlebars.compile($("#user-profile-template").html()),
                    html = userProfileViewHandler(data);
                $("#user-profile-view").empty();
                $("#user-profile-view").append(html);

                var address = {
                    provinceId: user.province.provinceId,
                    cityId: user.city.cityId,
                    districtId: user.district.districtId,
                    cities: data.addressRegion.cities,
                    districts: data.addressRegion.districts
                }

                selector.Controller.initAddressSelect("#user-profile-view", address);

            }
        };

        userProfile.Controller = {
            handleFormSubmit: function(){
                var validator = formValidator.getValidator("#userProfileForm");
                if (validator === undefined){return;}
                if (validator.form() == true){
                    $("#userProfileForm").ajaxSubmit(function(message) {
                        $("#userProfileSubmit").after('<label id="userProfileSubmit-error" class="error" style="margin-top: 23px;">已保存</label>');
                        return false;
                    });
                }
            },
            handleValidationCodeClicked: function(){
                ajaxHandler.sendRequest({
                    type: "GET",
                    url: "user-profile/validateUniqueCellphone",
                    data:{cellphone: $("#newCellphone").val()},
                    success: function(response){
                        if (response == true){
                            var validator = formValidator.getValidator("#userProfileForm");
                            var newCellphoneValid = validator.element($("#newCellphone"));
                            if (validator.form() == true && newCellphoneValid == true ){
                                additionalMethods.updateTimeLabel("#action-send-code", "验证码");
                                ajaxHandler.sendRequest({
                                    url: 'user-profile/sendValidationCode',
                                    type: 'POST',
                                    data: {
                                        newCellphone: $("#newCellphone").val()
                                    },
                                    success: function(response){
                                        if (response != "") {
                                            $("#validationCode").removeAttr("disabled");
                                        } else {
                                            $("#validationCode").attr({disabled: "disabled"});
                                        }
                                    },
                                    fail: function(response){
                                        $("#validationCode").attr({disabled: "disabled"});
                                    }
                                });
                            }
                        }else{
                            userProfile.View.showValidationError();
                        }
                    },
                    error: function(data){
                        console.log(data);
                        userProfile.View.showValidationError();
                    }
                });
            },
            showUserProfileView: function(){
                ajaxHandler.sendRequest({
                    type: "GET",
                    url: "/user-center/user-profile",
                    dataType: "json",
                    data:{userId: 1},
                    success: function(data){
                        userProfile.View.renderUserProfileView(false, data);
                    },
                    error: function(data){
                        console.log(data);
                    }
                });
            }
        }

        function init(){
            eventHandler.subscribe("userProfile:handleFormSubmit", userProfile.Controller.handleFormSubmit);
            eventHandler.subscribe("userProfile:handleValidationCodeClicked", userProfile.Controller.handleValidationCodeClicked);
            userProfile.Controller.showUserProfileView();
        }


        $(function() {
            init();
            //selector.initSelect('select');
            //$.extend(userProfile, eventHandler);
        });

        sannong.UserProfile = userProfile;
        return userProfile;

});