package ru.rosbank.javaschool.crudapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.rosbank.javaschool.crudapi.mapper.PostMapper;
import ru.rosbank.javaschool.crudapi.repository.PostRepository;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {



    @Test
    void searchByContent() {
        PostRepository repository = mock(PostRepository.class);
        PostMapper mapper=mock(PostMapper.class);
        PostService service = new PostService(repository,mapper);
        Mockito.when(repository.findAllByContentLike("qwert1234")).thenReturn(emptyList());
        assertEquals(emptyList(), service.searchByContent("qwert1234"));
    }



}
