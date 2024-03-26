package com.example.ams.service;

import com.example.ams.Test;
import com.example.ams.TestServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TestServiceImpl extends TestServiceGrpc.TestServiceImplBase {
    @Override
    public void testFunction(Test request, StreamObserver<Empty> responseObserver) {
        super.testFunction(request, responseObserver);
    }
}
