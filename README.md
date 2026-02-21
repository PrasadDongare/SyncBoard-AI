# Title-SyncBoard AI – Real-Time Collaborative Work Management Platform

**SyncBoard AI** is a high-performance, collaborative work management platform built with a modern Java stack. It combines the power of **Spring WebSockets** for zero-latency collaboration and **Spring AI** to automate project workflows.

---

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Backend** | Java, Spring Boot, Spring Data JPA |
| **Real-Time** | Spring WebSockets (STOMP) |
| **AI Integration** | Spring AI, OpenAI API |
| **Database** | MySQL |
| **Frontend** | JavaScript, Tailwind CSS, HTML5 |
| **Tools** | Maven, Lombok, Git, Postman |

---

## ✨ Key Features

### 🔄 Real-Time Synchronization
Implemented a full-duplex communication layer using **WebSockets**. When any user creates, moves, or deletes a task, the changes are broadcasted instantly to all connected clients without page refreshes.

### 🧠 AI Task Enhancement
Integrated **Spring AI** to transform short titles into professional task descriptions. Built a **resilient fallback strategy** ensuring that the system remains functional even if the AI service is unreachable.

### 📊 Live Analytics Dashboard
A dynamic progress tracking system powered by **Java Streams API**. It aggregates task data in real-time to provide a visual completion percentage and workload distribution.

### 📋 Professional Kanban UI
A utility-first, responsive interface built with **Tailwind CSS**. Features smooth state transitions (TODO → IN PROGRESS → DONE) and an intuitive user experience.

---

## 🏗 Architectural Highlights

* **Layered Architecture:** Strict separation of concerns between Controller, Service, and Repository layers.
* **State Machine Logic:** Backend-controlled task transitions to ensure data integrity.
* **Resilience Patterns:** Graceful degradation logic implemented in the AI service layer.
* **Database Optimization:** Efficient MySQL schema design with JPA-managed persistence.
