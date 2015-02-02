define('sannong', ['jquery'], function($) {

    "use strict";

    var sannong = {};

    sannong.Model = {};

    sannong.getBaseUrl =  function(){
        var pathArray = window.location.pathname.split( '/'),
        secondLevelLocation = pathArray[1],
        newURL = window.location.protocol + "//" + window.location.host + "/" + secondLevelLocation;
        return newURL;
    }

    function showWelcome(){
        $.ajax({
            type: "POST",
            url: "login/realName",
            dataType: "json",
            data: {},
            success: function (response) {
                if (response.statusCode < 2000) {
                    $("#welcome").text("欢迎 " + response.models.realName);
                } else {
                }
            },
            fail: function (response) {
            }
        });
    }

    $(function() {
        showWelcome();
    });

    window.Sannong = sannong;
    return sannong;

});