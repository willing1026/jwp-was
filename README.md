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