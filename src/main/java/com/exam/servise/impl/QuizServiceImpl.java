package com.exam.servise.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.servise.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        logger.info("Saving new quiz with title: {}", quiz.getTitle());
        logger.info("Quiz saved successfully with ID: {}", quiz.getqId());
       return this.quizRepository.save(quiz);

    }

    @Override
    public Set<Quiz> getQuizzes() {
        logger.info("Fetching all quizzes from database");
        return new HashSet<>(this.quizRepository.findAll());
        
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        logger.info("Updating quiz with ID: {}", quiz.getqId());
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        logger.info("Fetching quiz with ID: {}", quizId);
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        // TODO Auto-generated method stub
        Quiz quiz = new Quiz();
        quiz.setqId(quizId); 
        logger.info("Quiz deleted successfully with ID: {}", quizId);   
        this.quizRepository.delete(quiz);
    }
    
    

}
