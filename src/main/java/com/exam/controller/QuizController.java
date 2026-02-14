package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.exam.model.exam.Quiz;
import com.exam.servise.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    //add quiz services
    @PostMapping("/")
    public ResponseEntity<Quiz> add (@RequestBody Quiz quiz)
    {
        logger.info("Quiz created successfully with ID: {}", quiz.getqId());

        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz)
    {
        logger.info("Updating quiz with ID: {}", quiz.getqId());

        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity <?> quizzes()
    {
        logger.info("Fetching all quizzes");

        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid)
    {
        logger.info("Fetching quiz with ID: {}", qid);

        return this.quizService.getQuiz(qid);
    }

    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid") Long qid)
    {

        this.quizService.deleteQuiz(qid); 
        logger.info("Quiz deleted successfully with ID: {}", qid);

    }
}
