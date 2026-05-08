-- ================================================
--   DataLabel - AI Text Annotation Tool
--   Database Schema
-- ================================================

-- Create database (run this first in MySQL Workbench)
CREATE DATABASE IF NOT EXISTS annotation_db;
USE annotation_db;

-- Main table
CREATE TABLE IF NOT EXISTS text_entries (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    text         TEXT         NOT NULL,
    label        VARCHAR(100) NOT NULL,
    category     VARCHAR(100),
    annotated_by VARCHAR(100),
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Sample data to test immediately
INSERT INTO text_entries (text, label, category, annotated_by) VALUES
('The product quality is excellent and delivery was super fast!', 'Positive', 'Sentiment', 'Deepthi'),
('This is the worst experience I have ever had. Never buying again.', 'Negative', 'Sentiment', 'Deepthi'),
('The package arrived on Tuesday as expected.', 'Neutral', 'Sentiment', 'Deepthi'),
('Congratulations! You have won a free iPhone. Click here now!', 'Positive', 'Spam', 'Deepthi'),
('I want to track my order status', 'Neutral', 'Intent', 'Deepthi');
