/**
 * Created by apple on 2/5/15.
 */
define(['jquery', 'bootstrap', 'handlebars', 'sannong', 'validate', 'ajaxHandler', 'formValidator',
        'additionalMethods', 'pagination', 'selector', 'jqueryForm', 'eventHandler', 'questionnaire'],
    function($, bootstrap, handlebars, sannong, validate, ajaxHandler, formValidator,
             additionalMethods, pagination, selector, jqueryForm, eventHandler, questionnaire) {

        "use strict";
        var dataExport = {};

        dataExport.Controller = {
            exportCSV: function() {
                var searchKey = $("#searchKey").val(),
                    searchValue = $("#searchValue").val(),
                    provinceIndex = $("#provinceQuerySelect").val(),
                    cityIndex = $("#cityQuerySelect").val(),
                    districtIndex = $("#districtQuerySelect").val(),
                    parameter;

                if (searchKey == "手机号"){
                    parameter = "cellphone=" + searchValue;
                }else if (searchKey == "姓名"){
                    parameter = "realName=" + searchValue;
                }else if (searchKey == "工作单位"){
                    parameter = "company=" + searchValue;
                }else if (searchKey == "职位"){
                    parameter = "jobTitle=" + searchValue;
                }else if (searchKey == "电子邮箱"){
                    parameter = "mailbox=" + searchValue;
                }else if (searchKey == "单位地址"){
                    parameter = "companyAddress=" + searchValue;
                }

                parameter = parameter + "&provinceIndex=" + provinceIndex + "&cityIndex=" + cityIndex + "&districtIndex=" + districtIndex;

                $.ajax({
                    type : "post",
                    dataType : "json",
                    url : "exportCSV",
                    data : parameter,
                    success : function(data) {
                        $('#exportModal').modal('hide');
                        location.href = "/sannong/downloadCsv?csvFileName=" + data.returnValue;
                    }
                });
            }
        };

        function subscribeEvent(){
            eventHandler.subscribe("dataExport:exportCSV", dataExport.Controller.exportCSV);
        }

        $(function() {
            subscribeEvent();
        })


        sannong.DataExport = dataExport;
        return dataExport;
    });