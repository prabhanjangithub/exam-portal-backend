package com.exam.servise.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.servise.QuestionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class QuestionServicesImpl implements QuestionServices{
    private static final Logger logger = LoggerFactory.getLogger(QuestionServicesImpl.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        // TODO Auto-generated method stub
        logger.info("Question saved successfully with ID: {}", question.getQueId());

        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        logger.info("Updating question with ID: {}", question.getQueId());

        // TODO Auto-generated method stub
        return this.questionRepository.save(question);    
    }

    @Override
    public Set<Question> getQuestions() {
        logger.info("Fetching all questions from database");

        // TODO Auto-generated method stub
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        logger.info("Fetching question with ID: {}", questionId);

        // TODO Auto-generated method stub
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        logger.info("Fetching questions for quiz ID: {}", quiz.getqId());

        // TODO Auto-generated method stub
        return this.questionRepository.findByQuiz(quiz);
        }

    @Override
    public void deleteQuestion(Long quesId) {
        logger.warn("Deleting question with ID: {}", quesId);
        // TODO Auto-generated method stub
       Question question=new Question();
       question.setQueId(quesId);
       this.questionRepository.delete(question);
       logger.info("Question deleted successfully with ID: {}", quesId);
    }
    

}
