package com.mo.service.post;

import com.mo.domain.dto.post.req.StorePostDto;
import com.mo.domain.dto.post.res.PostListRes;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import com.mo.domain.repository.PostRepo;
import com.mo.domain.repository.page.PostPageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final PostPageRepo postPageRepo;

    @Override
    @Transactional
    public void storePost(StorePostDto storePostDto, User user) {
        Post post = storePostDto.toEntity(user);

        postRepo.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostListRes> getPostList(Pageable pageable) {
        List<PostListRes> result = new ArrayList<>();

        Page<Post> postPage = postPageRepo.findAll(pageable);
        List<Post> content = postPage.getContent();

        for (Post post : content) {
            PostListRes res = new PostListRes(post.getAuthor(), post.getDate(), post.getContent());

            result.add(res);
        }

        return result;
    }
}
