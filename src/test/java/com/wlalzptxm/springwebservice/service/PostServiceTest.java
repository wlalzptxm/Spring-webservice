package com.wlalzptxm.springwebservice.service;

import com.wlalzptxm.springwebservice.domain.posts.Posts;
import com.wlalzptxm.springwebservice.domain.posts.PostsRepository;
import com.wlalzptxm.springwebservice.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("wlalzptxm22@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        postsService.save(dto);

        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
