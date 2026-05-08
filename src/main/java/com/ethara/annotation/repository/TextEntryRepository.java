package com.ethara.annotation.repository;

import com.ethara.annotation.entity.TextEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TextEntryRepository extends JpaRepository<TextEntry, Long> {

    // Find all entries by label (e.g., "Positive")
    List<TextEntry> findByLabel(String label);

    // Find all entries by category (e.g., "Sentiment")
    List<TextEntry> findByCategory(String category);

    // Find by annotator name
    List<TextEntry> findByAnnotatedBy(String annotatedBy);
}
