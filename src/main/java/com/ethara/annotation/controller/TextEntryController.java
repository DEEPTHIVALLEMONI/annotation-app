package com.ethara.annotation.controller;

import com.ethara.annotation.entity.TextEntry;
import com.ethara.annotation.service.TextEntryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entries")
@CrossOrigin(origins = "*")  // Allows frontend on any port to call this API
public class TextEntryController {

    @Autowired
    private TextEntryService service;

    @Autowired
    private ObjectMapper objectMapper;

    // -----------------------------------------------
    // POST /api/entries  → Add new entry
    // -----------------------------------------------
    @PostMapping
    public ResponseEntity<TextEntry> addEntry(@RequestBody TextEntry entry) {
        TextEntry saved = service.addEntry(entry);
        return ResponseEntity.ok(saved);
    }

    // -----------------------------------------------
    // GET /api/entries  → Get all entries
    // -----------------------------------------------
    @GetMapping
    public ResponseEntity<List<TextEntry>> getAllEntries() {
        return ResponseEntity.ok(service.getAllEntries());
    }

    // -----------------------------------------------
    // GET /api/entries/{id}  → Get single entry
    // -----------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<TextEntry> getEntry(@PathVariable Long id) {
        return service.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------------------------
    // PUT /api/entries/{id}  → Update entry
    // -----------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<TextEntry> updateEntry(
            @PathVariable Long id,
            @RequestBody TextEntry entry) {
        try {
            TextEntry updated = service.updateEntry(id, entry);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // -----------------------------------------------
    // DELETE /api/entries/{id}  → Delete entry
    // -----------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        service.deleteEntry(id);
        return ResponseEntity.ok("Entry deleted successfully");
    }

    // -----------------------------------------------
    // GET /api/entries/filter?label=Positive
    // -----------------------------------------------
    @GetMapping("/filter")
    public ResponseEntity<List<TextEntry>> filterByLabel(@RequestParam String label) {
        return ResponseEntity.ok(service.getByLabel(label));
    }

    // -----------------------------------------------
    // GET /api/entries/stats  → Count per label
    // -----------------------------------------------
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    // -----------------------------------------------
    // GET /api/entries/export  → Download as JSON
    // -----------------------------------------------
    @GetMapping("/export")
    public void exportDataset(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=dataset.json");

        List<TextEntry> allEntries = service.getAllEntries();
        objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(response.getOutputStream(), allEntries);
    }

    // -----------------------------------------------
    // GET /api/entries/export/csv  → Download as CSV
    // -----------------------------------------------
    @GetMapping("/export/csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=dataset.csv");

        List<TextEntry> entries = service.getAllEntries();
        StringBuilder sb = new StringBuilder();
        sb.append("id,text,label,category,annotatedBy,createdAt\n");

        for (TextEntry e : entries) {
            sb.append(e.getId()).append(",")
              .append("\"").append(e.getText().replace("\"", "\"\"")).append("\"").append(",")
              .append(e.getLabel()).append(",")
              .append(e.getCategory() != null ? e.getCategory() : "").append(",")
              .append(e.getAnnotatedBy() != null ? e.getAnnotatedBy() : "").append(",")
              .append(e.getCreatedAt()).append("\n");
        }

        response.getOutputStream().write(sb.toString().getBytes());
    }
}
