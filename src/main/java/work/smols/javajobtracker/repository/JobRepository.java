package work.smols.javajobtracker.repository;

import work.smols.javajobtracker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
