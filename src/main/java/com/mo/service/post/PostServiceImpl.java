package com.mo.service.post;

import com.mo.domain.dto.post.StorePostDto;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import com.mo.domain.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Override
    @Transactional
    public void storePost(StorePostDto storePostDto, User user) {
        Post post = storePostDto.toEntity(user);

        postRepo.save(post);
    }
}
