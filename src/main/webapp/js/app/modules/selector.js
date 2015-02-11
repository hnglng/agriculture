/**
 * Created by Bright Huang on 11/5/14.
 */

define(['jquery', 'sannong', 'ajaxHandler'], function($, sannong, ajaxHandler) {

    "use strict";

    var selector = {},
        callbacks = {};

    selector.Model = {
        companyProvince: "", companyCity: "", companyDistrict: "",
        provinceSelects: {
            companyProvinceSelect: "companyProvinceSelect",
            provinceSelect: "provinceSelect",
            provinceQuerySelect: "provinceQuerySelect"
        },
        citySelects: {
            companyCitySelect: "companyCitySelect",
            citySelect: "citySelect",
            cityQuerySelect: "cityQuerySelect"
        }
    };
    selector.View = {
        setSelectedOption: function(select, optionVal){
            $(select).val(optionVal);
        },
        addCityOptions: function(selectId, options){
            var select = $(selectId);
            $(selectId + ' option').remove();
            for (var i in options){
                var optionValue = options[i].cityId,
                    optionText = options[i].cityName,
                    option = "<option value=" + optionValue + ">" + optionText + "</option>";
                select.append(option);
            }
        },
        addDistrictOptions: function(selectId, options){
            var select = $(selectId);
            $(selectId + ' option').remove();
            for (var i in options){
                var optionValue = options[i].districtId,
                    optionText = options[i].districtName,
                    option = "<option value=" + optionValue + ">" + optionText + "</option>";
                select.append(option);
            }
        },
        addStyleWrapper: function(selectId){
            $("#wrap_" + selectId).remove();
            $("#" + selectId + "Div").html('<select id="' + selectId + '" name="' + selectId + '" class="select-hidden"></select>');
        },
        initCitySelect: function(selectId, cities){
            selector.View.addStyleWrapper(selectId);
            selector.View.addCityOptions("#" + selectId, cities);
            selector.initSelect('select[id=' + selectId + ']');
        },
        initDistrictSelect: function(selectId, districts){
            selector.View.addStyleWrapper(selectId);
            selector.View.addDistrictOptions("#" + selectId, districts);
            selector.initSelect('select[id=' + selectId + ']');
        }
    };

    selector.Controller = {
        initAddressSelect: function(viewName, address){
            var provinceId = address.provinceId,
                cityId = address.cityId,
                districtId = address.districtId,
                cities = address.cities,
                districts = address.districts;

            selector.View.addCityOptions(viewName + " #citySelect", cities);
            selector.View.addDistrictOptions(viewName + " #districtSelect", districts);

            selector.View.setSelectedOption(viewName + " #provinceSelect", provinceId);
            selector.View.setSelectedOption(viewName + " #citySelect", cityId);
            selector.View.setSelectedOption(viewName + " #districtSelect", districtId);

            selector.initSelect('select', {
                provinceOption: {
                    value: provinceId,
                    text: $("#provinceSelect option:selected").text()
                },
                cityOption: {
                    value: cityId,
                    text: $("#citySelect option:selected").text()
                },
                districtOption: {
                    value: districtId,
                    text: $("#districtSelect option:selected").text()
                }
            });
        },
        handleProvinceSelected: function(provinceId, selectId){
            ajaxHandler.sendRequest({
                url: '/api/regions/provinces/' + provinceId + "/cities",
                type: 'GET',
                success: function(data){
                    selector.Controller.processProvinceSelect(selectId, data);
                },
                error: function(data){
                    console.log(data);
                }
            });
        },
        handleCitySelected: function(cityId, selectId){
            ajaxHandler.sendRequest({
                url: '/api/regions/cities/' + cityId + "/districts",
                type: 'GET',
                success: function(data){
                    selector.Controller.processCitySelect(selectId, data);
                },
                error: function(data){
                    console.log(data);
                }
            });
        },
        processProvinceSelect: function(selectId, cities){
            if (selectId === "companyProvinceSelect"){
                selector.View.initCitySelect("companyCitySelect", cities);
                selector.View.initDistrictSelect("companyDistrictSelect", cities[0].districts);
            }else if(selectId === "provinceSelect"){
                selector.View.initCitySelect("citySelect", cities);
                selector.View.initDistrictSelect("districtSelect", cities[0].districts);
            }else if(selectId === "provinceQuerySelect") {
                selector.View.initCitySelect("cityQuerySelect", cities);
                $("#cityQuerySelect").prepend('<option value="">市</option>');
                selector.View.initDistrictSelect("districtQuerySelect", cities[0].districts);
                $("#districtQuerySelect").prepend('<option value="">县/市辖区</option>');
            }

        },
        processCitySelect: function(selectId, districts){
            if(selectId === "companyCitySelect"){
                selector.View.initDistrictSelect("companyDistrictSelect", districts);
            }else if(selectId === "citySelect"){
                selector.View.initDistrictSelect("districtSelect", districts);
            }else if(selectId === "cityQuerySelect"){
                selector.View.initDistrictSelect("districtQuerySelect", districts);
                $("#districtQuerySelect").prepend('<option value="">县/市辖区</option>');
            }
        }
    };

    selector.initSelect = function(select, options){
        $(select).each(function(){
            var selectName = $(this).attr("name"),
                selectId =  $(this).attr("id"),
                $this = $(this),
                numberOfOptions = $(this).children('option').length;

            $this.addClass('select-hidden');
            $this.wrap('<div class="select" id="wrap_' + selectId + '"></div>');
            $this.after('<div class="select-styled selected_'+selectName+'"></div>');

            var $styledSelect = $this.next('div.select-styled'),
                styledSelectRel = $this.children('option').eq(0).val();

            if (selectId === "provinceSelect" && options != undefined){
                $styledSelect.text(options.provinceOption.text);
                $styledSelect.attr("rel", options.provinceOption.value);
            }else if (selectId === "citySelect" && options != undefined){
                $styledSelect.text(options.cityOption.text);
                $styledSelect.attr("rel", options.cityOption.value);
            }else if (selectId === "districtSelect" && options != undefined){
                $styledSelect.text(options.districtOption.text);
                $styledSelect.attr("rel", options.districtOption.value);
            }else{
                $styledSelect.text($this.children('option').eq(0).text());
                $styledSelect.attr("rel", styledSelectRel);
            }

            var $list = $('<ul />', {'class': 'select-options option_'+selectName+''}).insertAfter($styledSelect);

            for (var i = 0; i < numberOfOptions; i++) {
                $('<li />', {
                    text: $this.children('option').eq(i).text(),
                    rel: $this.children('option').eq(i).val()
                }).appendTo($list);
            }

            var $listItems = $list.children('li');

            $styledSelect.click(function(e) {
                e.stopPropagation();
                $('div.select-styled.active').each(function(){
                    $(this).removeClass('active').next('ul.select-options').hide();
                });
                $(this).toggleClass('active').next('ul.select-options').slideToggle(200);
            });

            $listItems.click(function(e) {
                e.stopPropagation();
                $styledSelect.text($(this).text()).removeClass('active');

                var selectedRel = $(this).attr('rel'),
                    selectId = $this.attr("id");

                $styledSelect.attr("rel", selectedRel);
                $this.val($(this).attr('rel'));

                $list.hide();

                if(selectId === "searchKey"){
                    $("#" + selectId).val(selectedRel);
                }else {
                    var selectValue = parseInt(selectedRel, 10);
                    $("#" + selectId).val(selectValue);
                    if (selectId.toLowerCase().indexOf("province") >= 0){
                        selector.Controller.handleProvinceSelected(selectValue, selectId);
                    }else if (selectId.toLowerCase().indexOf("city") >= 0){
                        selector.Controller.handleCitySelected(selectValue, selectId);
                    }
                }
            });

            $(document).click(function() {
                $styledSelect.removeClass('active');
                $list.hide();
            });

        });

    }

    sannong.Selector = selector;
    return selector;

});