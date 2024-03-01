package com.vl.producer.messagefactory;

import com.vl.model.avro.Employee;
import com.vl.model.avro.Report;
import com.vl.model.avro.ReportDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Component
@Profile("experimental-avro")
public class AvroMessageFactory implements MessageFactory {

    @Override
    public Report generateMessage() {
        return Report.newBuilder()
                .setReportId(UUID.randomUUID().toString())
                .setEmployee(Employee.newBuilder()
                        .setEmployeeId(UUID.randomUUID().toString())
                        .setDepartment(Integer.parseInt(randomNumeric(3)))
                        .setEmployeeName("EmployeeName-" + randomAlphabetic(4))
                        .setPosition("Position-" + randomAlphabetic(3))
                        .build())
                .setDetails(List.of(
                        ReportDetails.newBuilder()
                                .setDetailId("DetailId-" + randomAlphabetic(7))
                                .setDetailName("DetailName-" + randomAlphabetic(4))
                                .build())
                )
                .build();
    }
}
