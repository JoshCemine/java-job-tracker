package work.smols.javajobtracker.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import work.smols.javajobtracker.dto.JobRequest;
import work.smols.javajobtracker.dto.JobResponse;
import work.smols.javajobtracker.model.Job;
import work.smols.javajobtracker.service.JobService;

import java.net.URI;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping
    public Page<JobResponse> findAll(Pageable pageable) {
        return service.findAll(pageable).map(JobResponse::from);
    }

    @GetMapping("/{id}")
    public JobResponse findById(@PathVariable Long id) {
        return JobResponse.from(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<JobResponse> create(@Valid @RequestBody JobRequest request) {
        Job created = service.create(request.toEntity());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(JobResponse.from(created));
    }

    @PutMapping("/{id}")
    public JobResponse update(@PathVariable Long id, @Valid @RequestBody JobRequest request) {
        return JobResponse.from(service.update(id, request.toEntity()));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
