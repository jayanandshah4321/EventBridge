# 🎉 EventBridge

**EventBridge** is an event management and booking platform built with **Spring Boot** and **PostgreSQL**. It allows users to create and book events, secured using **JWT-based authentication**. The system includes robust **concurrency handling** using **Pessimistic Locking**, and performance tested with **K6** and **Grafana**.

---

## 🚀 Features

- 📝 Create and manage events
- 🎟️ Book event tickets (with concurrency control)
- 🔐 Secure login/register with JWT tokens
- 📊 Stress and load tested using K6 and Grafana
- 💾 Data persistence with PostgreSQL

---

## 🛠️ Tech Stack

- **Backend Framework:** Spring Boot
- **Database:** PostgreSQL
- **Security:** JWT (JSON Web Token)
- **Concurrency Control:** Pessimistic Locking (JPA)
- **Load Testing:** K6 + Grafana

---

## 🔐 Security

All APIs are secured using JWT-based authentication. Users must log in to receive a token which must be included in the `Authorization` header for protected routes.

---

## 🔄 Concurrency Handling

To prevent overbooking when multiple users attempt to book the same ticket:

- Implemented **Pessimistic Concurrency Control** using JPA's `@Lock(LockModeType.PESSIMISTIC_WRITE)`
- Locks the event record during the booking transaction

---

## 📈 Load Testing

- Used [K6](https://k6.io/) for writing load test scripts
- Integrated with **Grafana** for real-time monitoring
- Simulated high-concurrency booking scenarios

---

