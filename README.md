# DataLabel — AI Text Annotation & Dataset Builder
### Built for Ethara.AI Full-Stack Assessment

---

## How to Run Locally

### Step 1 — Setup MySQL
```sql
CREATE DATABASE annotation_db;
```

### Step 2 — Update application.properties
Open `src/main/resources/application.properties` and set your MySQL password:
```
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3 — Run the app
```bash
mvn spring-boot:run
```

### Step 4 — Open the app
Go to: http://localhost:8080

---

## API Endpoints

| Method | Endpoint                    | Description              |
|--------|-----------------------------|--------------------------|
| POST   | /api/entries                | Add a new text entry     |
| GET    | /api/entries                | Get all entries          |
| GET    | /api/entries/{id}           | Get single entry         |
| PUT    | /api/entries/{id}           | Update an entry          |
| DELETE | /api/entries/{id}           | Delete an entry          |
| GET    | /api/entries/filter?label=X | Filter by label          |
| GET    | /api/entries/stats          | Get label-wise counts    |
| GET    | /api/entries/export         | Download as JSON         |
| GET    | /api/entries/export/csv     | Download as CSV          |

---

## Deploy on Railway (Free Live Link)

1. Push your code to GitHub
2. Go to https://railway.app → New Project → Deploy from GitHub
3. Add these environment variables:
   - SPRING_DATASOURCE_URL
   - SPRING_DATASOURCE_USERNAME
   - SPRING_DATASOURCE_PASSWORD
4. Railway will give you a live URL like: https://annotation-app.up.railway.app

---

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.2, Spring Data JPA
- **Database**: MySQL
- **Frontend**: Vanilla HTML/CSS/JavaScript (single file)
- **Export**: JSON and CSV download endpoints

---

## Features
- Add text entries with labels (Positive / Negative / Neutral / Custom)
- Category tagging (Sentiment, Spam, Intent, Emotion, Topic)
- Annotator tracking
- Filter entries by label
- Real-time stats dashboard
- Export full dataset as JSON or CSV (training-ready format)
- Delete entries

---

Built by: [Your Name]
College: SNIST, Hyderabad
