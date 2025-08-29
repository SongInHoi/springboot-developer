package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 컨트롤러를 테스트할 때 작성하는 일반적인 테스트 코드 패턴
@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach // 테스트 실행 후 실행하는 메서드
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given (멤버를 저장합니다.)
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when (멤버 리스트를 조회하는 API를 호출합니다.)
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)); // accept() 메서드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드

        // then (응답 코드가 200 OK이고, 반환받은 값 중에 0번째 요소의 id와 name이 저장된 값과 같은지 확인합니다.)
        result
                .andExpect(status().isOk()) // andExcept() 메서드는 응답을 검증합니다.
                .andExpect(jsonPath("$[0].id").value(savedMember.getId())) // jsonPath()는 JSON 응답값의 값을 가져오는 역할을 하는 메서드
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}