package work.smols.javajobtracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import work.smols.javajobtracker.exception.JobNotFoundException;
import work.smols.javajobtracker.model.Job;
import work.smols.javajobtracker.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository repository;
    public JobService(JobRepository repository){
        this.repository = repository;
    }

    public Page<Job> findAll(Pageable pageable) {
        return repository.findAll(pageable);   // the free paginated variant
    }

    public Job findById(Long id){
        return this.repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    public Job create(Job job){
        return this.repository.save(job);
    }

    public Job update(Long id, Job incoming){
        Job existing = repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        existing.setCompany(incoming.getCompany());
        existing.setPosition(incoming.getPosition());
        existing.setStatus(incoming.getStatus());
        existing.setAppliedDate(incoming.getAppliedDate());
        return repository.save(existing);
    }

    public void deleteById(Long id){
        if (! repository.existsById(id)) throw new JobNotFoundException(id);
        this.repository.deleteById(id);
    }
}
