package com.lambda.io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class LambdaExceptions implements RequestHandler<SQSEvent, String> {

    public String handleRequest(SQSEvent input, Context context){
        throw new RuntimeException("Something went wrong");
    }
}
