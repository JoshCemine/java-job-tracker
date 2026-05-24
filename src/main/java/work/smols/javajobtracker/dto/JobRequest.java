package work.smols.javajobtracker.dto;

import jakarta.validation.constraints.NotBlank;
import work.smols.javajobtracker.model.Job;

import java.time.LocalDate;

public record JobRequest(
        @NotBlank String company,
        @NotBlank String position,
        String status,
        LocalDate appliedDate
) {
    public Job toEntity(){
        Job job = new Job();
        job.setCompany(company);
        job.setPosition(position);
        job.setStatus(status != null ? status : "APPLIED");
        job.setAppliedDate(appliedDate != null ? appliedDate : LocalDate.now());
        return job;
    }
}
