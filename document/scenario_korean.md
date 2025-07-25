# NoTI 최종 발표 시나리오
## 15분 학생 발표 가이드
**팀원:** 신우진, 최태진, 김용민, 신진철

---

# 📝 발표 스크립트 (15분)

## **슬라이드 1: 오프닝 슬라이드**
**🎭 발표자:** "안녕하세요, 오늘 저희가 발표할 프로젝트는 NoTI - AI 기반 음성 녹음 및 회의록 자동화 서비스입니다."

**📺 화면 표시:** 
- 프로젝트 제목: "NoTI - 말하는 순간, 회의록이 완성된다"
- 팀원 이름
- 대학교 로고

**🗣️ 발표 대사:**
"저는 [이름]이고, 팀원 신우진, 최태진, 김용민, 신진철과 함께 한 학기 동안 NoTI를 개발했습니다. NoTI는 'Note Taking Intelligence'의 줄임말이며, 저희 태그라인은 '말하는 순간, 회의록이 완성된다'입니다."

---

## **1. 개요** (2분)

### **슬라이드 2: 문제 정의**
**📺 화면 표시:**
- 통계: "회의 문서화에 평균 30-45분 소요"
- 문제점: 수동 전사, 세부사항 누락, 시간 소모
- 대상 사용자: 직장인, 학생, 연구자

**🗣️ 발표 대사:**
"저희는 직장인들이 각 회의 후 수동으로 회의록을 작성하는 데 30-45분을 소비한다는 것을 파악했습니다. 이는 중요한 세부사항 누락과 상당한 시간 낭비로 이어집니다. 저희 대상 사용자는 매일 여러 회의에 참석하는 직장인, 학생, 연구자들입니다."

### **슬라이드 3: 솔루션 개요**
**📺 화면 표시:**
- NoTI 앱 아이콘 및 주요 기능
- 사용자 여정: 녹음 → 전사 → 요약 → 채팅
- 주요 이점: 90% 시간 단축, AI 기반 정확성

**🗣️ 발표 대사:**
"NoTI는 종합적인 솔루션을 제공합니다. 사용자는 단순히 회의를 녹음하기만 하면, 저희 AI가 자동으로 전사본, 요약본을 생성하고, 회의 내용에 대한 대화형 Q&A까지 가능하게 합니다. 저희는 회의 문서화에서 90% 시간 단축을 달성했습니다."

---

## **2. 아이디어 선정 도구 및 핵심 기능** (2분)

### **슬라이드 4: 기술 스택 결정**
**📺 화면 표시:**
- 아키텍처 다이어그램: Android 앱 ↔ Spring Boot 백엔드 ↔ AI 서비스
- 기술 선택과 근거

**🗣️ 발표 대사:**
"Android 앱 개발은 java와 kotlin을 사용하였습니다. (kotlin 선택 이유) 백엔드에서는 Spring Boot를 사용했으며,  JWT 보안과 함께 강력한 백엔드 아키텍처를 제공합니다. AI의 경우, 정확성을 위해 Google Speech-to-Text와 지능적인 요약을 위해 Gemini AI를 통합했습니다."

### **슬라이드 5: 핵심 기능**
**📺 화면 표시:**
- 기능 쇼케이스: 실시간 녹음, STT 변환, AI 요약, 대화형 챗봇
- 각 기능의 기술 사양

**🗣️ 발표 대사:**
"저희의 네 가지 핵심 기능은 다음과 같습니다. 첫째, 노이즈 캔슬링이 적용된 실시간 오디오 녹음. 둘째, Google API를 사용한 Speech-to-Text 변환. 셋째, 핵심 포인트와 액션 아이템을 추출하는 AI 기반 요약. 넷째, 회의 내용에 대한 질문에 답변하는 대화형 챗봇입니다."

---

## **3. 각 레이아웃과 액티비티 파일의 UI/UX** (4분)

### **슬라이드 6: 스플래시 및 인증 플로우**
**📺 화면 표시:**
- 스크린샷: splashActivity → SignInActivity → SignUpActivity
- 액티비티 파일의 코드 스니펫

**🗣️ 발표 대사:**
"저희 UI/UX 디자인을 살펴보겠습니다. splashActivity.kt부터 시작해서, 사용자는 브랜딩된 스플래시 화면을 봅니다. 인증 플로우는 Material Design 컴포넌트와 부드러운 애니메이션을 적용한 SignInActivity.kt와 SignUpActivity.kt를 사용합니다."

### **슬라이드 7: 메인 대시보드**
**📺 화면 표시:**
- DashBoardActivity 스크린샷
- RecyclerView 구현을 보여주는 코드 스니펫
- 내비게이션 메뉴 및 파일 관리 기능

**🗣️ 발표 대사:**
"DashBoardActivity.kt는 저희 메인 허브 역할을 합니다. 사용자는 RecyclerView에서 녹음 히스토리를 보고, 로컬 또는 서버 파일별로 필터링하며, 빠른 액션에 접근할 수 있습니다. UI는 더 나은 사용자 경험을 위해 부드러운 애니메이션이 적용된 Material Design 카드를 구현합니다."

### **슬라이드 8: 녹음 인터페이스**
**📺 화면 표시:**
- RecordActivity 스크린샷
- MediaRecorder 구현을 보여주는 코드
- 녹음 컨트롤 및 시각적 피드백

**🗣️ 발표 대사:**
"RecordActivity.kt는 핵심 녹음 기능을 처리합니다. 권한 처리, 녹음 중 시각적 피드백, 파일명 대화상자가 포함된 Android의 MediaRecorder를 사용합니다. 인터페이스는 녹음 지속시간을 표시하고 명확한 시작/정지 컨트롤을 제공합니다."

### **슬라이드 9: 녹음 상세 및 챗봇**
**📺 화면 표시:**
- RecordingDetailActivity 스크린샷
- ChatbotActivity 인터페이스
- 채팅 메시지 레이아웃 (item_chat_user.xml, item_chat_ai.xml)

**🗣️ 발표 대사:**
"RecordingDetailActivity.kt는 재생 컨트롤과 함께 전사본과 요약본을 표시합니다. ChatbotActivity.kt는 사용자와 AI 메시지를 위한 커스텀 레이아웃을 사용하는 RecyclerView로 대화형 AI를 구현하여 자연스러운 채팅 경험을 만듭니다."

---

## **4. 백엔드가 프론트엔드의 API 요청을 처리하는 방법** (3분)

### **슬라이드 10: API 아키텍처**
**📺 화면 표시:**
- 백엔드 패키지 구조
- Controller → Service → Repository 패턴 다이어그램
- JWT 인증 플로우

**🗣️ 발표 대사:**
"저희 백엔드는 MVC 아키텍처를 따릅니다. Controller는 HTTP 요청을 처리하고, Service는 비즈니스 로직을 포함하며, Repository는 데이터 접근을 관리합니다. JWT 토큰은 모든 엔드포인트를 보안화하여, 인증된 사용자만 자신의 데이터에 접근할 수 있도록 합니다."

### **슬라이드 11: 파일 업로드 및 처리**
**📺 화면 표시:**
- FileController.java 코드
- 오디오 파일 업로드 프로세스
- 데이터베이스 엔티티 관계

**🗣️ 발표 대사:**
"사용자가 오디오 파일을 업로드할 때, FileController.java가 멀티파트 요청을 처리하고, 파일 타입을 검증하며, 고유한 파일명을 생성하고, 메타데이터를 MySQL에 저장합니다. AudioFileEntity.java는 사용자 관계가 포함된 데이터베이스 스키마에 매핑됩니다."

### **슬라이드 12: 인증료
3. 파일 저장 및 업로드
4. 전사 결과 보기
5. AI 요약 표시
6. 챗봇에 질문하기

### **슬라이드 17: 결과 및 영향**
**📺 화면 표시:**
- 성능 지표: 95% STT 정확도, 3초 요약 생성
- 사용자 피드백 및 향후 로드맵
- 팀 성과

**🗣️ 발표 대사:**
"NoTI는 95% 전사 정확도를 달성하고 3초 만에 요약을 생성합니다. 현대 AI 기술을 직관적인 모바일 디자인과 성공적으로 통합했습니다. 향후 개선사항으로는 교수님께서 중간 피드백에서 말씀해 주신 실시간 협업 기능 제공과 다국어 지원등이 있습니다."

### **슬라이드 18: 감사합니다**
**📺 화면 표시:**
- 팀 사진
- 연락처 정보
- "질문이 있으신가요?" 프롬프트

**🗣️ 발표 대사:**
"들어주셔서 감사합니다. 질문이 있으시면 답변해드리겠습니다."

---

# 📱 필요 자료

**보여줄 코드 예제:**
- [ ] DashBoardActivity.kt (UI 구현)
- [ ] RecordActivity.kt (녹음 로직)
- [ ] FileController.java (API 엔드포인트)
- [ ] GeminiService.java (AI 통합)
- [ ] JWTUtil.java (보안 구현)

- [ ] | 주차       | 날짜         | 주요 개발 내용                                                                                       | Git 커밋 증거                                                                                                    | 스크럼 회의                |
|------------|--------------|------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|----------------------------|
| 1주차      | 3/14         | - 프로젝트 기획 및 아이디어 확정<br>- 아이디어 브레인스토밍<br>- 팀 활동 기록 시작<br>- 초기 프로젝트 구조 설정 | - `6925eb7` 팀 활동 기록<br>- `09e5927` Create Team_Activity_Record.txt                                        | ✅ 25_03_14 주간 스크럼 회의 |
| 2주차      | 3/21         | - 아이디어 구체화 및 기술 스택 결정<br>- 프로젝트 상세 정의<br>- 기술 문서 작성<br>- Figma UI/UX 설계 시작 | - `5c1c52f` pigma url<br>- `4e95cec` pigma url<br>- `777af86` 프로젝트 계획서                                    | ✅ 25_03_21 주간 스크럼 회의 |
| 3주차      | 3/28         | - 기본 UI 구현 시작<br>- Android 프로젝트 초기 설정<br>- Splash 화면 구현<br>- 로그인/회원가입 화면 구현 | - `cbb48dc` splash 화면 구현<br>- `c8be7d2` 로그인, 회원가입 화면 구현                                          | ✅ 25_03_28 주간 스크럼 회의 |
| 4주차      | 4/4          | - 백엔드 서버 기초 구축<br>- Spring Boot 프로젝트 설정<br>- Main Activity 초안<br>- 파일 구조 정리    | - `af3a99c` 스프링 커밋<br>- `b2e6128` Main activity 초안<br>- `974275f` record_layout 초안                      | ✅ 25_04_04 주간 스크럼 회의 |
| 5주차      | 4/11         | - 인증 시스템 구현<br>- 서버 연결 제외 인증 기능<br>- Record layout 초안<br>- 파일 정리 및 구조 개선    | - `fd05f94` 서버 연결 제외 auth 구현<br>- `ced0fda`, `c88b419` 파일 정리                                         | ✅ 25_04_11 주간 스크럼 회의 |
| 🛑 휴식    | 4/18~5/1     | - 중간고사 기간 + 연휴<br>- 개발 일시 중단<br>- 중간고사 및 어린이날 연휴<br>- 2주간 프로젝트 휴식         | - 없음                                                                                                           | 🔄 개발 휴식 기간           |
| 6주차      | 5/2          | - 개발 재개 - 녹음 기능 완성<br>- 애자일 과제 및 문서화<br>- 하단 Navbar 기능 구현                     | - `fe9e277`, `3e54d2c` 녹음 기능 구현<br>- `c0ca773` 하단 Navbar 기능 구현                                      | ✅ 25_05_02 주간 스크럼 회의 |
| 7주차      | 5/9          | - STT 및 AI 서비스 통합<br>- 오디오 파일 업로드 시스템<br>- Whisper 모델 통합                          | - `b1b323b` STT/요약 AI 구축, STT API<br>- `f0f178b`, `4776e23` 오디오 파일 업데이트                             | ✅ 25_05_09 주간 스크럼 회의 |
| 8주차      | 5/16         | - 서버-클라이언트 연동<br>- 파일 업로드 기능<br>- 오디오 정보 제공 API<br>- 서버 로그 추가               | - `fadce15` 녹음 파일 클라이언트 -> 서버<br>- `35b362d` 오디오 정보 API<br>- `3c4ac04` 필요없는 파일 삭제       | ✅ 25_05_16 주간 스크럼 회의 |
| 9주차      | 5/23         | - 요약 및 스크립트 기능 완성<br>- 서버 녹음 파일 로드<br>- UI/UX 최적화 시작                           | - `17d4112` 요약/스크립트 기능 구현<br>- `5240c27` 서버 녹음 파일 로드<br>- `124036d` 내 정보 페이지 초안       | ✅ 25_05_23 주간 스크럼 회의 |
| 10주차     | 5/30         | - 사용자 정보 관리 및 UI 개선<br>- 파일 관리 API 완성<br>- Dashboard UI/UX 개선                         | - `01a7ab1` 사용자 정보 API<br>- `e9d567d` 파일 이름 변경 API<br>- `882572e` 사용자 정보 load/edit 기능 구현     | ✅ 25_05_30 주간 스크럼 회의 |
| 11~14주차  | 6/6~6/19     | - 챗봇 시스템 구현<br>- 데모 영상 제작<br>- 발표 자료 완성<br>- 프로젝트 최종 마무리                     | - `ec2a902` 챗봇 API 추가<br>- `c3eb639` 챗봇 연결 완료<br>- `36146cd` 데모 자료<br>- `8b86c68` 최종 수정     | 📋 최종 완성 기간           |

