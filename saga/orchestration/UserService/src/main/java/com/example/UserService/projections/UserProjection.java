package com.example.UserService.projections;

import com.example.CommonService.models.CardDetails;
import com.example.CommonService.models.User;
import com.example.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {
    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query){
        CardDetails cardDetails
                = CardDetails.builder()
                .name("yassin mohamed")
                .validUntilMonth(1)
                .validUntilYear(2026)
                .cardNumber("123456789")
                .cvv(111)
                .build();
        return User.builder()
                .userId(query.getUserId())
                .firstName("yassin")
                .lastName("mohamed")
                .cardDetails(cardDetails)
                .build();
    }
}
