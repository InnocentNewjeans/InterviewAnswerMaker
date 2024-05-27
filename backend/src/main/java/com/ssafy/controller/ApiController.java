package com.ssafy.controller;

import com.ssafy.service.PythonService;
import com.ssafy.model.Question;
import com.ssafy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PythonService pythonService;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/questions")
    public Question saveQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PostMapping("/predict")
    public void predict(@RequestBody Map<String, Long> request) {
        Long questionId = request.get("questionId");
        Optional<Question> questionOpt = questionRepository.findById(questionId);

        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            String answer = pythonService.runPythonScript(question.getQuestion(), question.getPosition());
            question.setAnswer(answer);
            questionRepository.save(question);
        } else {
            throw new RuntimeException("Question not found for id: " + questionId);
        }
    }

    @GetMapping("/questions/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found for id: " + id));
    }
}
-----