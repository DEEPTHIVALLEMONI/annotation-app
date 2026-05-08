package com.ethara.annotation.service;

import com.ethara.annotation.entity.TextEntry;
import com.ethara.annotation.repository.TextEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextEntryService {

    @Autowired
    private TextEntryRepository repository;

    // Add a new text entry
    public TextEntry addEntry(TextEntry entry) {
        return repository.save(entry);
    }

    // Get all entries
    public List<TextEntry> getAllEntries() {
        return repository.findAll();
    }

    // Get entry by ID
    public Optional<TextEntry> getEntryById(Long id) {
        return repository.findById(id);
    }

    // Update an entry (label / category)
    public TextEntry updateEntry(Long id, TextEntry updatedEntry) {
        return repository.findById(id).map(existing -> {
            existing.setText(updatedEntry.getText());
            existing.setLabel(updatedEntry.getLabel());
            existing.setCategory(updatedEntry.getCategory());
            existing.setAnnotatedBy(updatedEntry.getAnnotatedBy());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Entry not found with id: " + id));
    }

    // Delete an entry
    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }

    // Get all entries by label
    public List<TextEntry> getByLabel(String label) {
        return repository.findByLabel(label);
    }

    // Get all entries by category
    public List<TextEntry> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    // Get dataset stats
    public java.util.Map<String, Long> getStats() {
        List<TextEntry> all = repository.findAll();
        java.util.Map<String, Long> stats = new java.util.HashMap<>();
        stats.put("total", (long) all.size());
        all.forEach(e -> stats.merge(e.getLabel(), 1L, Long::sum));
        return stats;
    }
}
