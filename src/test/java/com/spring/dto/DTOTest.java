package com.spring.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DTOTest {
	
	private MessageDTO msgDto;
	private ResponseDTO resDto;
	private UserDTO usrDto;
	
	@Test
	void messageDTOTest() {
		msgDto = new MessageDTO();
		msgDto.setMsgDetail("t");
		assertThat(msgDto.getMsgDetail()).isEqualTo("t");
	}

}
