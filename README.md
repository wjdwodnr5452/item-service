# 상품 목록 타임리스

##### 타임리프 사용 선언
```
<html xmlns:th="http://www.thymeleaf.org">
```

##### 속성 변경 - th:href
- href= "value1" 을 th:href="value2" 의 값으로 변경
- 타임리프 뷰 템플릿을 거치게 되면 원래 값을 th:xxxx 값으로 변경한다. 만약 값이 없다면 새로 생성한다.

##### 타임리프 핵심
- 핵심은 th:xxx가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체 함 th:xxx이 없으면 기존 html의 xxx 속성이 그대로 사용된다.
- HTML을 파일로 직접 열었을 때, th:xxx 가 있어도 웹 브라우저는 ht: 속성을 알지 못하므로 무시한다.


##### URL 링크 표현식 -@{...}
- @{...} : 타임리프는 url 링크를 사용하는 경우 @{...} 를 사용한다. 이것을 url 링크 표현식이다.
- url 링크 표현식을 사용하면 서블릿 컨텍스트를 자동으로 포함한다.

##### 속성 변경 - th:onclick
- onclick="location.href='addForm.html'"
- th:onclick="|location.href='@{/basic/items/add}'|"

##### 리터럴 대체 - |...|
- 타임리프에서 문자와 표현식 등은 분리되어 있기 때문에 더해서 사용해야 한다.
  - <span th:text="'Welcome to our application, ' + ${user.name} + '!'">
- 리터럴 대체 문법을 사용하면, 더하기 없이 편리하게 사용할 수 있다.
   - <span th:text="|Welcome to our application, ${user.name}!|">

##### 반복 출력 - th:each
- <tr th:each="item : ${items}">
- 반복은 th:each 를 사용한다. 이렇게 하면 모델에 포함된 items 컬렉션 데이터가 item 변수에 하나씩 포함 되고, 반복문 안에서 item 변수를 사용할 수 있다.
- 컬렉션의 수 만큼 <tr>..</tr> 이 하위 테그를 포함해서 생성된다.

##### 변수 표현식 - ${...}
- <td th:text="${item.price}">10000</td>
- 모델에 포함된 값이나, 타임리프 변수로 선언한 값을 조회할 수 있다.
- 프로퍼티 접근법을 사용한다. ( item.getPrice() )

##### 내용 변경 - th:text
- <td th:text="${item.price}">10000</td>
- 내용의 값을 th:text 의 값으로 변경한다.
- 여기서는 10000을 ${item.price} 의 값으로 변경한다.

##### URL 링크 표현식2 - @{...},
- th:href="@{/basic/items/{itemId}(itemId=${item.id})}"
- 상품 ID를 선택하는 링크를 확인해보자.
- URL 링크 표현식을 사용하면 경로를 템플릿처럼 편리하게 사용할 수 있다.
- 경로 변수( {itemId} ) 뿐만 아니라 쿼리 파라미터도 생성한다.
- 예) th:href="@{/basic/items/{itemId}(itemId=${item.id}, query='test')}"
  - 생성 링크: http://localhost:8080/basic/items/1?query=test

##### URL 링크 간단히
- th:href="@{|/basic/items/${item.id}|}"
- 상품 이름을 선택하는 링크를 확인해보자.
- 리터럴 대체 문법을 활용해서 간단히 사용할 수도 있다.
