package com.huh.BaekJoonSupporter.comment;

import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.board.BoardRepository;
import com.huh.BaekJoonSupporter.board.BoardService;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberRepository;
import com.huh.BaekJoonSupporter.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class CommentServiceTests {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    // 글, 유저 있다는 가정 (둘다 db에서 생성)
    @Test
    @Rollback(value = false)
    void CommentTests(){
        Member member = new Member();
        Long boId = this.boardService.create("제목", "내용", member);
        Board board = new Board()
        // 댓글 생성
        Comment comment = this.commentService.create("테스트 댓글", boardService.getBoard(10L), memberService.getMember("park"));
        assertThat(comment.getContent()).isEqualTo("테스트 댓글");

//         댓글 수정
        Optional<Comment> checkco = this.commentRepository.findByContent("테스트 댓글");
        if (!checkco.isPresent());
        Comment comment1 = checkco.get();
        commentService.modify(comment1, "수정");
        assertThat(comment1.getContent()).isEqualTo("수정");

//        댓글 삭제
        Optional<Comment> checkco1 = this.commentRepository.findByContent("수정");
        if (!checkco1.isPresent());
        Comment comment12 = checkco1.get();
        commentService.delete(comment12);
        assertTrue(!comment12.getContent().isEmpty());
    }
}
