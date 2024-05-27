다음은 ENTIREPJT 프로젝트의 한글 README.md 파일입니다.

---

# ENTIREPJT - 면접 답변 자동 생성 프로그램

## 소개

**MuLLM**은 구직자들을 돕기 위해 면접 질문에 대한 예시 답변을 생성하는 프로젝트입니다. 대형 언어 모델(LLM)을 웹 인터페이스와 통합하여 사용자가 직무와 특정 면접 질문을 입력하면 일관되고 맥락에 맞는 답변을 제공합니다.

## 목차

1. [배경 및 개요](#배경-및-개요)
2. [프로젝트 구조](#프로젝트-구조)
3. [설치 및 설정](#설치-및-설정)
4. [프로젝트 실행](#프로젝트-실행)
5. [웹 인터페이스](#웹-인터페이스)
6. [API 엔드포인트](#api-엔드포인트)
7. [개선 사항](#개선-사항)
8. [기여자](#기여자)

## 배경 및 개요

구직자의 수가 853,000명을 넘어서면서 거의 모든 기업이 이력서 검토 후 면접 라운드를 진행합니다. **MuLLM**은 이러한 구직자들을 돕기 위해 면접 질문에 대한 예시 답변을 생성합니다. 이 프로젝트는 Meta에서 개발한 대형 언어 모델인 LLaMA 3을 사용하며, 상업적 용도로 무료로 사용할 수 있습니다.

### LLaMA 3 사용의 장점:
- 추가 비용 없음
- 데이터 유출 없음
- 맞춤형 학습 효과

### 데이터:
- AI 허브 오픈 데이터
- 약 70,000개의 면접 질문-답변 쌍 (텍스트 데이터 사용)

### 모델:
- Meta의 LLaMA 3
- 자원 제약으로 인해 8B 모델 선택
- GPU A100과 32GB RAM을 사용한 Colab에서의 학습
- 총 학습 시간: 8시간 30분

## 프로젝트 구조

```plaintext
entirepjt/
├── backend/
│   ├── bin/
│   │   └── bash/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/
│   │   │           └── ssafy/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               └── service/
│   │   └── resources/
│   │       └── static/
│   │           └── css/
│   │           └── fonts/
│   │           └── js/
│   └── test/
│       └── java/
│           └── com/
│               └── ssafy/
├── frontend/
│   ├── src/
│   │   ├── assets/
│   │   │   └── fonts/
│   │   ├── components/
│   │   ├── router/
│   │   └── store/
└── ml/
    └── scripts/
        └── model.py
```

## 설치 및 설정

### 사전 요구 사항
- [Node.js](https://nodejs.org/)
- [Python](https://www.python.org/) 및 conda
- [Git](https://git-scm.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)

### 설치 단계

1. **리포지토리 클론:**
   ```bash
   git clone https://github.com/yourusername/entirepjt.git
   cd entirepjt
   ```

2. **백엔드 설정:**
   - 백엔드 디렉토리로 이동:
     ```bash
     cd backend
     ```
   - 필요한 종속성 설치:
     ```bash
     ./mvnw install
     ```

3. **프론트엔드 설정:**
   - 프론트엔드 디렉토리로 이동:
     ```bash
     cd ../frontend
     ```
   - 필요한 종속성 설치:
     ```bash
     npm install
     ```

4. **Python 환경 설정:**
   - ml/scripts 디렉토리로 이동:
     ```bash
     cd ../ml/scripts
     ```
   - conda 환경 생성 및 활성화:
     ```bash
     conda create --name realfinal python=3.8
     conda activate realfinal
     pip install -r requirements.txt
     ```

## 프로젝트 실행

### 백엔드 실행
1. 백엔드 디렉토리로 이동:
   ```bash
   cd backend
   ```
2. Spring Boot 애플리케이션 시작:
   ```bash
   ./mvnw spring-boot:run
   ```

### 프론트엔드 실행
1. 프론트엔드 디렉토리로 이동:
   ```bash
   cd ../frontend
   ```
2. Vue.js 애플리케이션 시작:
   ```bash
   npm run serve
   ```

##

## 웹 인터페이스

### InputPage.vue
- 사용자가 직무와 면접 질문을 입력할 수 있습니다.
- 제출 버튼을 누르면 데이터가 백엔드로 전송되고 Output 페이지로 이동합니다.

### OutputPage.vue
- 입력된 질문과 생성된 답변을 표시합니다.

## API 엔드포인트

### 질문 저장
- **URL:** `/api/questions`
- **메서드:** `POST`
- **요청 본문:** `question`과 `position`이 포함된 JSON.
- **응답:** 저장된 질문 객체.

### 답변 예측
- **URL:** `/api/predict`
- **메서드:** `POST`
- **요청 본문:** `questionId`가 포함된 JSON.
- **응답:** 없음 (답변은 데이터베이스에 저장됨).

### 질문 가져오기
- **URL:** `/api/questions/{id}`
- **메서드:** `GET`
- **응답:** 생성된 답변이 포함된 질문 객체.

## 개선 사항

- 불완전한 답변 처리
- 숫자 데이터의 이해 향상
- 개인 커버 레터를 기반으로 질문 생성
- 다중 학습 라운드
- 숫자 데이터를 위한 별도 모델 디자인
- 개인 데이터를 저장하기 위한 CRUD 작업 구현

## 기여자

- **김민혁**
- **김영찬**
