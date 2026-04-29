# Football API

RESTful API built with Spring Boot, JPA, and PostgreSQL.

## Tech Stack

- Java 21
- Spring Boot 3.4
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Swagger (springdoc-openapi)

## Features

- JWT Authentication (Register / Login)
- Match Management (CRUD)
- User Following System
- Global Exception Handling
- Request Validation

## Project Structure

\`\`\`
src/main/java/com/kai/football_api/
├── user/           # User domain (auth)
├── match/          # Match domain
├── following/      # Following domain
├── jwt/            # JWT filter & util
└── common/         # Exception handler, configs
\`\`\`

## API Endpoints

### Auth
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/auth/register | 회원가입 |
| POST | /api/auth/login | 로그인 |

### Match
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/matches | 경기 생성 |
| GET | /api/matches | 전체 경기 조회 |
| GET | /api/matches/status/{status} | 상태별 경기 조회 |
| PATCH | /api/matches/{id}/score | 경기 점수 업데이트 |

### Following
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/follow/{targetId} | 팔로우 |
| DELETE | /api/follow/{targetId} | 언팔로우 |
| GET | /api/follow/followings | 팔로잉 목록 |
| GET | /api/follow/followers | 팔로워 목록 |

## Getting Started

### Requirements
- Java 21
- PostgreSQL

### Setup

1. Clone the repository
   \`\`\`bash
   git clone https://github.com/kai-dev/football-api.git
   \`\`\`

2. Configure database in `application.yml`
   \`\`\`yaml
   spring:
   datasource:
   url: jdbc:postgresql://localhost:5432/your_db
   username: your_username
   password: your_password
   \`\`\`

3. Run the application
   \`\`\`bash
   ./gradlew bootRun
   \`\`\`

4. Access Swagger UI
   \`\`\`
   http://localhost:8080/swagger-ui.html
   \`\`\`