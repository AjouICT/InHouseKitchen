package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.*;
import com.ajouict.inhousekitchen.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    //private static final Logger log= LoggerFactory.getLogger(ReviewController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/{userId}")
    public String show(@PathVariable String userId, Model model, HttpSession session) {
        System.out.println("여기");
        User temp = userRepository.findByUserId(userId);
        // 호스트
        Host user = searchRepository.findByHost(temp);

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        
        List<Review> reviewList = user.getReviews();

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("user", user);

        return "/review/add_review";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/review/list_review";
    }

    @PostMapping("/create/{hostId}/{userId}")
    public String create(@PathVariable Long hostId, @PathVariable String userId, HttpSession session, String title, String contents, String score){
        // 작성자
        User writer = HttpSessionUtils.getUserFromSession(session);
        // 호스트
        Host host = searchRepository.findHostById(hostId);
        // 호스트를 유저로 변환
        User tempHost = userRepository.getOne(hostId);

        if(score == null){
            return String.format("redirect:/review/%s", tempHost.getUserId());
        }
        // String의 별점을 int로 변환
        int iScore = Integer.parseInt(score);
        // 별점 적용
        host.calculateScore(iScore, 1);
        // 자기 자신에게는 리뷰를 달지 못하게 함
        if(host.IsSameHost(writer)){
           return String.format("redirect:/review/%s", tempHost.getUserId());
        }
        // 이미 작성했을 경우 작성하지 못하게 함


        // 작성한 리뷰
        Review review = new Review(writer, host, title, contents, iScore);
        reviewRepository.save(review);

        return String.format("redirect:/review/%s", tempHost.getUserId());
    }

    @GetMapping("/update/{reviewId}")
    public String update(@PathVariable Long reviewId, HttpSession session, Model model){
        // 글쓴이
        User writer = HttpSessionUtils.getUserFromSession(session);
        // 리뷰
        Review review = reviewRepository.findReviewById(reviewId);
        User hostUser = review.getHost().getHost();

        if(!review.IsSameWriter(writer)) {
            return String.format("redirect:/review/%s", hostUser.getUserId());
        }
        model.addAttribute("review", review);
        return "review/update_review";
    }

    @PutMapping("/{reviewId}")
    public String update(@PathVariable Long reviewId, String title, String contents, String score, HttpSession session){
        Review review = reviewRepository.findReviewById(reviewId);
        Host host = review.getHost();

        int iScore = Integer.parseInt(score);

        // 수정
        host.calculateScore(iScore - review.getScore(), 2);
        review.update(review, title, contents, iScore);
        reviewRepository.save(review);

        return String.format("redirect:/review/%s", review.getHost().getHost().getUserId());
    }

    @GetMapping("/delete/{reviewId}")
    public String delete(@PathVariable Long reviewId, HttpSession session){
        Review review = reviewRepository.findReviewById(reviewId);
        Host host = review.getHost();
        User hostUser = review.getHost().getHost();
        User writer = HttpSessionUtils.getUserFromSession(session);

        // 작성자가 아닌 경우 리스트로 보냄
        if(!review.IsSameWriter(writer)){
            return String.format("redirect:/review/%s", hostUser.getUserId());
        }
        // 별점 적용
        System.out.println("삭제 적용");
        host.calculateScore(review.getScore(), 0);
        reviewRepository.delete(review);



        return String.format("redirect:/review/%s", hostUser.getUserId());
    }
}
