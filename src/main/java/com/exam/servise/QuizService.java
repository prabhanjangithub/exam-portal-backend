package com.exam.servise;

import java.util.Set;

import com.exam.model.exam.Quiz;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Set<Quiz> getQuizzes();
    public Quiz updateQuiz(Quiz quiz);
    public Quiz getQuiz(Long quizId);
    

    public void deleteQuiz(Long quizId);
}
