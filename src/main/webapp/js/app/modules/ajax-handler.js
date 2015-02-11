/**
 * Created by Bright Huang on 11/5/14.
 */

define(['jquery', 'sannong'], function($, sannong) {

    "use strict";

    var ajaxHandler = {};

    ajaxHandler.sendRequest = function (options) {
        return $.ajax({
            cache: false,
            data: options.data,
            dataType: options.dataType,
            type: options.type,
            url: options.url
        }).success(function (data, status, xhr) {
            options.success(data, status, xhr);
        }).error(function (xhr, status, error) {
            options.error(error);
        }).always(function (xhr, status, error) {
            //options.always(xhr, status, error);
        });
    };

    sannong.AjaxHandler = ajaxHandler;
    return ajaxHandler;

});