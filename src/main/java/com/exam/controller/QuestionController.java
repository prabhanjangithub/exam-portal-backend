package com.exam.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.servise.QuestionServices;
import com.exam.servise.QuizService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionServices services;
    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.services.addQuestion(question));
    }



    @PutMapping("/")
    public ResponseEntity <Question> update(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.services.updateQuestion(question));

    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity <?> getQuestionsOfQuiz(@PathVariable("qid") Long qid)
    {
    //     Quiz quiz= new Quiz();
    //     quiz.setqId(qid); 
        
    //    Set<Question> getQuestionsOfQuiz=  this.services.getQuestionsOfQuiz(quiz);
    //    return ResponseEntity.ok(getQuestionsOfQuiz);

   Quiz quiz=  this.quizService.getQuiz(qid);
   Set<Question> question=  quiz.getQuestion();
   List list= new ArrayList(question);
   if(list.size() > Integer.parseInt(quiz.getNumberOfquestion()))
   {
    list = list.subList(0,Integer.parseInt(quiz.getNumberOfquestion()+1));


   }
   return ResponseEntity.ok(list);
    }

    @GetMapping("/{queId}")
    public Question get(@PathVariable("queId") Long qusId)
    
    {
        return this.services.getQuestion(qusId);
    }

    @DeleteMapping("/{queId}")
    
        public void delete(@PathVariable("queId") Long queId)
        {
                this.services.deleteQuestion(queId);
        }

}
