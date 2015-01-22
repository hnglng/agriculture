package com.sannong.domain.share;

/**
 * Created by Bright Huang on 1/21/15.
 */
public class Error {

    /**
     HTTP Status Code Summary
     200 OK - Everything worked as expected.
     400 Bad Request - Often missing a required parameter.
     401 Unauthorized - No valid API key provided.
     402 Request Failed - Parameters were valid but request failed.
     404 Not Found - The requested item doesn't exist.
     500, 502, 503, 504 Server errors - something went wrong on Stripe's end.
     */

    /**
     type
     The type of error returned. Can be invalid_request_error, api_error, or card_error.

     message
     A human-readable message giving more details about the error. For card errors, these messages can be shown to your users.

     code optional
     For card errors, a short string from amongst those listed on the right describing the kind of card error that occurred.

     param optional
     The parameter the error relates to if the error is parameter-specific. You can use this to display a message near the correct form field, for example.
     */

    //private static final String code;
    //private static final String message;



}
