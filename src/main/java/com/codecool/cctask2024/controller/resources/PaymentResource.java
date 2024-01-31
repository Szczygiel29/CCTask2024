//package com.codecool.cctask2024.controller.resources;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.List;
//
//public interface PaymentResource {
//
//    String FIND_ALL_PAYMENTS = "/all";
//    String SAVE_PAYMENT = "/save";
//
//    /**
//     * Endpoint for finding all payments
//     *
//     * @return payments
//     */
//    List<Payment> showPayments();
//
//    /**
//     * Endpoint saving the Payment to the database
//     *
//     * @param payment Model Payment description a payment
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Create data for payment")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Will return MedDream access to"),
//            @ApiResponse(
//                    responseCode = "401",
//                    description = "Authentication failed"),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "Internal server error"),
//    })
//    void savePayment(@RequestBody Payment payment);
//
//}
