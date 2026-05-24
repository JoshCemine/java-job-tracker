package work.smols.javajobtracker.dto;

import work.smols.javajobtracker.model.Job;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobResponse(
        Long id,
        String company,
        String position,
        String status,
        LocalDate appliedDate,
        LocalDateTime createdAt
) {
    public static JobResponse from(Job job){
        return new JobResponse(
                job.getId(), job.getCompany(), job.getPosition(),
                job.getStatus(), job.getAppliedDate(), job.getCreatedAt()
        );
    }
}
