package com.example.coursework_2.impl;

import com.example.coursework_2.QuestionService;
import com.example.coursework_2.exception.CollectionAreEmptyException;
import com.example.coursework_2.exception.ElementMissingException;
import com.example.coursework_2.exception.QuestionAlreadyExistsException;
import com.example.coursework_2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questionSet = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questionSet.add(question)) {
            throw new QuestionAlreadyExistsException("Вопрос уже существует");
        }
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.remove(question)) {
            throw new ElementMissingException("Такой вопрос в списке отсутствует");
        }
        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionSet);
    }

    @Override
    public Question getRandomQuestion() {
        if (questionSet.isEmpty()) {
            throw new CollectionAreEmptyException("Коллекция вопросов пуста");
        }
        return new ArrayList<>(questionSet).get(random.nextInt(questionSet.size()));
    }
}
