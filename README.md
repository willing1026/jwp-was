# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

### commit message
- add : 신규기능 추가
- modify : 기능 수정
- fix : 오류 개선


### step1

#### 요구사항 1 - QueryString 파싱
* method와 path분리
* path에서 queryString 분리
    > queryString이 존재하는지 확인 필요
    > 존재하면 분리 진행.
    > 없으면 PASS
* queryString에서 key, value 분리
> 공통기능 : 구분자를 기준으로 데이터 분리

 * Path객체 만들기
    > path와 parameter list 저장
 
* 검증
 > requestLine이 null 또는 size 0
 
 #### 요구사항 3 - if문 제거 연습
 * FigureCreatorFactory 생성 + 도형별 생성객체 Map으로 저장
 * FigureCreator 상속받아 도형별 생성객체 생성

### step2

* step1 피드백 반영
- [x] QueryString 파라미터 1급 컬랙션변경
    - Parameters객체 생성
    - 입력받은 queryString 패턴 검사
    - Parameters에서 queryString 분리. Parameter객체 생성
    - Parameters -> Path객체에 반영
 
- [x] @CsvSource 테스트케이스 적용해보기
    - 참고 : [4.4. CSV Literals](https://www.baeldung.com/parameterized-tests-junit-5)
    - ArgumentsAccessor 적용 (여러개의 assertThat 결과 확인 가능)

* step2 요구사항
- [x] 요구사항 1 : http://localhost:8080/index.html 로 접속했을 때 webapp 디렉토리의 index.html 파일을 읽어 클라이언트에 응답한다.
    - 테스트를 위한 참고자료 [string to inputstream](https://www.baeldung.com/convert-string-to-input-stream)
    
- [x] 요구사항 2 : 회원가입 / 결과 -> model.User클래스에 저장
    - [x] 요청 path별로 필요한 서비스 호출 기능 추가하기
    - [x] webService 인터페이스를 만들고, 상속받아 사용
    - [x] Map으로 해당 서비스들 미리 만들어두고 Mapper에서 불러와서 사용하도록 설정
      (step1 요구사항3 참고해서 진행하기)
    - Parameters : List -> Map으로 변경하기 <String, Parameter>
      그래야 User객체 저장할때 순서에 구애받지 않고 할 수 있어
    
- [x] 요구사항 3 : form 태그 method를 get에서 post로 수정 / 회원가입 기능 정상 동작하도록 구현

- [x] 요구사항 4 : 회원가입 후 index.html로 이동 (redirect)
- [x] 요구사항 5 : 로그인 기능 구현 ( 성공,실패 case) / 쿠키사용
    - [x] UserService 내부에서 각 경로별로 method분리
    - [x] WebService로 Outputstream 넘길 것.
    
- [x] 요구사항 6 : 로그인상태인 경우 user/list보여주기 / 아닌경우 로그인페이지
    - [x] /user/list 접근했을때 로그인상태인지 판단 (cookie 확인)
    - [x] 로그인했으면 Database에 있는 user정보 읽어서 화면 출력
    - [x] 아닌경우 로그인 페이지로 이동
    - [x] handlebars적용, 결과출력
    
- [x] 요구사항 7 : stylesheet 지원하도록 구현

> 뒤로 갈수록 테스트케이스를 어떻게 만들어야할지 잘 모르겠다.

-----------------------------------------------------
## step2 구조 재정리

#### 1. HttpRequest
- [x] RequestLine : method, url, queryString 정보 보관
- [x] RequestHeader : header 각종정보 보관
- [x] RequestParams : queryString 또는 reqBody 데이터를 key,value형태로 나눠서 보관

 #### 2. HttpResponse
- [x] StatusLine
- [x] Location
- [x] ContentType
- [x] ContentLength
- [x] Cookie

 #### 3. RequestMapping
- [x] PathMapper -> RequestMapping 
    - [x] 각각 controller를 호출하도록 변경
    - [x] responseUrl은 controller에서 관리하도록 변경

- [x] ResourceMapping
    - [x] css, js, font, images 지원

 #### 4. Controller
- [x] HomeController : index.html
- [x] UserCreateController : 회원가입
- [x] UserListController : 회원목록
- [x] UserLoginController : 로그인
- [x] ForwardController : 단순 forwarding

-----------------------------------------------------
# step3
- [x] Thread Pool을 적용, 다수의 사용자 요청 동시 처리 (FixedThreadPool)
- [x] HTTP 요청/응답 처리 기능 분리, 재사용 가능 구조로 변경
- [x] Bad Smell 코드 리팩토링

-----------------------------------------------------
# step4
- [x] HttpSession VO 생성
    - [x] getId()
    - [x] setAttribute(String name, Object value), getAttribute(String name)
    - [x] removeAttribute(String name)
    - [x] invalidate()
    
- [x] sessionManager (Map<String, HttpSession>)
    - [x] Client Header Cookie 에 httpSession ID가 없거나, 다른경우 -> 신규 생성. 응답
    - [x] 존재하는 경우 해당 HttpSession 반환
    
- [x] session ID 생성(UUID)
    - [x] HttpRequestHeader 생성할때 cookie에서 SessionId 꺼내기
    - [x] 없으면 생성하고, cookie에 sessionId 추가해줘야함
    - [x] Response에 sessionId 추가하기

- [x] sessionId로 로그인여부 확인하기
    - [x] 로그인 할때 attribute에 User정보 등록
    - [x] 로그인 여부 확인
    
- [ ] 로그아웃 기능 구현 / 저장된 session 모두 삭제

-----------------------------------------------------
#### step4 관련 궁금증
- 세션은 언제 할당해줘야하지?
    - HttpServletRequest 보니까 getSession 을 할때 없으면 새로 만들도록 되어있다.
    - [세션개념2](https://cjh5414.github.io/cookie-and-session/)
        - 로그인과 상관없이 세션생성
        - 로그인하면 새로운 세션으로 변경
        - 로그아웃하면 기존세션 제거, 새로운 세션 부여
        - 브라우저 종료 -> 세션 제거 ( how? )

- 브라우저 종료시점 알 수 있나? 세션 종료는 어떻게 해?
    - [How to detect browser closing?](https://stackoverflow.com/questions/299679/how-to-detect-browser-closing)
    - 브라우저 종료는 서버사이드에선 알 수 없다.

- 참고 
- [HTTP Session](https://mohwaproject.tistory.com/entry/HTTP-Session-%EC%9D%B4%EB%9E%80)
- [쿠키 / 세선 개념](https://interconnection.tistory.com/74) 

 