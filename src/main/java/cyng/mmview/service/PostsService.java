package cyng.mmview.service;

import cyng.mmview.domain.Posts;
import cyng.mmview.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PostsService {
    private final PostsRepository postsRepository;

    public void addPost(Posts posts) {
        postsRepository.save(posts);
    }

    public Page<Posts> allPosts(Pageable pageable) {
        return postsRepository.findAll(pageable);

    }
    public Optional<Posts> onePosts(long id) {
        return postsRepository.findById(id);
    }

    public void delPosts(Posts posts) {
        postsRepository.delete(posts);
    }

    public void editPosts(long postsId, Posts params) {
        Posts posts = onePosts(postsId).orElse(null);
        posts.setHeader(params.getHeader());
        posts.setContent(params.getContent());
    }

}
